package com.app.entities;

import lombok.ToString;


public enum UserRole {
	ROLE_USER, ROLE_ADMIN;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name().toLowerCase();
	}
}
