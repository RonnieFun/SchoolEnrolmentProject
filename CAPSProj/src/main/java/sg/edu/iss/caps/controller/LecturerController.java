package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Roles;
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

	@GetMapping("/coursestaught")
	public String showCourses(Model model) {
		model.addAttribute("coursestaught", lectservice.getAllCourses());
		
		return "coursestaught";  
	}
	
	@GetMapping("/enrolment")
	public String showEnrolments(Model model) {
		
		model.addAttribute("allEnrolment", lectservice.getAllUsers());
		model.addAttribute("studentEnrolment", lectservice.getAllUsersByRole(Roles.STUDENT));
		model.addAttribute("lecturerEnrolment", lectservice.getAllUsersByRole(Roles.LECTURER));
		
		return "enrolment";
	}
	
}
