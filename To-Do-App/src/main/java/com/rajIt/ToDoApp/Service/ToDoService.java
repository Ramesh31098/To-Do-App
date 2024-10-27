package com.rajIt.ToDoApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajIt.ToDoApp.model.ToDo;
import com.rajIt.ToDoApp.repository.IToDoRepository;
@Service
public class ToDoService {
	 @Autowired
	 private IToDoRepository doRepository;
public List<ToDo> getAllToDoItem() {
	List<ToDo> todoList = new ArrayList<>();
	doRepository.findAll().forEach(t->todoList.add(t));
	return todoList;
}
public ToDo getToDoItemById(long id) {
	Optional<ToDo> findById = doRepository.findById(id);
	
	return findById.get();
	
}
 public boolean updateStatus(Long id) {
	 ToDo toDoItemById = getToDoItemById(id);
	 toDoItemById.setStatus("completed");
	 return saveOrUpdateToDoItem(toDoItemById);
 }
  public boolean saveOrUpdateToDoItem( ToDo toDo) {
	   ToDo updateToDoItem = doRepository.save(toDo);
	    if(getToDoItemById(updateToDoItem.getId())!=null) {
	    	return true;
	    	
	    }return false;
	  
  }
  public boolean deletetodoItem(long id) {
	  doRepository.deleteById(id);
	   if(getToDoItemById(id)== null) {
		   return true;
		   
	   }
	return false;
}
  
}
