package com.Springboot.restApi.Payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class CommentDto {
	
	private int id;
	
	@NotEmpty(message="name should not be empty")
	private String name;
	@NotEmpty(message="Email  should not be empty")@Email
	private String email;
	@NotEmpty(message="body should not be empty")
	private String body;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", name=" + name + ", email=" + email + ", body=" + body + "]";
	}

	
	
}
