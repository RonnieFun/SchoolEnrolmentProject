package sg.edu.iss.caps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.LecturerCourseDetails;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.AdminInterface;
import sg.edu.iss.caps.service.LecturerInterface;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminInterface adservice;
	@Autowired
	LecturerInterface leservice;

//	@Autowired
//	public void setAdminInterface(AdminInterface ads) {
//		this.adservice = ads;
//	}

	@RequestMapping("/courselist")
	public String courseList(Model model) {
		model.addAttribute("courses", adservice.getCourses());
		return "admin/courselist";
	}

	@RequestMapping("/deletecourse/{courseID}")
	public String deleteCourse(@PathVariable("courseID") long courseID) {
		adservice.deleteCourse(adservice.getCourseById(courseID));
		return "forward:/admin/courselist";
	}

	@RequestMapping("/editcourse/{courseID}")
	public String editCourse(Model model, @PathVariable("courseID") long courseID) {
		model.addAttribute("courses", adservice.getCourseById(courseID));
		return "admin/editcourse";
	}

	@RequestMapping("/addcourse")
	public String addCourse(Model model) {
		Courses course = new Courses();
		List<Users> lecturers = leservice.getAllUsersByRole(Roles.LECTURER);
		model.addAttribute("course", course);
		model.addAttribute("lecturers", lecturers);
		return "admin/addcourse";
	}

	@RequestMapping("/savecourse")
	public String saveCourse(@ModelAttribute("course") Courses course, @ModelAttribute("lecturerCourseDetails") LecturerCourseDetails lecturerCourseDetails, Model model) {
		adservice.savecourse(course);
		adservice.saveLecturerCourseDetails(lecturerCourseDetails);
		return "forward:/admin/courselist";
	}
}