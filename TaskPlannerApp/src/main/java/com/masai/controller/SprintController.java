package com.masai.controller;

import java.time.LocalDate;
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

import com.masai.exception.SprintException;
import com.masai.exception.TaskException;
import com.masai.model.*;
import com.masai.service.SprintService;

@RestController
@RequestMapping("/TaskPlanner/Sprints")
public class SprintController {

	@Autowired
	private SprintService sprintService;
	
	@PostMapping("/create")
	public ResponseEntity<Sprint> createSprintHandler(@RequestBody Sprint sprint) throws SprintException {
		
		Sprint createSprint =  sprintService.createSprint(sprint);
		
		return new ResponseEntity<Sprint>(createSprint,HttpStatus.CREATED);
	}
	
	@GetMapping("/getbyId/{sprintId}")
	public ResponseEntity<Sprint> getSprintByIdHandler(@PathVariable("sprintId") Long sprintId) throws SprintException {
		
		Sprint getSprint =  sprintService.getSprintById(sprintId);
		
		return new ResponseEntity<Sprint>(getSprint,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{sprintId}/{tasks}")
	public ResponseEntity<List<Task>> getAllTaskInSprintHandler(@PathVariable("sprintId") Long sprintId) throws SprintException{
		
		return new ResponseEntity<List<Task>>(sprintService.getAllTasksInSprint(sprintId),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deletebyId/{sprintId}")
	public ResponseEntity<Sprint> deleteSprintByIdHandler(@PathVariable("sprintId") Long sprintId) throws SprintException{
		
		Sprint deleteSprint =  sprintService.getSprintById(sprintId);
		
		return new ResponseEntity<Sprint>(deleteSprint,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update_sprint")
	public ResponseEntity<Sprint> updateSprintHandler(@RequestParam Long sprintId,@RequestParam String sprintName,
			@RequestParam String description,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate) throws SprintException {

		Sprint updateSprint = sprintService.updateSprint(sprintId, sprintName, description, startDate, endDate);

		return new ResponseEntity<Sprint>(updateSprint, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addTask/{taskId}/{sprintId}")
	public ResponseEntity<String> addTaskToSprintHandler(@PathVariable("taskId") Long taskId, @PathVariable("sprintId") Long sprintId) throws SprintException, TaskException  {
		
		String result =  sprintService.addTaskToSprint(taskId, sprintId);
		
		return new ResponseEntity<String>(result,HttpStatus.CREATED);
	}
}
