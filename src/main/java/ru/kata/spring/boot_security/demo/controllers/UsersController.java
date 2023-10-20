package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.security.Principal;
import java.util.List;

//В этом классе  у нас прописаны методы с для переходов на вьющки при переходе
//на адреса указанные в гет\пост запросах
@Controller
@RequestMapping("/user")
public class UsersController {
	private final UserService userService;

	@Autowired
	public UsersController (UserService userService) {
		this.userService = userService;
	}

	/*Principal нужен для того, чтобы мы могли отобразить данные
	 мы можем вызвать принципал из любого места кода, так как он есть в контексте(в текущей сессии пользователя)
	 Полагаю, что модель тоже
	*/
    @GetMapping("/user_profile")
	public String showUserAcc(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute("user", user);
		model.addAttribute("roles", user.getAuthorities()); //
		return "user_profile";
	}
}