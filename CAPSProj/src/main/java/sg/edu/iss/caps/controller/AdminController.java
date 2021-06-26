package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Courses;
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

	@RequestMapping("/courselist")
	public String courseList(Model model) {
		model.addAttribute("courses", adservice.getCourses());
		return "admin-courselist";
	}
	
	@RequestMapping("/deletecourse/{courseID}")
	public String deleteCourse(@PathVariable("courseID") long courseID)
	{
		adservice.deleteCourse(adservice.getCourseById(courseID));
		return "forward:/admin/courselist";
	}
	
	@RequestMapping("/editcourse/{courseID}")
	public String editCourse(Model model, @PathVariable("courseID") long courseID)
	{
		model.addAttribute("courses", adservice.getCourseById(courseID));
		return "admin-courseform";
	}
}