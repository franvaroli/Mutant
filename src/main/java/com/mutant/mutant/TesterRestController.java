package com.mutant.mutant;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/")
	public ResponseEntity<String> mutant(@RequestBody Map<String, String[]> dna) {
		boolean mutant = false;
		String error;
		try {
			mutant = tester.isMutant(dna.get("dna"));
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

}
