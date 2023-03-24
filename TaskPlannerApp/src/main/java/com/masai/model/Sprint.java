package com.masai.model;

import java.time.*;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sprintId;
	
	private String sprintName;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	@OneToMany(mappedBy = "sprint")
	private List<Task> tasksList;
	
}
