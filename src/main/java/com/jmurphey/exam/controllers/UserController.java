package com.jmurphey.exam.controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jmurphey.exam.models.Idea;
import com.jmurphey.exam.models.User;
import com.jmurphey.exam.services.IdeaService;
import com.jmurphey.exam.services.UserService;
import com.jmurphey.exam.validators.UserValidator;

@Controller
@RequestMapping("/")
public class UserController {

// --- Autowires ---
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IdeaService ideaService;
	
	@Autowired
	private UserValidator validator;
	
	
// --- Method To Check/Get User in Session ---
	
	public Long userInSession(HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return null;
		} else {
			return (Long)session.getAttribute("userId");
		}
	}
	
	
// --- Login and Registration Methods ---
	
	@GetMapping("")
	public String index(@ModelAttribute("user")User user) {
		return "loginRegistration.jsp";
	}
	
	@PostMapping("")
	public String register(@Valid @ModelAttribute("user")User user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "loginRegistration.jsp";
		} else {
			User newUser = userService.registerUser(user);
			session.setAttribute("userId", newUser.getId());
			return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email")String email, @RequestParam("password")String password, HttpSession session, RedirectAttributes redirect) {
		if(userService.authenticateUser(email, password)) {
			User user = userService.findByEmail(email);
			session.setAttribute("userId", user.getId());
			return "redirect:/dashboard";
		} else {
			redirect.addFlashAttribute("error", "Invalid Email or Password");
			return "redirect:/";
		}
	}
	
	
// --- Below Routes Require a User be logged-in ---
	
	@GetMapping("/dashboard")
	public String dash(HttpSession session, Model model) {
		Long userId = userInSession(session);
		if(userId == null) {
			return "redirect:/";
		} else {
			User user = userService.findUserById(userId);
			model.addAttribute("user", user);
			model.addAttribute("ideas", ideaService.allIdeas());
			return "dashboard.jsp";
		}
	}
	
	public Idea getAllForIdea(Long id) {
		return ideaService.getIdeaCreatorAndVistorCount(id);
	}
	
	
	// Rework functionality of this in Repo/Service as the Query is wrong. 
	
//	@GetMapping("/dashboardhigh")
//	public String dashHigh(HttpSession session, Model model) {
//		Long userId = userInSession(session);
//		if(userId == null) {
//			return "redirect:/";
//		} else {
//			User user = userService.findUserById(userId);
//			model.addAttribute("user", user);
//			model.addAttribute("ideas", ideaService.allIdeas());
//			return "dashboardhigh.jsp";
//		}
//	}

	
	
// --- Logout User ---
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

