package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class UsersController {
	private final UserService userService;

	@Autowired
	public UsersController (UserService userService)
	{this.userService = userService;}

	@GetMapping("/")
	public ModelAndView home() {
		List<User> users = userService.getUserTable();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("users", users);
		return mav;
	}

	@GetMapping("/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "/new";
	}

	@PostMapping("/new")
	public String create(@ModelAttribute("user") User user) {
		userService.addUser(user);
		return "redirect:/";
	}

	@GetMapping("/{id}/edit")
	public String editUser(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.findUser(id));
		return "/edit";
	}

	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:/admin";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return "redirect:/admin";
	}

	@GetMapping("/admin")
	public String controlUsers() {
		return "adminPanel";
	}

	@GetMapping("/user")
	public String showUser() {
		return "/user";
	}


}