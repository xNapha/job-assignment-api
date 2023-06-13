package com.github.resourcingApi.temps;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temps")
public class TempController {
	@Autowired
	private TempService service;

	@PostMapping
	public ResponseEntity<Temp> create(@RequestBody CreateTempDTO data) {
		Temp newTemp = this.service.create(data);
		return new ResponseEntity<Temp>(newTemp, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Temp>> findAll() {
		List<Temp> allTemps = this.service.findAll();

		return new ResponseEntity<List<Temp>>(allTemps, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Temp> findById(@PathVariable Long id) {
		Optional<Temp> maybeTemp = this.service.findById(id);

		return new ResponseEntity<Temp>(Optional.of(maybeTemp.get()).orElseThrow(), HttpStatus.OK);
	}

//	@GetMapping("?jobId={jobId}")
//	public ResponseEntity<List<Temp>> findAllAvailableTemps(){
//		
//	}
}
