package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.enums.Gender;
import com.masai.enums.RoleOfUser;
import com.masai.exception.TaskException;
import com.masai.exception.UserException;
import com.masai.model.User;
import com.masai.service.UserService;


@RestController
@RequestMapping("/TaskPlanner/User")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUserHandler(@RequestBody User user) throws UserException{
		
		User user1 =  userService.registerNewUser(user);
		
		return new ResponseEntity<User>(user1,HttpStatus.CREATED);
	}
	
	@GetMapping("/getbyId/{userId}")
	public ResponseEntity<User> getUserByIdHandler(@PathVariable("userId") Long userId) throws UserException{
		
		User user1 =  userService.getUserById(userId);
		
		return new ResponseEntity<User>(user1,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletebyId/{userId}")
	public ResponseEntity<User> deleteUserByIdHandler(@PathVariable("userId") Long userId) throws UserException{
		
		User user1 =  userService.deleteUser(userId);
		
		return new ResponseEntity<User>(user1,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update_user")
	public ResponseEntity<User> updateUserHandler(@RequestParam Long userId,@RequestParam String fname,
			@RequestParam String lname,@RequestParam String email,@RequestParam String mobile,@RequestParam String password,@RequestParam RoleOfUser role,@RequestParam Gender gender) throws UserException {

		User cust = userService.updateUser(userId, fname, lname, email, mobile, password, role, gender);

		return new ResponseEntity<User>(cust, HttpStatus.FOUND);
	}
	
	@PostMapping("/assignTask/{userId}/{taskId}")
	public ResponseEntity<String> assignTaskToUserHandler(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) throws UserException, TaskException {
		
		String result =  userService.assignTaskToUser(userId, taskId);
		
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
}
