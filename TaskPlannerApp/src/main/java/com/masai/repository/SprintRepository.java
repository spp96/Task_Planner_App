package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long>{

	public Sprint findBySprintName(String sprintName);
}
