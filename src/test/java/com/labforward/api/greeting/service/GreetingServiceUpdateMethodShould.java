package com.labforward.api.greeting.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.labforward.api.core.exception.ResourceNotFoundException;
import com.labforward.api.greeting.constants.Constants;
import com.labforward.api.greeting.domain.Greeting;
import com.labforward.api.greeting.repository.GreetingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingServiceUpdateMethodShould {

	@Autowired
	private GreetingService greetingService;

	@Autowired
	private GreetingRepository greetingRepository;

	@Test(expected = IllegalArgumentException.class)
	public void throwIlligalArgumentException_when_idIsNull() {
		greetingService.updateGreeting(null, new Greeting("new message", Constants.DEFAUL_LOCALE));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void throwResourceNotFoundException_when_greetingDoesNotExist() {
		greetingService.updateGreeting(UUID.randomUUID().toString(), new Greeting("new message", Locale.CHINESE));
	}

	@Test
	public void returnUpdatedGreeting_when_greetingIsCorrect() {
		String greetingId = "test1";
		greetingRepository.save(new Greeting(greetingId, "message", Constants.DEFAUL_LOCALE));

		String message = "message updated";
		Optional<Greeting> greeting = greetingService.updateGreeting(greetingId,
				new Greeting(greetingId, message, Constants.DEFAUL_LOCALE));

		assertTrue(greeting.isPresent());
		assertEquals(message, greeting.get().getMessage());
	}
}
