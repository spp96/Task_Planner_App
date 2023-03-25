package com.masai.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.enums.Gender;
import com.masai.enums.RoleOfUser;
import com.masai.exception.TaskException;
import com.masai.exception.UserException;
import com.masai.model.Task;
import com.masai.model.User;
import com.masai.repository.TaskRepository;
import com.masai.repository.UserRepository;
import com.masai.service.UserService;

@Service
public class UserImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TaskRepository taskRepo;
	

	@Override
	public User registerNewUser(User user) throws UserException {
		User existingUser = userRepo.findByMobileNumber(user.getMobileNumber());
		
		if(existingUser != null) {
			throw new UserException("User Already registered with this mobilenumber");
		}
		
		User addUser = userRepo.save(user);
		return addUser;
	}

	@Override
	public User getUserById(Long userId) throws UserException {
		User user = userRepo.findById(userId)
				            .orElseThrow(() -> new UserException("User not found with given Id :-" + userId));
		return user;
	}

	@Override
	public User updateUser(Long userId,String fname,String lname,String email,String mobile,String password,RoleOfUser role,Gender gender) throws UserException {
		  
		User user = userRepo.findById(userId)
	            .orElseThrow(() -> new UserException("User not found with given Id :-" + userId));
	
	 		Optional<User> newUser = userRepo.findById(userId);

	 		User newUser1 = newUser.get();

	 		if (fname != null) {
	 			newUser1.setFirstName(fname);;
	 		}
	 		if (lname != null) {
	 			newUser1.setLastName(lname);;
	 		}
	 		if (email != null) {
	 			newUser1.setEmail(email);
	 		}
	 		if (password != null) {
	 			newUser1.setPassword(password);
	 		}
	 		if(role != null) {
	 			newUser1.setRole(role);
	 		}
	 		if(gender != null) {
	 			newUser1.setGender(gender);
	 		}

	 		User updated = userRepo.save(newUser1);

	 		return updated;
	}

	@Override
	public User deleteUser(Long userId) throws UserException {
		User user = userRepo.findById(userId)
	            .orElseThrow(() -> new UserException("User not found with given Id :-" + userId));
		
		userRepo.delete(user);
		return user;
	}

	@Override
	public String assignTaskToUser(Long userId, Long taskId) throws UserException, TaskException{
		User fUser = userRepo.findById(userId).orElseThrow(() -> new UserException("User not found with given Id :" + userId));
		
		
		Task fTask = taskRepo.findById(taskId).orElseThrow(() -> new TaskException("Task not added with given Id :" + taskId));
		
		if(fTask.getUser() != null) {
			throw new UserException("task already assign to user with given Id :" + userId);
		} else {
			fTask.setUser(fUser);
			fUser.getTasksList().add(fTask);
			taskRepo.save(fTask);
			userRepo.save(fUser);
			return "Task " + taskId + ":- Assigned to user with userId " + userId;	
		}
	}

}
