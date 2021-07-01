package sg.edu.iss.caps.controller;




import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.service.StudentInterface;

@RestController
@RequestMapping("/studentapi")
@PreAuthorize("hasRole('ROLE_STUDENT')")
public class StudentRestController {

	@Autowired
	StudentInterface stuservice;
	
	
	@Autowired
	public void setStudentInterface(StudentInterface stus) {
		this.stuservice =stus;	
	}
	
	// find all courses that already assigned to lecturers
	@GetMapping("/lecturercourses")
	public List<?> getAllLectuererCourses()
	{
		return stuservice.findCoursesByRole(Roles.LECTURER);
				
	}
	

		
	
}
