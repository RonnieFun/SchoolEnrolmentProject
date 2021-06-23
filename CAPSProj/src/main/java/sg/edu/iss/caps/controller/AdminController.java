package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.iss.caps.service.AdminInterface;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminInterface adservice;
	
	@Autowired
	public void setAdminInterface(AdminInterface ads) {
		this.adservice = ads;
	}

}
