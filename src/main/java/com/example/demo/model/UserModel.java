package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	
	private String fullname;
	@JsonProperty("email")
	private String email;

}
