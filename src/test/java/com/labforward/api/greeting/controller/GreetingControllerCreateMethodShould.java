package com.labforward.api.greeting.controller;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.labforward.api.common.MVCIntegrationTest;
import com.labforward.api.greeting.dto.GreetingDTO;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingControllerCreateMethodShould extends MVCIntegrationTest {

	private static final String HELLO_LUKE = "Hello Luke";

	@Test
	public void returnUnprocessableRequest_when_MessageMissing() throws Exception {
		String body = "{\"language\": \"en\"}";
		mockMvc.perform(post("/hello").content(body)
		                              .contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().isUnprocessableEntity())
		       .andExpect(jsonPath("$.validationErrors", hasSize(1)))
		       .andExpect(jsonPath("$.validationErrors[*].field", contains("message")));
	}

	@Test
	public void returnBadRequest_when_messageEmptyString() throws Exception {
		GreetingDTO emptyMessage = new GreetingDTO("", Locale.CHINESE);
		final String body = getGreetingBody(emptyMessage);

		mockMvc.perform(post("/hello").content(body)
		                              .contentType(MediaType.APPLICATION_JSON))
		       .andExpect(status().isUnprocessableEntity())
		       .andExpect(jsonPath("$.validationErrors", hasSize(1)))
		       .andExpect(jsonPath("$.validationErrors[*].field", contains("message")));
	}

	@Test
	public void returnStatusCreated_when_requiredGreetingProvided() throws Exception {
		GreetingDTO greeting = new GreetingDTO(HELLO_LUKE, Locale.ENGLISH);
		final String body = getGreetingBody(greeting);

		mockMvc.perform(post("/hello").contentType(MediaType.APPLICATION_JSON)
		                              .content(body))
		       .andExpect(status().isCreated())
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
