package com.masai.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.masai.exception.SprintException;
import com.masai.exception.TaskException;
import com.masai.model.Sprint;
import com.masai.model.Task;
import com.masai.repository.SprintRepository;
import com.masai.repository.TaskRepository;
import com.masai.service.SprintService;

@Service
public class SprintImpl implements SprintService{
	
	@Autowired
	private SprintRepository sprintRepo;
	
	@Autowired
	private TaskRepository taskRepo;

	@Override
	public Sprint createSprint(Sprint sprint) throws SprintException {
		Sprint existingSprint = sprintRepo.findBySprintName(sprint.getSprintName());
		
		if(existingSprint != null) {
			throw new SprintException("Sprint Already created with this sprintname :" + sprint.getSprintName());
		}
		
		Sprint addSprint = sprintRepo.save(sprint);
		return addSprint;
	}

	@Override
	public List<Task> getAllTasksInSprint(Long sprintId) throws SprintException {
		return sprintRepo.findById(sprintId).get().getTasksList();
	}

	@Override
	public Sprint getSprintById(Long sprintId) throws SprintException {
		Sprint sprint = sprintRepo.findById(sprintId)
	            .orElseThrow(() -> new SprintException("Sprint not found with given Id :-" + sprintId));
		return sprint;
	}

	@Override
	public Sprint updateSprint(Long sprintId, String sprintName, String description, LocalDate startDate,
			LocalDate endDate) throws SprintException {
		Sprint sprint = sprintRepo.findById(sprintId)
	            .orElseThrow(() -> new SprintException("Sprint not found with given Id :-" + sprintId));
	 		
	 		
	 		Optional<Sprint> newSprint = sprintRepo.findById(sprintId);

	 		Sprint newSprint1 = newSprint.get();

	 		if (sprintName != null) {
	 			newSprint1.setSprintName(sprintName);
	 		}
	 		if (description != null) {
	 			newSprint1.setDescription(description);
	 		}
	 		if (startDate != null) {
	 			newSprint1.setStartDate(startDate);
	 		}
	 		if (endDate != null) {
	 			newSprint1.setEndDate(endDate);
	 		}

	 		Sprint updated = sprintRepo.save(newSprint1);

	 		return updated;
	}

	@Override
	public Sprint deleteSprintById(Long sprintId) throws SprintException {
		Sprint sprint = sprintRepo.findById(sprintId)
	            .orElseThrow(() -> new SprintException("Sprint not found with given Id :-" + sprintId));
		
		sprintRepo.delete(sprint);
		return sprint;
	}

	@Override
	public String addTaskToSprint(Long taskId, Long sprintId) throws SprintException, TaskException {
				
		Task fTask = taskRepo.findById(taskId).orElseThrow(() -> new TaskException("Task not added with given Id :" + taskId));
		
		Sprint fSprint = sprintRepo.findById(sprintId).orElseThrow(() -> new SprintException("Sprint not found with given Id :" + sprintId));
		
		if(fTask.getSprint() != null) {
			throw new SprintException("task already added in given sprint :" + sprintId);
		} else {
			fTask.setSprint(fSprint);
			fSprint.getTasksList().add(fTask);
			sprintRepo.save(fSprint);
			taskRepo.save(fTask);
			return "Task " + taskId + ":- added to spring having sprint " + sprintId;	
		}
	}

}
