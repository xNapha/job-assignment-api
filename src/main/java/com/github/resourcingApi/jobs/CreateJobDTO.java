package com.github.resourcingApi.jobs;

import java.util.Date;
import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

public class CreateJobDTO {

	@NotBlank
	String name;

	Date startDate;

	Date endDate;

	Optional<Long> temp;

	public CreateJobDTO() {

	}

	public CreateJobDTO(String name, Date startDate, Date endDate, Optional<Long> temp) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.temp = temp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Optional<Long> getTemp() {
		return temp;
	}

	public void setTemp_id(Optional<Long> temp) {
		this.temp = temp;
	}

}
