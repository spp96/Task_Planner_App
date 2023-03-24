package com.masai.model;

import com.masai.enums.StatusOfTask;
import com.masai.enums.Types;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long taskId;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Types type;
	
	private String assignTo;
	
	@Enumerated(EnumType.STRING)
	private StatusOfTask status;
	
	@ManyToOne
	private Sprint sprint;
	
	@ManyToOne
	@JoinColumn(name = "user_Id")
	private User user;

}
