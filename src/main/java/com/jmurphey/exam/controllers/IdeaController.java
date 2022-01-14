package com.jmurphey.exam.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jmurphey.exam.models.Idea;
import com.jmurphey.exam.models.User;
import com.jmurphey.exam.services.IdeaService;
import com.jmurphey.exam.services.UserService;

@Controller
@RequestMapping("/ideas")
public class IdeaController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IdeaService ideaService;
	
	@Autowired
	private UserController userController;
	
	
	
// --- Create a New Idea ---
	
	@GetMapping("/new")
	public String newIdea(@ModelAttribute("idea")Idea idea, HttpSession session, Model model) {
		Long userId = userController.userInSession(session);
		if(userId == null) {
			return "redirect:/";
		} else {
			User user = userService.findUserById(userId);
			model.addAttribute("user", user);
			return "newIdea.jsp";
		}
	}
	
	@PostMapping("/new")
	public String createIdea(@Valid @ModelAttribute("idea")Idea idea, BindingResult result) {
		if(result.hasErrors()) {
			return "newIdea.jsp";
		} else {
			ideaService.createIdea(idea);
			return "redirect:/dashboard";
		}
	}
	

// --- Update/Edit Idea ---
	
	@GetMapping("/edit/{id}")
	public String editIdead(@PathVariable("id")Long id, Model model, HttpSession session) {
		Long userId = userController.userInSession(session);
		Idea idea = ideaService.getIdeaById(id);
		if(userId == null) {
			return "redirect:/";
		} else if (userService.findUserById(userId) != idea.getCreator()) {
			return "redirect:/dashboard";
		} else {
			model.addAttribute("user", userService.findUserById(userId));
			model.addAttribute("idea", ideaService.getIdeaById(id));
			return "editIdea.jsp";
		}
	}
	
	@PutMapping("/{id}")
	public String update(@Valid @ModelAttribute("idea")Idea idea, BindingResult result, RedirectAttributes redirect) {
		if(result.hasErrors()) {
			redirect.addFlashAttribute("error", "Input Field Cannot Be Blank");
			return "redirect:/ideas/edit/" + idea.getId();
		} else {
			ideaService.updateIdea(idea);
			return "redirect:/dashboard";
		}
	}
	
	
// ---  View Idea ---
	
	@GetMapping("/show/{id}")
	public String showIdea(@PathVariable("id")Long id, Model model, HttpSession session) {
		Long userId = userController.userInSession(session);
		if(userId == null) {
			return "redirect:/";
		} else {
			User user = userService.findUserById(userId);
			model.addAttribute("user", user);
			model.addAttribute("idea", ideaService.getIdeaById(id));
			return "showIdea.jsp";
		}
	}
	
	
// --- Likes Relationship ---
	
	@GetMapping("{id}/like/{liker}")
	public String addLikerToIdea(@PathVariable("id")Long id, @PathVariable("liker")Long likerId, HttpSession session) {
		Long userId = userController.userInSession(session);
		Idea idea = ideaService.getIdeaById(id);
		if(userId == null) {
			return "redirect:/";
		} else {
			User likerUser = userService.findUserById(userId);
			ideaService.addToUsersLiked(likerUser, idea);
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("{id}/unlike/{liker}")
	public String removeLiker(@PathVariable("id")Long id, @PathVariable("liker")Long likerId, HttpSession session) {
		Long userId = userController.userInSession(session);
		Idea idea = ideaService.getIdeaById(id);
		if(userId == null) {
			return "redirect:/";
		} else {
			User likerUser = userService.findUserById(userId);
			ideaService.removeUserLike(likerUser, idea);
			return "redirect:/dashboard";
		}
	}
	
	
// --- Delete Idea ---
	
	@DeleteMapping("/delete/{id}")
	public String deleteIdea(@PathVariable("id")Long id) {
		ideaService.deleteIdea(id);
		return "redirect:/dashboard";
	}
}
