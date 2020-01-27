package com.labforward.api.greeting.dto;

import java.util.Locale;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.labforward.api.core.validation.EntityUpdateValidatorGroup;

public class GreetingDTO {
	
	public String id;

	@NotEmpty(groups = {EntityUpdateValidatorGroup.class})
	public String message;

	@NotNull
	public Locale language;

	public GreetingDTO() {
		// needed for JSON deserialization
	}

	public GreetingDTO(String id, String message, Locale language) {
		this.id = id;
		this.message = message;
		this.language = language;
	}

	public GreetingDTO(String message, Locale language) {
		this.message = message;
		this.language = language;
	}
}
