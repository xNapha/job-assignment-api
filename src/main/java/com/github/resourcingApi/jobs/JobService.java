package com.github.resourcingApi.jobs;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {
	@Autowired
	private JobRepository repository;

	@Autowired
	ModelMapper modelMapper;

	public Job create(CreateJobDTO data) {
		Job jobRequest = modelMapper.map(data, Job.class);
		return this.repository.save(jobRequest);
	}

	public List<Job> findAll() {
		return this.repository.findAll();
	}

	public Optional<Job> findById(Long id) {
		return this.repository.findById(id);
	}

	public List<Job> filter(boolean isAssigned) {
		return (isAssigned) ? this.repository.findByJobIsNotNull() : this.repository.findByJobIsNull();
	}

//	public Optional<Job> updateById(Long id, UpdateJobDTO data) {
//		this.repository.saveAndFlush();
//		return null;
//	}
}
