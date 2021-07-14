
package com.cdac.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cdac.entity.User;
import com.cdac.repo.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	public List<User> listAllUser() {
		return userRepository.findAll();
	}

	public User saveUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}


//	public User registerUser(SignupRequest signupRequest) throws ResourceNotFoundException {
//		User newUser = new User();
//		newUser.setTitle(signupRequest.getTitle());
//		newUser.setFirstName(signupRequest.getFirstName());
//		newUser.setLastName(signupRequest.getLastName());
//		newUser.setRoles(signupRequest.getRoles());
//		newUser.setGender(signupRequest.getGender());
//		newUser.setEmail(signupRequest.getEmail());
//		newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
//		return userRepository.save(newUser);
//	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public User updateUser(Long id, User user) {		
		User updUser = userRepository.getById(id);
		updUser.setTitle(user.getTitle());
		updUser.setFirstName(user.getFirstName());
		updUser.setLastName(user.getLastName());	
		updUser.setRole(user.getRole());
		updUser.setGender(user.getGender());
		updUser.setEmailId(user.getEmailId());
		updUser.setPassword(user.getPassword());
		return userRepository.save(updUser);
	}
}