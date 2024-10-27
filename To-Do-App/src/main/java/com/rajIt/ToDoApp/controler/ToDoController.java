package com.rajIt.ToDoApp.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rajIt.ToDoApp.Service.ToDoService;
import com.rajIt.ToDoApp.model.ToDo;

@Controller
public class ToDoController {
	@Autowired
	private ToDoService doService;

	@GetMapping({"/","viewtodoList"})
	public String viewAllToDoItems(Model model,@ModelAttribute("message") String message) {
model.addAttribute("list", doService.getAllToDoItem());
 model.addAttribute("msg",message);
return "ViewToDoList";
	}

	@PostMapping("/updateToDostatus/{id}")
	public String updateToDoStatus(@PathVariable long id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message","updateSuccess");
		 if (doService.updateStatus(id)) {
			return "redirect:/viewTodoList";
		}
		 redirectAttributes.addFlashAttribute("message", "updateFaliour");
 return"redirect:/viewTodoList";
	}

	@GetMapping("/addToDoItem")
	public String addToDoItem(Model model) {
		model.addAttribute(new ToDo());
return "AddToDoItem";
	}

	@PostMapping("/saveToDoItem")
	public String saveToDoItem(ToDo toDo, RedirectAttributes redirectAttributes) {
if (doService.saveOrUpdateToDoItem(toDo)) {
	redirectAttributes.addFlashAttribute("msg"," save success");
	 return "redirect:/addToDoItem";
}
 redirectAttributes.addFlashAttribute("msg", "Save Failure");
 return "redirect:/viewToDoItem";

	}

	@GetMapping("/editToDoItem/{id}")
	public String editTodoItem(@PathVariable long id,Model model) {
 ToDo toDoItemById = doService.getToDoItemById(id);
  model.addAttribute("todo",toDoItemById);
  return "EditToDoItem";
  
	}

	@PostMapping("/editSaveToDoItem")
	public String editSaveToDoItem( ToDo todo, RedirectAttributes redirectAttributes) {
		if (doService.saveOrUpdateToDoItem(  todo)) {
			redirectAttributes.addFlashAttribute("msg"," Edit success");
			 return "redirect:/viewToDoItem";
		}
		 redirectAttributes.addFlashAttribute("msg", "Edit Failure");
		 return "redirect:/viewToDoItem" + todo.getId();

	}
	@GetMapping("/deleteToDoItem/{id}")
	 public String deleteToDoItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		 if(doService.deletetodoItem(id)) {
		 	 redirectAttributes.addFlashAttribute("msg", "delete Success");
			 
			 
		 }
		  redirectAttributes.addFlashAttribute("msg", "Edit Failure");
return  "redirect:/viewToDoItem";
		  
		
	}

}
