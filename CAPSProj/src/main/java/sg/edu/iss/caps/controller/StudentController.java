package sg.edu.iss.caps.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.service.StudentInterface;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentInterface stuservice;
	
	@Autowired
	public void setStudentInterface(StudentInterface stus) {
		this.stuservice =stus;	
	}
	
	// find all courses that already assigned to lecturers
	
	public String getAllLectuererCourses(Model model)
	{
		model.addAttribute("lecturercourses", stuservice.findCoursesByRole(Roles.LECTURER));
		return "lecturercourses";
	}
	
	
}
