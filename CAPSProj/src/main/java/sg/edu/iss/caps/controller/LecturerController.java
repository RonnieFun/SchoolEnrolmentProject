package sg.edu.iss.caps.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.LecturerInterface;
import sg.edu.iss.caps.service.MyUserDetails;

@Controller
@RequestMapping("/")
@PreAuthorize("hasRole('ROLE_LECTURER')")
public class LecturerController {

	@Autowired
	LecturerInterface lectservice;
	
	@Autowired
	public void setLecturerInterface(LecturerInterface ls) {
		this.lectservice =ls;
	}
	
	
	@GetMapping(value = "/lecturer/coursestaught")
	public String showLecturerCoursesById(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
		
		if(userDetails == null) {
			return "redirect:/login";	
		}
		
		long userID = userDetails.getUserID();
		
		model.addAttribute("coursestaught", lectservice.getAllCoursesByRoleAndId(Roles.LECTURER, userID));
		
		return "lecturer/coursestaught";
	}
	
	@GetMapping(value = "lecturer/enrolment")
	public String showCoursesByCourseNameCourseStart(Model model, String courseName,
			@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate courseStartDate, Roles role, EnrolmentStatus enrolmentStatus, 
			@AuthenticationPrincipal MyUserDetails userDetails) {
		
		if(userDetails == null) {
			return "redirect:/login";	
		}
	
		long lecturerID = userDetails.getUserID();
		
		if (courseName != null && courseStartDate != null) {
			model.addAttribute("studentCourseDetails", lectservice.getAllUsersByRoleCourseNameStartDate(
					Roles.STUDENT, 
					EnrolmentStatus.ACCEPTED, 
					courseName, 
					courseStartDate));
			
			model.addAttribute("selectedCourseName", lectservice.findCoursebyCourseNameStartDateCourseID(courseName, courseStartDate));
		} 		
		model.addAttribute("coursestaught", lectservice.getAllCoursesByLecturerId(lecturerID));
		
		return "lecturer/enrolment";
	}
		
	
	@GetMapping(value = "/lecturer/viewstudentgrades")
	public String showStudentGrades(Roles role, Long userID, Model model, 
			@AuthenticationPrincipal MyUserDetails userDetails) {
		 
		if(userDetails == null) {
			return "redirect:/login";	
		}
		
		if(userID != null) {
			
			model.addAttribute("studentCourseDetails", lectservice.getGradesByStudentId(userID, Roles.STUDENT));
			model.addAttribute("users", lectservice.getAllUsersByUserID(userID));
		} 
		
		int totalCredits = 0;
		double cgpa = 0;
		double sum = 0.0;
		
		Map<String, Double> gradepointsmap = new HashMap<String, Double>();
		gradepointsmap.put("A+", 5.0);
		gradepointsmap.put("A", 5.0);
		gradepointsmap.put("A-", 4.5);
		gradepointsmap.put("B+", 4.0);
		gradepointsmap.put("B", 3.5);
		gradepointsmap.put("B-", 3.0);
		gradepointsmap.put("C+", 2.5);
		gradepointsmap.put("C", 2.0);
		gradepointsmap.put("D+", 1.5);
		gradepointsmap.put("D", 1.0);
		gradepointsmap.put("F", 0.0);

		List<StudentCourseDetails> studentSelected = lectservice.getGradesByStudentId(userID, Roles.STUDENT);
			
		for(StudentCourseDetails e: studentSelected)
		{
			String grades = e.getGrades();
			if (e.getGrades() == null || grades.trim().isEmpty())
			{
				sum += 0;
				totalCredits += 0;
				
			}	else {				
				sum +=  gradepointsmap.get(e.getGrades())  *  e.getCourse().getCredits();
				totalCredits += e.getCourse().getCredits();
			}
		}	

		if (totalCredits != 0) {
			cgpa = sum / totalCredits;
			cgpa = Math.round(cgpa * 100.0) / 100.0;
		}
		
		model.addAttribute("totalCredits", totalCredits); 		
		model.addAttribute("cgpa", cgpa);  
		
		return "lecturer/viewstudentgrades";
	}	

	@RequestMapping("/lecturer/gradecourse")
	public String gradeCourse(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {

		List<Courses> coursesTaught = lectservice.getAllCoursesByLecturerId(userDetails.getUserID());
		model.addAttribute("coursestaught", coursesTaught);
		return "lecturer/gradecourse";
	}

	@RequestMapping("/lecturer/gradecourse2")
	public String gradeCertainCourse(Model model, HttpServletRequest request, @RequestParam String courseID, @AuthenticationPrincipal MyUserDetails userDetails) {

		List<Courses> coursesTaught = lectservice.getAllCoursesByLecturerId(userDetails.getUserID());
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
