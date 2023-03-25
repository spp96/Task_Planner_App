package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.exception.SprintException;
import com.masai.model.*;

public interface SprintService {

	public Sprint createSprint(Sprint sprint) throws SprintException;
	
	public List<Task> getAllTasksInSprint(Long sprintId) throws SprintException;
	
	public Sprint getSprintById(Long sprintId) throws SprintException;
	
	public Sprint updateSprint(Long sprintId, String sprintName, String description, LocalDate startDate, LocalDate endDate) throws SprintException;
	
	public Sprint deleteSprintById (Long sprintId) throws SprintException;
}
