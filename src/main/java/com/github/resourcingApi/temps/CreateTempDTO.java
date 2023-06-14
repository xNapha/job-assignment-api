package com.github.resourcingApi.temps;

import java.util.Set;

import com.github.resourcingApi.jobs.Job;

public class CreateTempDTO {

	private String firstName;

	private String lastName;

	private Set<Job> job;

	public CreateTempDTO(String firstName, String lastName, Set<Job> job) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.job = job;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Job> getJob() {
		return job;
	}

	public void setJob(Set<Job> job) {
		this.job = job;
	}

}
