package com.masai.controller;

import java.util.List;

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
import com.masai.enums.StatusOfTask;
import com.masai.enums.Types;
import com.masai.exception.TaskException;
import com.masai.exception.UserException;
import com.masai.model.Task;
import com.masai.service.TaskService;

@RestController
@RequestMapping("/TaskPlanner/Tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@PostMapping("/addTask")
	public ResponseEntity<Task> addTaskHandler(@RequestBody Task task) throws TaskException{
		
		Task addTask =  taskService.addTask(task);
		
		return new ResponseEntity<Task>(addTask,HttpStatus.CREATED);
	}
	
	@PostMapping("/{taskId}/changeAssignee")
	public ResponseEntity<Task> changeAssigneeHandler(@PathVariable("taskId") Long taskId,@RequestParam String newAssignee ) throws TaskException{
		
		Task change =  taskService.changeAssignee(taskId, newAssignee);
		
		return new ResponseEntity<Task>(change,HttpStatus.CREATED);
	}
	
	@GetMapping("/getbyId/{taskId}")
	public ResponseEntity<Task> getTaskByIdHandler(@PathVariable("taskId") Long taskId) throws TaskException{
		
		Task getTask =  taskService.getTaskById(taskId);
		
		return new ResponseEntity<Task>(getTask,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletebyId/{taskId}")
	public ResponseEntity<Task> deleteTaskByIdHandler(@PathVariable("taskId") Long taskId) throws TaskException {
		
		Task getTask =  taskService.deleteTaskById(taskId);
		
		return new ResponseEntity<Task>(getTask,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update_task")
	public ResponseEntity<Task> updateUserHandler(@RequestParam Long taskId,@RequestParam String description,
			@RequestParam Types types,@RequestParam String assingTo,@RequestParam StatusOfTask status) throws TaskException {
		
		Task updateTask = taskService.updateTask(taskId, description, types, assingTo, status);
		return new ResponseEntity<Task>(updateTask, HttpStatus.FOUND);
	}
	
	@GetMapping("/{userId}/tasks")
	public ResponseEntity<List<Task>> getAllTaskInSprintHandler(@PathVariable("userId") Long userId) throws UserException{
		
		return new ResponseEntity<List<Task>>(taskService.getAllTasksAssignToUser(userId),HttpStatus.ACCEPTED);
	}

}
