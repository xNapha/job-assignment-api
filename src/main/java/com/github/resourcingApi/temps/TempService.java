package com.github.resourcingApi.temps;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TempService {
	@Autowired
	private TempRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	public Temp create(CreateTempDTO data) {
		Temp tempRequest = modelMapper.map(data, Temp.class);
		return this.repository.save(tempRequest);
	}

	public List<Temp> findAll() {
		return this.repository.findAll();
	}

	public Temp findById(Long id) throws ResponseStatusException {
		return this.repository
				.findById(id)
				.orElseThrow(
						() -> new ResponseStatusException(
								HttpStatus.NOT_FOUND,
								String.format("Temp with ID of %s, was not found", id)));
	}

}
