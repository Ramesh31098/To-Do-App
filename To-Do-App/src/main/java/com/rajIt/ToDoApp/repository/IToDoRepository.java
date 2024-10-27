package com.rajIt.ToDoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajIt.ToDoApp.model.ToDo;

public interface IToDoRepository  extends JpaRepository<ToDo, Long>{

}
