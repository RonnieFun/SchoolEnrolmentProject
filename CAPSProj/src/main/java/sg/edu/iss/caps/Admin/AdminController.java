package sg.edu.iss.caps.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Users;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminInterface adservice;
	
	@Autowired
	public void setAdminInterface(AdminInterface ads) {
		this.adservice = ads;
	}
	
	@RequestMapping(value = "list")
	public String listUser(Model model) {
		List<Users> ulist = adservice.listAllUsers();
		model.addAttribute("ulist", ulist);
		return "Admin";
	}

}
