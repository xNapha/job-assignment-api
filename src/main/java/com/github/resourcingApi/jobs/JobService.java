package com.github.resourcingApi.jobs;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.resourcingApi.temps.Temp;
import com.github.resourcingApi.temps.TempService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobService {
	@Autowired
	private JobRepository repository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private TempService tempService;

	public Job create(CreateJobDTO data) {

		Job jobRequest = modelMapper.map(data, Job.class);
		Optional<Long> maybeTempId = data.getTemp();
		if (maybeTempId.isPresent()) {
			Temp currentTemp = tempService.findById(maybeTempId.get());
			jobRequest.setTemp(currentTemp);
		}

		return this.repository.save(jobRequest);

	}

	public List<Job> findAll() {
		return this.repository.findAll();
	}

	public Job findById(Long id) throws ResponseStatusException {
		return this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("Job with ID of %s, was not found", id)));
	}

	public List<Job> filter(boolean isAssigned) {
		return (isAssigned) ? this.repository.findByJobIsNotNull() : this.repository.findByJobIsNull();
	}

	public Job updateById(Long id, UpdateJobDTO data) {
		Job currentJob = this.findById(id);
		Optional<Long> maybeTempId = data.getTemp();
		if (maybeTempId.isEmpty()) {
			currentJob.setTemp(null);
		} else {
			Temp currentTemp = tempService.findById(maybeTempId.get());
			currentJob.setTemp(currentTemp);
		}
		return currentJob;
	}

}
