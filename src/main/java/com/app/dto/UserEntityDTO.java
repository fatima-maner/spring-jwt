package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.app.entities.UserRole;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString(exclude ="roles")
public class UserEntityDTO {

	private Long userId;
	@NotBlank(message="user name must be supplied")
	private String userName;
	@NotBlank(message="email name must be supplied")
	@Email(message="invalid email format")
	private String email;
	@NotBlank(message="password must be supplied")
	private String password;
	// many-to-many , User *--->* Role
	//@NotEmpty(message="at least 1 role should be choosen")
	private Set<UserRole> roles = new HashSet<>();

}
