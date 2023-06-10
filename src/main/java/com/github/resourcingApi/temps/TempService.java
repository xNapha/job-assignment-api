package com.github.resourcingApi.temps;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TempService {
	@Autowired
	private TempRepository repository;

	@Autowired
	private ModelMapper modelMapper;
}
