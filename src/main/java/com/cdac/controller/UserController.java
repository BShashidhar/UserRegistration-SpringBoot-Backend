package com.cdac.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.UserDTO;
import com.cdac.entity.User;
import com.cdac.service.UserService;

@RestController
@RequestMapping(value="/v1/auth")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserService userService;
	
	@GetMapping("/hi")	
	public String Hello() {
		return "Spring Boot JWT App...";
	}

	@GetMapping("/users")
	public List<UserDTO> getAllUsers() {
		return userService.listAllUser().stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserDTO> getUsersById(@PathVariable(value = "id") Long id) {
		User user = userService.getUserById(id);
		UserDTO userRes=modelMapper.map(user,UserDTO.class);
		return ResponseEntity.ok().body(userRes);
	}

	@PostMapping("users/new")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		User userReq = modelMapper.map(userDTO, User.class);
		User user = userService.saveUser(userReq);
		UserDTO userRes = modelMapper.map(user, UserDTO.class);
		return new ResponseEntity<UserDTO>(userRes, HttpStatus.CREATED);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
		User userReq = modelMapper.map(userDTO, User.class);
		User user = userService.updateUser(id, userReq);
		UserDTO userRes = modelMapper.map(user, UserDTO.class);
		return ResponseEntity.ok().body(userRes);
	}

	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable Long id) {
		userService.deleteUser(id);
	}
}
