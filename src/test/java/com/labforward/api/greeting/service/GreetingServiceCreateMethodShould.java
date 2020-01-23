package com.labforward.api.greeting.service;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.labforward.api.core.exception.EntityValidationException;
import com.labforward.api.greeting.domain.Greeting;
import com.labforward.api.greeting.service.GreetingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingServiceCreateMethodShould {

	@Autowired
	private GreetingService greetingService;

	private final Locale testLocale = Locale.ENGLISH;

	@Test(expected = EntityValidationException.class)
	public void throwEntityValidatioException_when_greetingWithEmptyMessage() {
		final String EMPTY_MESSAGE = "";
		greetingService.createGreeting(new Greeting(EMPTY_MESSAGE, testLocale));
	}

	@Test(expected = EntityValidationException.class)
	public void throwEntityValidationException_when_greetingWithNullMessage() {
		greetingService.createGreeting(new Greeting(null, testLocale));
	}

	@Test
	public void haveCorrectMessage_and_language_when_greetingIsValid() {
		final String HELLO_LUKE = "Hello Luke";
		final Locale language = Locale.FRANCE;

		Greeting request = new Greeting(HELLO_LUKE, language);

		Greeting created = greetingService.createGreeting(request).get();
		Assert.assertEquals(HELLO_LUKE, created.getMessage());
		Assert.assertEquals(language, created.getLanguage());
	}
}
