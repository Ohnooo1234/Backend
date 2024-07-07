package com.vti.dto;

public class LoginInfoUser {

	private String token;

	private String userName;

	private String email;

	private String firstName;

	private String lastName;
	
	private int phoneNumber;

	private String role;

	private String status;

	public LoginInfoUser(String token, String userName, String email, String firstName, String lastName, int phoneNumber, String role,
			String status) {
		this.token = token;
		this.userName = userName;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public int getphoneNumber() {
		return phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

}