package com.mutant.mutant;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class TesterRestController {

	@Autowired
	@Qualifier("tester")
	public Tester tester;

	@Autowired
	@Qualifier("testRepository")
	public TestRepository testRepository;

	@PostMapping("/")
	public ResponseEntity<String> mutant(@RequestBody Map<String, String[]> dna) {
		boolean mutant = false;
		String error;
		try {
			mutant = tester.isMutant(dna.get("dna"));

			Test test = new Test(dna.get("dna"), mutant);
			testRepository.saveAndFlush(test);

			if (mutant) {
				return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>("", HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			error = e.getMessage();
		}
		return new ResponseEntity<String>(error, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/stats/")
	public ResponseEntity<Map<String, Object>> stats() {

		Map<String, Object> json = new LinkedHashMap<>();
		json.put("count_mutant_dna", testRepository.countMutants());
		json.put("count_human_dna", testRepository.countHumans());
		if (testRepository.countHumans() != 0) {
			json.put("ratio", testRepository.countMutants() / testRepository.countHumans());
		} else {
			json.put("ratio", testRepository.countMutants());
		}

		return new ResponseEntity<Map<String, Object>>(json, HttpStatus.ACCEPTED);
	}

}
