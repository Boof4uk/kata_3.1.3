package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.util.List;

//В этом классе  у нас прописаны методы с для переходов на вьющки при переходе
//на адреса указанные в гет\пост запросах
@Controller
public class UsersController {
	private final UserServiceImp userServiceImp;
	private final UserDetailsService userDetailsService;
	//Для того, чтобы мы могли взаимодействовать с моделями, нам нужно заинжектить UserServiceImp
	//контроллер - юзерсервис - репозиторий - модель - бд.
	//
	@Autowired
	public UsersController (UserDetailsService userDetailsService, UserServiceImp userServiceImp) {
		this.userDetailsService = userDetailsService;
		this.userServiceImp = userServiceImp;

	}

	//Домашняя страница
	@GetMapping("/")
	public ModelAndView home() {
		List<User> users = userServiceImp.findAll();
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("users", users);
		return mav;
	}

	//Гет запрос, для того чтобы отобразить страницу с полями для нового пользователя
	//Доступна админу
	@GetMapping("/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "/new";
	}

	//Пост запрос, чтобы отправить данные с полей через нажатие кнопки
	//Доступна админу
	@PostMapping("/new")
	public String create(@ModelAttribute("user") User user) {
		userServiceImp.saveUser(user);
		return "redirect:/";
	}

	//Гетзапрос для получения страницы с полями конкретного пользователя
	//Доступна админу
	@GetMapping("/{id}/edit")
	public String editUser(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userServiceImp.findUserById(id));
		return "/edit";
	}

	//Частичный пост запрос для того, чтобы обновить данные со страницы гетзапроса обновления юзера
	@PatchMapping("/{id}")
	public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
		userServiceImp.updateUser(user, id);
		return "redirect:/admin";
	}

	//Гет запрос(???) для удаления пользователя
	//Доступна админу
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		userServiceImp.deleteUser(id);
		return "redirect:/admin";
	}

	//Гет запрос для получения таблицы с кнопками для пост запросов
	//Доступна админу
	@GetMapping("/admin")
	public String controlUsers() {
		return "adminPanel";
	}

	//При этом гет запросе должна выводиться информация об авторизованном юзере, который выполняет этот запрос
	@GetMapping("/user") //todo заинжектить principal. Там где нужно получать информацию о текущ юзере
	public String showUser() {
		return "/user";
	}
}