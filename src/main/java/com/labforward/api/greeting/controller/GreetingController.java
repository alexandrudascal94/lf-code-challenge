package com.labforward.api.greeting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.labforward.api.core.exception.ResourceNotCreatedException;
import com.labforward.api.core.exception.ResourceNotFoundException;
import com.labforward.api.core.exception.ResourceNotUpdatedException;
import com.labforward.api.greeting.domain.Greeting;
import com.labforward.api.greeting.dto.GreetingDTO;
import com.labforward.api.greeting.mapper.GreetingMapper;
import com.labforward.api.greeting.service.GreetingService;

@RestController
public class GreetingController {

	public static final String GREETING_NOT_FOUND = "Greeting Not Found";
	public static final String GREETING_NOT_CREATED = "Greeting Not Created";
	public static final String GREETING_NOT_UPDATED = "Greeting Not Updated";

	private GreetingService greetingService;

	public GreetingController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@GetMapping(value = "/hello")
	@ResponseBody
	public GreetingDTO getGreeting() {
		Greeting greeting = greetingService.getDefaultGreeting()
				.orElseThrow(() -> new ResourceNotFoundException(GREETING_NOT_FOUND));
		
		return GreetingMapper.toDTO(greeting);
	}

	@GetMapping(value = "/hello/{id}")
	@ResponseBody
	public GreetingDTO getGreeting(@PathVariable String id) {
		Greeting greeting = greetingService.getGreeting(id)
				.orElseThrow(() -> new ResourceNotFoundException(GREETING_NOT_FOUND));
		
		return GreetingMapper.toDTO(greeting);
	}

	@PostMapping(value = "/hello")
	public ResponseEntity<GreetingDTO> createGreeting(@RequestBody GreetingDTO createRequest) {
		Greeting greeting = greetingService.createGreeting(GreetingMapper.toEntity(createRequest))
				.orElseThrow(() -> new ResourceNotCreatedException(GREETING_NOT_CREATED));
		
		return new ResponseEntity<GreetingDTO>(GreetingMapper.toDTO(greeting), HttpStatus.CREATED);
	}

	@PutMapping(value = "/hello/{id}")
	public GreetingDTO updateGreeting(@PathVariable String id, @RequestBody GreetingDTO updateRequest) {
		Greeting greeting = greetingService.updateGreeting(id, GreetingMapper.toEntity(updateRequest))
				.orElseThrow(() -> new ResourceNotUpdatedException(GREETING_NOT_UPDATED));
		
		return GreetingMapper.toDTO(greeting);
	}
}
