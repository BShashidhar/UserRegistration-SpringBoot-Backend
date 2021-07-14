package com.cdac.dto;

import com.cdac.entity.Gender;
import com.cdac.entity.Role;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDTO {

	private Long id;
	
	private String title;

	private String firstName;

	private String lastName;
	
	private Role role;

	private Gender gender;

	private String emailId;

    private String password;

}
