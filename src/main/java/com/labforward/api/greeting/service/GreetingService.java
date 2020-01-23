package com.labforward.api.greeting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labforward.api.core.exception.ResourceNotFoundException;
import com.labforward.api.core.validation.EntityValidator;
import com.labforward.api.greeting.constants.Constants;
import com.labforward.api.greeting.domain.Greeting;
import com.labforward.api.greeting.repository.GreetingRepository;

@Service
public class GreetingService {

	public static final String GREETING_NOT_FOUND = "Greeting Not Found";

	private GreetingRepository greetingRepository;

	private EntityValidator entityValidator;

	@Autowired
	public GreetingService(GreetingRepository greetingRepository, EntityValidator entityValidator) {
		this.entityValidator = entityValidator;
		this.greetingRepository = greetingRepository;
	}

	public Optional<Greeting> createGreeting(Greeting request) {
		entityValidator.validateCreate(request);
		return Optional.ofNullable(greetingRepository.save(request));
	}

	public Optional<Greeting> getGreeting(String id) {
		return greetingRepository.findById(id);
	}

	public Optional<Greeting> getDefaultGreeting() {
		Optional<Greeting> defaultGreeting = greetingRepository.findById(Constants.DEFAULT_ID);

		if (defaultGreeting.isPresent()) {
			return defaultGreeting;
		}
		
		return createGreeting(new Greeting(Constants.DEFAULT_ID, Constants.DEFAULT_MESSAGE, Constants.DEFAUL_LOCALE));
	}

	public Optional<Greeting> updateGreeting(String id, Greeting newGreeting) {
		entityValidator.validateUpdate(id, newGreeting);

		Greeting existingGreeting = greetingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(GREETING_NOT_FOUND));

		existingGreeting.setMessage(newGreeting.getMessage(), newGreeting.getLanguage());
		entityValidator.validateUpdate(id, existingGreeting);

		return Optional.ofNullable(greetingRepository.save(existingGreeting));
	}
}
