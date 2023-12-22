package com.example.demo.dto;




import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String password;

}
