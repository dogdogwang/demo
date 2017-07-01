package net.wjd.controller;

import javax.servlet.http.HttpServletRequest;

import net.wjd.model.User;
import net.wjd.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request,Model model){
		String userId = request.getParameter("id");
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "user_detail"; 
	}
}
