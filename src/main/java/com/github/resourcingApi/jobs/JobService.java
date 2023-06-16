package com.github.resourcingApi.jobs;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
			return this.repository.save(assignTemp(jobRequest, maybeTempId.get()));
		}

		return this.repository.save(jobRequest);

	}

	public List<Job> findAll() {
		return this.repository.findAll();
	}

	public Job assignTemp(Job jobRequest, Long id) {
		Temp currentTemp = tempService.findById(id);
		Set<Job> allAssignedJobs = currentTemp.getJob();
		boolean anyAvailableTime = allAssignedJobs.stream().noneMatch(job -> JobUtility.checkDates(job, jobRequest));

		if (anyAvailableTime)
			jobRequest.setTemp(currentTemp);
		return jobRequest;
	}

	public Job findById(Long id) throws ResponseStatusException {
		return this.repository	.findById(id)
								.orElseThrow(() -> new ResponseStatusException(	HttpStatus.NOT_FOUND,
																				String.format(	"Job with ID of %s, was not found",
																								id)));
	}

	public List<Job> filter(boolean isAssigned) {
		return (isAssigned) ? this.repository.findByJobIsNotNull() : this.repository.findByJobIsNull();
	}

	public Job updateById(Long id, UpdateJobDTO data) {
		Job currentJob = this.findById(id);
		Optional<Long> maybeTempId = data.getTemp();
		if (maybeTempId.isEmpty()) {
			currentJob.setTemp(null);
			return currentJob;
		} else {
			return assignTemp(currentJob, maybeTempId.get());
		}
	}

	public List<Temp> findAllAvailableTemps(Long jobId) {
		Job requestedJob = this.findById(jobId);
		return tempService	.findAll()
							.stream()
							.filter(temp -> filterTemp(temp, requestedJob))
							.collect(Collectors.toList());

	}

	public boolean filterTemp(Temp temp, Job requestedJob) {
		Set<Job> allJobs = temp.getJob();
		return allJobs.stream().noneMatch(currentJob -> JobUtility.checkDates(currentJob, requestedJob));
	}

}
