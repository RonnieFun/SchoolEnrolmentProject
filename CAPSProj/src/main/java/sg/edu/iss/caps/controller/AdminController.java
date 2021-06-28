package sg.edu.iss.caps.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Courses;
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

	//display course list
	@RequestMapping("/courselist")
	public String courseList(Model model) {
		model.addAttribute("courses", adservice.getCourses());
		return "admin/courselist";
	}

	//delete course with validations on whether course still has students/lecturers
	@RequestMapping("/deletecourse/{courseID}")
	public String deleteCourse(@PathVariable("courseID") long courseID, Model model) {
		if (adservice.getCoursesWithStudents().contains(courseID)) {
			model.addAttribute("ErrorMessage", "Course " + courseID
					+ " still has students associated with it. Please remove all students still associated with the course before deletion.");
			return "forward:/admin/courselist";
		} else if (adservice.getCourseById(courseID).getUsers().size() != 0) {
			model.addAttribute("ErrorMessage", "Course " + courseID
					+ " still has lecturers associated with it. Please remove all lecturers still associated with the course before deletion.");
			return "forward:/admin/courselist";
		} else {
			model.addAttribute("SuccessMessage", "Course " + courseID + " was deleted successfully.");
			adservice.deleteCourse(adservice.getCourseById(courseID));
			return "forward:/admin/courselist";
		}
	}
	
	//edit course
	@RequestMapping("/editcourse/{courseID}")
	public String editCourse(@PathVariable("courseID") long courseID, Model model) {
		List<Users> lecturers = leservice.getAllUsersByRole(Roles.LECTURER);
		model.addAttribute("course", adservice.getCourseById(courseID));
		model.addAttribute("lecturers", lecturers);
		model.addAttribute("statusupcoming", CourseStatus.UPCOMING);
		model.addAttribute("statusongoing", CourseStatus.ONGOING);
		model.addAttribute("statuscompleted", CourseStatus.COMPLETED);
		return "admin/courseform";
	}

	//add course
	@RequestMapping("/addcourse")
	public String addCourse(Model model) {
		Courses course = new Courses();
		List<Users> lecturers = leservice.getAllUsersByRole(Roles.LECTURER);
		model.addAttribute("course", course);
		model.addAttribute("lecturers", lecturers);
		model.addAttribute("statusupcoming", CourseStatus.UPCOMING);
		model.addAttribute("statusongoing", CourseStatus.ONGOING);
		model.addAttribute("statuscompleted", CourseStatus.COMPLETED);
		return "admin/courseform";
	}
	
	//save course
	@RequestMapping("/savecourse")
	public String saveCourse(@Valid @ModelAttribute("course") Courses course, BindingResult bindingResult,
			Model model) {
		Courses course1 = new Courses();
		List<Users> lecturers = leservice.getAllUsersByRole(Roles.LECTURER);
		model.addAttribute("course", course);
		model.addAttribute("lecturers", lecturers);
		model.addAttribute("statusupcoming", CourseStatus.UPCOMING);
		model.addAttribute("statusongoing", CourseStatus.ONGOING);
		model.addAttribute("statuscompleted", CourseStatus.COMPLETED);

		// check if submission has any class annotation validation errors
		if (bindingResult.hasErrors()) {
			return "admin/courseform";
		}

		// validate if submission's start date is before end date
		if (course.getCourseStartDate() != null && course.getCourseEndDate() != null) {
			if (course.getCourseStartDate().compareTo(course.getCourseEndDate()) > 0) {
				model.addAttribute("enddateerror", "End date should not be before start date.");
				return "admin/courseform";
			}
		}

		// validate if submission's start date is before exam date
		if (course.getCourseStartDate() != null && course.getExamDate() != null) {
			if (course.getCourseStartDate().compareTo(course.getExamDate()) > 0) {
				model.addAttribute("examdateerror", "Exam date should not be before start date.");
				return "admin/courseform";
			}
		}

		// validate if submission's start date and end date are populated for automatic
		// course status
		if (course.getCourseStatus() == null) {
			if (course.getCourseStartDate() == null || course.getCourseEndDate() == null) {
				model.addAttribute("datemissingerror",
						"Course Start Date and Course End Date need to be filled in for automatic detection of course status.");
				return "admin/courseform";
			} else {
				if (course.getCourseStartDate().compareTo(LocalDate.now()) > 0) {
					course.setCourseStatus(CourseStatus.UPCOMING);
				} else if (course.getCourseStartDate().compareTo(LocalDate.now()) <= 0
						&& course.getCourseEndDate().compareTo(LocalDate.now()) >= 0) {
					course.setCourseStatus(CourseStatus.ONGOING);
				} else if (course.getCourseEndDate().compareTo(LocalDate.now()) < 0) {
					course.setCourseStatus(CourseStatus.COMPLETED);
				}
			}
		}

		// save course + save course to selected lecturers
		if (adservice.findCourseById(course.getCourseID()).isPresent()) {
			course1 = adservice.getCourseById(course.getCourseID());
		}
		if (course1.getUsers() != null) {
			for (Users a : course1.getUsers()) {
				leservice.removeCourseTaught(a.getUserID(), course1);
				adservice.saveuser(a);
			}
		}
		adservice.savecourse(course);
		if (course.getUsers() != null) {
			for (Users a : course.getUsers()) {
				leservice.addCourseTaught(a.getUserID(), course);
				adservice.saveuser(a);
			}
		}
		return "forward:/admin/courselist";
	}
}