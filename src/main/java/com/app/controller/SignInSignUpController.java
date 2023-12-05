package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.servlet.AuthorizeRequestsDsl;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.UserEntityDTO;
import com.app.jwt_utils.JwtUtils;
import com.app.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class SignInSignUpController {
//dep:JWT utils:for generating JWT
	@Autowired
	private JwtUtils utils;
	//dep:Auth mgr
	@Autowired
	private AuthenticationManager manager;
	//add a method to authenticate user in case of success--send back err msg
  //user service for handling users
	@Autowired
	private IUserService userService;
	@PostMapping("/signin")
   public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request)
   {
	   //store incming user details (not yet validated)into authenticate object
	   //Authentication i/f -->impl by UserNamePasswordAuthToken
	   UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
       log.info("auth token"+authToken);
       try {
       //autheticate the credential
       Authentication authenticatedDetails=manager.authenticate(authToken);
       //->auth success
       return ResponseEntity.ok(new AuthResp("Auth successful",utils.generateJwtToken(authenticatedDetails)));
       }
       catch(BadCredentialsException e) {
       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
       }
       
   }
//add request handing method for user registration
   @PostMapping("/signup")
   public ResponseEntity<?> userRegistration(@RequestBody @Valid UserEntityDTO user)
   {
	  System.out.println("in reg user:user{}"+user+"roles"+user.getRoles());
	   return ResponseEntity.status(HttpStatus.CREATED) .body(userService.registerUser(user));	  	    
   }
}
