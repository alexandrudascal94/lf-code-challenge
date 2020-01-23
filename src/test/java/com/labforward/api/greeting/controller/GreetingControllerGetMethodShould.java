package com.labforward.api.greeting.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.labforward.api.common.MVCIntegrationTest;
import com.labforward.api.greeting.constants.Constants;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingControllerGetMethodShould extends MVCIntegrationTest {

	@Test
	public void returnValidJSON_and_StatusOk_when_greetingExists() throws Exception {
		mockMvc.perform(get("/hello")).andExpect(status().isOk())
				.andExpect(jsonPath("$.message", is(Constants.DEFAULT_MESSAGE)))
				.andExpect(jsonPath("$.language", is(Constants.DEFAUL_LOCALE.toString())));
	}

	@Test
	public void returnNotFound_when_greetingDoesNotExist() throws Exception {
		mockMvc.perform(get("/hello/23")).andExpect(status().isNotFound());
	}
}
