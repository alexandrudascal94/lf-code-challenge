package com.labforward.api.greeting.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;



import com.labforward.api.common.MVCIntegrationTest;
import com.labforward.api.greeting.domain.Greeting;
import com.labforward.api.greeting.dto.GreetingDTO;
import com.labforward.api.greeting.repository.GreetingRepository;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingControllerUpdateMethodShould extends MVCIntegrationTest {
	private static final String HELLO_LUKE = "Hello Luke";

	@Autowired
	private GreetingRepository repository;

	@Before
	public void init() {
		repository.save(new Greeting("TEST_ID", "Test Greeting", Locale.ENGLISH));
	}

	@Test
	public void returnNotFound_when_IdDoesNotExist() throws Exception {
		GreetingDTO message = new GreetingDTO("message test", Locale.CHINESE);
		final String body = getGreetingBody(message);

		mockMvc.perform(put("/hello/12").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void returnBadRequest_when_GreetingIsNotCorrect() throws Exception {
		GreetingDTO emptyMessage = new GreetingDTO("", null);
		final String body = getGreetingBody(emptyMessage);

		mockMvc.perform(put("/hello/TEST_ID").content(body).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void returnStatusAccepted_when_requiredGreetingProvided() throws Exception {
		GreetingDTO greeting = new GreetingDTO(HELLO_LUKE, Locale.ENGLISH);
		final String body = getGreetingBody(greeting);

		mockMvc.perform(put("/hello/TEST_ID").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isAccepted())
				.andExpect(jsonPath("$.message", is(greeting.message)));
	}

	private String getGreetingBody(GreetingDTO greeting) throws JSONException {
		JSONObject json = new JSONObject().put("message", greeting.message);
		json.put("language", greeting.language);
		if (greeting.id != null) {
			json.put("id", greeting.id);
		}

		return json.toString();
	}

}
