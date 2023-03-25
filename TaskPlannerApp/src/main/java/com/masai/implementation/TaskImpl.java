package com.masai.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.enums.StatusOfTask;
import com.masai.enums.Types;
import com.masai.exception.TaskException;
import com.masai.exception.UserException;
import com.masai.model.Task;
import com.masai.model.User;
import com.masai.repository.TaskRepository;
import com.masai.repository.UserRepository;
import com.masai.service.TaskService;

@Service
public class TaskImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Task addTask(Task task) throws TaskException {
		return taskRepo.save(task);
	}

	@Override
	public Task changeAssign(Long taskId, String newAssign)throws TaskException  {
		Task task = taskRepo.findById(taskId).get();
		task.setAssignTo(newAssign);
		
		return taskRepo.save(task);
	}

	@Override
	public Task getTaskById(Long taskId) throws TaskException {
		Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new TaskException("Task not added with given Id :-" + taskId));
		return task;
	}

	@Override
	public Task updateTask(Long taskId, String description, Types types, String assingTo, StatusOfTask status)
			throws TaskException {
		Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new TaskException("Task not added with given Id :-" + taskId));
	 		
	 		
	 		Optional<Task> newtask = taskRepo.findById(taskId);

	 		Task newTask1 = newtask.get();

	 		if (description != null) {
	 			newTask1.setDescription(description);
	 		}
	 		if (types != null) {
	 			newTask1.setType(types);
	 		}
	 		if (assingTo != null) {
	 			newTask1.setAssignTo(assingTo);
	 		}
	 		if (status != null) {
	 			newTask1.setStatus(status);
	 		}

	 		Task updated = taskRepo.save(newTask1);

	 		return updated;
	}

	@Override
	public Task deleteTaskById(Long taskId) throws TaskException {
		Task task = taskRepo.findById(taskId)
	            .orElseThrow(() -> new TaskException("Task not added with given Id :-" + taskId));
		
		taskRepo.delete(task);
		return task;
	}

	@Override
	public List<Task> getAllTasksAssignToUser(Long userId) throws UserException {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserException("User not found with given id :"+userId));
		return user.getTasksList();	
	}
}
