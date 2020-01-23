package com.labforward.api.greeting.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.labforward.api.greeting.constants.Constants;
import com.labforward.api.greeting.domain.Greeting;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingServiceGetDefaultGreetingMethodShould {
	
	@Autowired
	private GreetingService greetingService;

	@Test
	public void haveDefaultMessage_id_language() {
		Optional<Greeting> greeting = greetingService.getDefaultGreeting();
		Assert.assertTrue(greeting.isPresent());
		Assert.assertEquals(Constants.DEFAULT_ID, greeting.get().getId());
		Assert.assertEquals(Constants.DEFAULT_MESSAGE, greeting.get().getMessage());
		Assert.assertEquals(Constants.DEFAUL_LOCALE, greeting.get().getLanguage());

	}

}
