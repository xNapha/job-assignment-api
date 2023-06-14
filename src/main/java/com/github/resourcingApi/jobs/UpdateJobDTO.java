package com.github.resourcingApi.jobs;

import java.util.Optional;

public class UpdateJobDTO {

	Optional<Long> temp;

	public UpdateJobDTO() {

	}

	public UpdateJobDTO(Optional<Long> temp) {
		super();
		this.temp = temp;
	}

	public Optional<Long> getTemp() {
		return temp;
	}

	public void setTemp(Optional<Long> temp) {
		this.temp = temp;
	}

}
