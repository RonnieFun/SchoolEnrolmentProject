package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.service.LecturerInterface;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {
	
	@Autowired
	LecturerInterface lectservice;
	
	@Autowired
	public void setLecturerInterface(LecturerInterface ls) {
		this.lectservice =ls;
	}

}
