package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
