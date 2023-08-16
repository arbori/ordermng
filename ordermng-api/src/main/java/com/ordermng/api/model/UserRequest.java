package com.ordermng.api.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-23T12:06:14.117462+01:00[Europe/Lisbon]")

public class UserRequest {
	@JsonProperty("email")
	private String email;

	@JsonProperty("name")
	private String name;

	public UserRequest email(String email) {
		this.email = email;
		return this;
	}

	/**
	 * Get code
	 * 
	 * @return code
	 **/
	@Schema(example = "mymail@serv.com", description = "")

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRequest name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Get code
	 * 
	 * @return code
	 **/
	@Schema(example = "John Snow", description = "")

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
