package com.github.resourcingApi.jobs;

import java.util.Date;

public class UpdateJobDTO {

	String name;

	Date startDate;

	Date endDate;

	Long temp_id;

	public UpdateJobDTO(String name, Date startDate, Date endDate, Long temp_id) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.temp_id = temp_id;
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

	public Long getTemp_id() {
		return temp_id;
	}

	public void setTemp_id(Long temp_id) {
		this.temp_id = temp_id;
	}

}
