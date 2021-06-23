package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.HomeInterface;

@Controller
public class HomeController {
	
	@Autowired
	HomeInterface hservice;
	
	@Autowired
	public void setHomeInterface(HomeInterface hs) {
		this.hservice = hs;
	}

	@RequestMapping("/login")
	public String login(Model model) {
		Users u = new Users();
		model.addAttribute("user", u);
		return "login";
	}
}
