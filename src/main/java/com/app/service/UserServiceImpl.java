package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.app.dto.UserEntityDTO;
import com.app.dto.UserRegResponse;
import com.app.entities.UserEntity;
import com.app.repository.UserRepository;
import com.app.repository.RoleRepository;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    //dep:user repo
	@Autowired
	private UserRepository userRepo;
	 //dep:repo repo
	@Autowired
		private RoleRepository roleRepo;
	//mapper
	@Autowired
	private ModelMapper mapper;
	//password enc
	@Autowired
	private PasswordEncoder encoder;
	@Override
	public UserRegResponse registerUser(UserEntityDTO user) {
		//object : 1 rec inserted in users table n insert n recs in link table(user_roles
		//1.map dto-->entity
		UserEntity userEntity=mapper.map(user, UserEntity.class);
		userEntity.getUserRoles().clear();
		//2.iterate over the roles from user dto n map it to role --add them under user entity
		user.getRoles().stream()//stream<UserRole>
		.map(roleEnum->roleRepo.findByRoleName(roleEnum).orElseThrow(()->new RuntimeException("Invalid rol!!")))
		.forEach(role->userEntity.getUserRoles().add(role));
		//3.encode pwd
		
		userEntity.setPassword(encoder.encode(user.getPassword()));
		//4.should i save roles first before saving user dtls?No:already existing in db
		UserEntity persistUser=userRepo.save(userEntity);
		return new UserRegResponse("user registered successfully with id"+persistUser.getUserId());
	}

}
