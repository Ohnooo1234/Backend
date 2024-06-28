package com.vti.dto;

import org.springframework.hateoas.RepresentationModel;

import com.vti.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {
	
	private String userName;
	
	private String email;

	private String password;

	private String firstName;

	private String lastName;
	
	private int phoneNumber;

	public User toEntity() {
		return new User(userName, email, password, firstName, lastName, phoneNumber);
	}

}