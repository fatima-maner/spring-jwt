package com.app.service;

import javax.validation.Valid;

import com.app.dto.UserEntityDTO;
import com.app.dto.UserRegResponse;

public interface IUserService {

	UserRegResponse	registerUser(UserEntityDTO user);

}
