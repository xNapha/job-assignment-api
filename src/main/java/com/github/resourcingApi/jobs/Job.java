package com.github.resourcingApi.jobs;

import java.util.Date;

import com.github.resourcingApi.temps.Temp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	@Column(name = "name")
	String name;
	@Column(name = "start_date")
	Date stateDate;
	@Column(name = "end_date")
	Date endDate;
	@Column(name = "temp_id")
	Temp temp_id;
}
