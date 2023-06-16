package com.github.resourcingApi.jobs;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {
	@Query("SELECT x FROM Job x WHERE x.temp IS NULL")
	List<Job> findByJobIsNull();

	@Query("SELECT x FROM Job x WHERE x.temp IS NOT NULL")
	List<Job> findByJobIsNotNull();
}
