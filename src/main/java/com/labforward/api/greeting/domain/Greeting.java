package com.labforward.api.greeting.domain;

import java.util.Locale;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.labforward.api.core.validation.EntityUpdateValidatorGroup;

@Entity
@Table(name = "greetings")
public class Greeting {

	@Id
	@Column(name = "id", nullable = false, unique = true)
	@NotEmpty(groups = { EntityUpdateValidatorGroup.class })
	private String id;

	@Column(name="message", nullable = false)
	@NotEmpty
	private String message;

	@Column(name="language", nullable = false)
	@NotEmpty
	private String language;

	public Greeting(String id, String message, Locale langauge) {
		this.message = message;
		this.id = id;
		this.language = langauge.getLanguage();
	}

	public Greeting(String message, Locale language) {
		this.id = UUID.randomUUID().toString();
		this.message = message;
		this.language = language.toLanguageTag();
	}

	public Greeting() {

	}

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message, Locale language) {
		this.message = message;
		this.language = language.toLanguageTag();
	}

	public Locale getLanguage() {
		return Locale.forLanguageTag(this.language);
	}
}
