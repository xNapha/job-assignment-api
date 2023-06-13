package com.github.resourcingApi.temps;

import java.util.List;
import java.util.Optional;

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

	public Temp create(CreateTempDTO data) {
		Temp tempRequest = modelMapper.map(data, Temp.class);
		return this.repository.save(tempRequest);
	}

	public List<Temp> findAll() {
		return this.repository.findAll();
	}

	public Optional<Temp> findById(Long id) {
		Optional<Temp> maybeTemp = this.repository.findById(id);

		if (maybeTemp.isPresent()) {
			Temp temp = modelMapper.map(maybeTemp, Temp.class);
			temp.setJob(null);
		}
		return null;
	}
}
