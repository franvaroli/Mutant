package com.mutant.mutant;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("testRepository")
public interface TestRepository extends JpaRepository<Test, Serializable> {
	
	@Query("SELECT COUNT(a) FROM Test a WHERE a.isMutant = TRUE")
	public int countMutants();
	
	@Query("SELECT COUNT(a) FROM Test a WHERE a.isMutant = FALSE")
	public int countHumans();

}
