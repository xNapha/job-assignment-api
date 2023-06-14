package com.github.resourcingApi.jobs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	public ResponseEntity<List<Job>> getAllJobs(@RequestParam Optional<Boolean> assigned) {
		if (assigned.isPresent()) {
			List<Job> filteredJobs = this.service.filter(assigned.get());
			return new ResponseEntity<List<Job>>(filteredJobs, HttpStatus.OK);
		} else {
			List<Job> allJobs = this.service.findAll();
			return new ResponseEntity<List<Job>>(allJobs, HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> getJobById(@PathVariable Long id) throws ResponseStatusException {

		return new ResponseEntity<Job>(this.service.findById(id), HttpStatus.OK);

	}

	@PatchMapping("/{id}")
	public ResponseEntity<Job> updateById(@PathVariable Long id, @RequestBody UpdateJobDTO data) {
		Job job = this.service.updateById(id, data);
		return new ResponseEntity<Job>(job, HttpStatus.ACCEPTED);
	}

}
