package com.labforward.api.greeting.mapper;

import com.labforward.api.greeting.domain.Greeting;
import com.labforward.api.greeting.dto.GreetingDTO;

public class GreetingMapper {

	public static Greeting toEntity(GreetingDTO dto) {
		return new Greeting(dto.message, dto.language);
	}

	public static GreetingDTO toDTO(Greeting entity) {
		return new GreetingDTO(entity.getId(), entity.getMessage(), entity.getLanguage());
	}
}
