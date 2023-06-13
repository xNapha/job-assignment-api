package com.github.resourcingApi.temps;

import java.util.Set;

import com.github.resourcingApi.jobs.Job;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "temps")
public class Temp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "temp_id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToMany(mappedBy = "temp")
	private Set<Job> job;

	public Temp() {

	}

	public Set<Job> getJob() {
		return job;
	}

	public void setJob(Job data) {
		this.job.add(data);
	}

}
