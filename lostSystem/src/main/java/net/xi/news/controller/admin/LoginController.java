package net.xi.news.controller.admin;


import net.xi.news.pojo.User;
import net.xi.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String loginPage() {
		return "admin/login";
	}

	@PostMapping("/login")
	//登录页面
	public String login(@RequestParam String username,
	                    @RequestParam String password,
	                    HttpSession session,
	                    RedirectAttributes attributes,
	                    Model model) {
		User user = userService.checkUser(username, password);

		if (user != null) {
			user.setPassword(null);
			session.setAttribute("user", user);
			model.addAttribute("user", username);
			return "admin/index";
		} else {
			attributes.addFlashAttribute("msg", "用户名或密码错误");
			return "redirect:/admin";
		}
	}


	@GetMapping("/loggout")
	//登出
	public String loggout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
}
