package sg.edu.iss.caps.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.LecturerInterface;

@Controller
@RequestMapping("/")
public class LecturerController {

	@Autowired
	LecturerInterface lectservice;

	@Autowired
	public void setLecturerInterface(LecturerInterface ls) {
		this.lectservice = ls;
	}

	@GetMapping(value = "/lecturer/coursestaught")
	public String showAllCourses(Model model) {
		model.addAttribute("coursestaught", lectservice.getAllCourses());

		return "lecturer/coursestaught";
	}

	@GetMapping(value = "/lecturer/coursestaught/{id}")
	public String showLecturerCoursesById(@PathVariable Long id, Model model) {

		model.addAttribute("coursestaught", lectservice.getAllCoursesByRoleAndId(Roles.LECTURER, id));

		return "lecturer/coursestaught";
	}

	@GetMapping(value = "lecturer/enrolment")
	public String showCoursesByCourseNameCourseStart(Model model, String courseName,
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate courseStartDate, Roles role, EnrolmentStatus enrolmentStatus) {
		
		if (courseName != null && courseStartDate != null) {
			model.addAttribute("users", lectservice.getAllUsersByRoleCourseNameStartDate(
					Roles.STUDENT, 
					EnrolmentStatus.ACCEPTED, 
					courseName, 
					courseStartDate));
		} 		
		model.addAttribute("coursestaught", lectservice.getAllCourses());
		
		return "lecturer/enrolment";
	}

	@GetMapping(value = "/lecturer/viewstudentgrades")
	public String showStudentGradesByStudentId(Long userID, Roles role, Model model) {

		if (userID != null) {
			model.addAttribute("studentCourseDetails", lectservice.getGradesByStudentId(userID, Roles.STUDENT));
		}

		model.addAttribute("users", lectservice.getAllCourses());
		return "lecturer/viewstudentgrades";
	}

	// COMMENT BY MAX: KIV the below mapping methods. Please do not delete them for now.
	
//	@GetMapping(value = "/lecturer/enrolment")
//	public String showCoursesByCourseNameCourseStart(Model model, String courseName, 
//			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate courseStartDate, Roles role) {
//		
//		if (courseName != null && courseStartDate != null) {
//			model.addAttribute("courses", lectservice.getByCourseNameCourseStart(courseName, courseStartDate));
//		} else {
//			model.addAttribute("courses", lectservice.getAllCourses());
//		}
//		
//		return "lecturer/enrolment";
//	}
	
//	@GetMapping(value = "/lecturer/enrolment/{id}") 
//	public String showEnrolmentsByLecturerId(@PathVariable Long id, Model model) {
//		
//		model.addAttribute("enrolmentByLecturerId", lectservice.getAllCoursesByLecturerId(Roles.LECTURER, id));
//		
//		return "lecturer/enrolment";
//	}
//	
	
//	@GetMapping(value = "/lecturer/enrolment")
//	public String showEnrolments(Model model) {
//		
//		model.addAttribute("allEnrolment", lectservice.getAllUsers());
//		model.addAttribute("studentEnrolment", lectservice.getAllUsersByRole(Roles.STUDENT));
//		model.addAttribute("lecturerEnrolment", lectservice.getAllUsersByRole(Roles.LECTURER));
//		
//		return "lecturer/enrolment";
//	}

	@RequestMapping("/lecturer/gradecourse")
	public String gradeCourse(Model model) {

		// next line is temporary lines pending sessions
		Long a = (long) 2;

		List<Courses> coursesTaught = lectservice.getAllCoursesByLecturerId(a);
		model.addAttribute("coursestaught", coursesTaught);
		return "lecturer/gradecourse";
	}

	@RequestMapping("/lecturer/gradecourse2")
	public String gradeCertainCourse(Model model, HttpServletRequest request, @RequestParam String courseID) {

		// next line is temporary lines pending sessions
		Long a = (long) 2;

		List<Courses> coursesTaught = lectservice.getAllCoursesByLecturerId(a);
		List<Users> studentsTookCourse = lectservice.getStudentsByCourseID(Long.parseLong(courseID));
		List<StudentCourseDetails> studentdetails = lectservice
				.getStudentCourseDetailsByCourseID(Long.parseLong(courseID));
		model.addAttribute("coursestaught", coursesTaught);
		model.addAttribute("studentsTookCourse", studentsTookCourse);
		model.addAttribute("previousselectedcourse", lectservice.getCoursesById(Long.parseLong(courseID)));
		model.addAttribute("studentdetails", studentdetails);
		return "lecturer/gradecourse";
	}

	@RequestMapping("/lecturer/savegrade")
	public String saveGrade(@RequestParam String studentID, @RequestParam String grade, @RequestParam String courseID) {
		StudentCourseDetails scd = lectservice.getStudentCourseDetailsByStudentIDAndCourseID(Long.parseLong(studentID),
				Long.parseLong(courseID));
		scd.setGrades(grade);
		lectservice.saveStudentCourseDetails(scd);
		return "forward:/lecturer/gradecourse2";
	}

}
