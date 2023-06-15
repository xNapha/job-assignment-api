package com.github.resourcingApi.jobs;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
		boolean anyAvailableTime = allAssignedJobs.stream().noneMatch(job -> checkDates(job, jobRequest));

		if (anyAvailableTime)
			jobRequest.setTemp(currentTemp);
		return jobRequest;
	}

	public boolean checkDates(Job currentJob, Job requestedJob) {
		Date cJStart = currentJob.getStartDate();
		Date cJEnd = currentJob.getEndDate();
		Date rJStart = requestedJob.getStartDate();
		Date rJEnd = requestedJob.getEndDate();

		boolean isCJStartWithinRJDate = cJStart.compareTo(rJStart) > 0 && cJStart.compareTo(rJEnd) < 0;

		boolean isCJEndWithinRJDate = cJEnd.compareTo(rJStart) < 0 && cJEnd.compareTo(rJEnd) > 0;

		boolean isCJStartEqualRJStart = cJStart.compareTo(rJStart) == 0;

		boolean isCJEndEqualRJStart = cJEnd.compareTo(rJStart) == 0;

		boolean isCJStartEqualRJEnd = cJStart.compareTo(rJEnd) == 0;

		boolean isCJEndEqualRJEND = cJEnd.compareTo(rJStart) == 0;

		boolean isRJDatesWithinCJDates = cJStart.compareTo(rJStart) < 0 && cJEnd.compareTo(rJEnd) > 0;

		return (isCJStartWithinRJDate) || (isCJEndWithinRJDate) || (isCJStartEqualRJStart) || (isCJEndEqualRJStart)
				|| (isCJStartEqualRJEnd) || (isCJEndEqualRJEND) || (isRJDatesWithinCJDates);
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
			return currentJob;
		} else {
			return assignTemp(currentJob, maybeTempId.get());
		}
	}

}
