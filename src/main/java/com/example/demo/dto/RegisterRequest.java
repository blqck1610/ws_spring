package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String matchingPassword;
	@NotBlank
	private String email;
	@NotBlank
	private String fullName;

}
