package sg.edu.iss.caps.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.service.LecturerInterface;

@Controller
@RequestMapping("/")
public class LecturerController {
	
	@Autowired
	LecturerInterface lectservice;
	
	@Autowired
	public void setLecturerInterface(LecturerInterface ls) {
		this.lectservice =ls;
	}
	
	@GetMapping(value = "/lecturer/coursestaught/{id}")
	public String showCoursesById(@PathVariable Long id, Model model) {
		
		model.addAttribute("coursestaught", lectservice.getAllCoursesByLecturerId(id));
		
		return "lecturer/coursestaught";
	}
	
	@GetMapping(value = "/lecturer/enrolment")
	public String showCoursesByCourseNameCourseStart(Model model, String courseName, 
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate courseStartDate) {
		
		if (courseName != null && courseStartDate != null) {
			model.addAttribute("enrolments", lectservice.getByCourseNameCourseStart(courseName, courseStartDate));
		} else {
			model.addAttribute("enrolments", lectservice.getAllCourses());
		}
		
		return "lecturer/enrolment";
	}
	
	
//	@GetMapping(value = "/lecturer/enrolment")
//	public String showEnrolments(Model model) {
//		
//		model.addAttribute("allEnrolment", lectservice.getAllUsers());
//		model.addAttribute("studentEnrolment", lectservice.getAllUsersByRole(Roles.STUDENT));
//		model.addAttribute("lecturerEnrolment", lectservice.getAllUsersByRole(Roles.LECTURER));
//		
//		return "lecturer/enrolment";
//	}
//	
//	@GetMapping(value = "/lecturer/enrolment/{id}") 
//	public String showEnrolmentsByLecturerId(@PathVariable Long id, Model model) {
//		
//		model.addAttribute("enrolmentByLecturerId", lectservice.getAllCoursesByLecturerId(id));
//		
//		return "lecturer/enrolment";
//	}
	
}
