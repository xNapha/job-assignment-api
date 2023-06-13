package com.github.resourcingApi.jobs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {
	@Autowired
	private JobService service;

	@PostMapping
	public ResponseEntity<Job> createJob(@Valid @RequestBody CreateJobDTO data) {
		Job createdJob = this.service.create(data);
		return new ResponseEntity<Job>(createdJob, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Job>> getAllJobs() {
		List<Job> allJobs = this.service.findAll();
		return new ResponseEntity<List<Job>>(allJobs, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) {
		return new ResponseEntity<Job>(Optional.of(this.service.findById(id).get()).orElseThrow(), HttpStatus.OK);
	}

//	@GetMapping("?assigned={isAssigned}")
//	public ResponseEntity<List<Job>> filterJobByAssignment(@PathVariable boolean isAssigned) {
//		List<Job> filteredJobs = this.service.filter(isAssigned);
//		return new ResponseEntity<List<Job>>(filteredJobs, HttpStatus.OK);
//	}

//	@PatchMapping("/{id}")
//	public ResponseEntity<Job> updateById(@PathVariable Long id) {
//		Optional<Job> maybeJob = this.service.updateById(id);
//	}

}
