package com.github.resourcingApi.jobs;

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
}
