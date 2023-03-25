package com.masai.service;

import java.util.List;

import com.masai.enums.StatusOfTask;
import com.masai.enums.Types;
import com.masai.exception.TaskException;
import com.masai.exception.UserException;
import com.masai.model.Task;

public interface TaskService {

	public Task addTask(Task task) throws TaskException;
	
	public Task changeAssignee(Long taskId, String newAssign) throws TaskException ;
	
	public List<Task> getAllTasksAssignToUser(Long userId) throws UserException;
	
	public Task getTaskById(Long taskId) throws TaskException;
	
	public Task updateTask(Long taskId, String description, Types types, String assingTo, StatusOfTask status ) throws TaskException;
	
	public Task deleteTaskById (Long taskId) throws TaskException;
}
