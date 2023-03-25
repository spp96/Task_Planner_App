package com.masai.service;

import com.masai.enums.Gender;
import com.masai.enums.RoleOfUser;
import com.masai.exception.TaskException;
import com.masai.exception.UserException;
import com.masai.model.User;

public interface UserService {
	
	public User registerNewUser(User user) throws UserException;
	
	public User getUserById(Long userId) throws UserException;
	
	public User updateUser(Long userId,String fname,String lname,String email,String mobile,String password,RoleOfUser role,Gender gender) throws UserException;
	
	public User deleteUser(Long userId) throws UserException;
	
	public String assignTaskToUser (Long userId, Long taskId) throws UserException, TaskException;

}
