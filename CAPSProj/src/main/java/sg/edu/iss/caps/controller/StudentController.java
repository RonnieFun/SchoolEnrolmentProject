package sg.edu.iss.caps.controller;



import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.service.EmailSendingInterface;
import sg.edu.iss.caps.service.MyUserDetails;
import sg.edu.iss.caps.service.StudentInterface;

@Controller
@RequestMapping("/student")
@PreAuthorize("hasRole('ROLE_STUDENT')")
public class StudentController {

	@Autowired
	StudentInterface stuservice;
		
	@Autowired
	public void setStudentInterface(StudentInterface stus) {
		this.stuservice =stus;	
	}
	
	@Autowired
	EmailSendingInterface sendEmail;
	
	// find all courses that already assigned to lecturers
	@GetMapping("/availablecourselist")
	public String getAllLectuererCourses(Model model)
	{
		List<Courses>  lecturercourses= stuservice.findCoursesByRole(Roles.LECTURER);
		List<Courses>  availablecourses = new ArrayList<>();
		
		for(Courses c : lecturercourses) {
			if (c.getCourseStartDate().compareTo(LocalDate.now()) > 0 )
				availablecourses.add(c);
		}
		
		
		Map<Long, Integer> currentEnrolmentMap= new HashMap<Long, Integer>();
		
		for(Courses c: availablecourses) {		
			List<StudentCourseDetails>  enrolmentlist = stuservice.findEnrolmentByCourseID(c.getCourseID());
			currentEnrolmentMap.put(c.getCourseID(), enrolmentlist.size());	
		}
				
		model.addAttribute("currentEnrolmentMap", currentEnrolmentMap);	
		model.addAttribute("availablecourses", availablecourses);		
		
		return "/student/availablecourselist";
	}
	
	
	@GetMapping("/addenrolment/{courseid}")
	public String addEnrolment( Model model, @PathVariable long courseid, @AuthenticationPrincipal MyUserDetails userDetails) {		
		
		if(userDetails == null) {
			return "redirect:/login";	
		}
		
		long userid = userDetails.getUserID();

		Courses course = stuservice.findCoursebyCourseID(courseid);
		
		// find enrolment by UserID and CourseID regardless of enrolmentStatus
		List<StudentCourseDetails> enrolmentfound = stuservice.findEnrolmentByUserIDAndCourseID(userid, courseid);
		
		List<Courses>  lecturercourses= stuservice.findCoursesByRole(Roles.LECTURER);		
		
		// If the course capacity is fully occupied, then the student is unable to enroll
		for(Courses c: lecturercourses) {		
			
			List<StudentCourseDetails>  enrolmentlist = stuservice.findEnrolmentByCourseID(c.getCourseID());
			if (enrolmentlist.size() == c.getCourseCapacity())
			{
				model.addAttribute("CapacityFullErrorMsg", "The course capacity is fully occupied, no slot available. ");
				return "forward:/student/lecturercourses";	
			}
		}
		
		// If an enrolment already exists for the userID and CourseID,  then the student is unable to enroll
		if (enrolmentfound.size() != 0)
		{
			model.addAttribute("ExistsErrorMsg", "Enrolment for this course already exists in your enrolment list.");
			return "forward:/student/lecturercourses";	
		}

		// If the  course is already started, then the student is unable to enroll
		else if (course.getCourseStartDate() != null && course.getCourseEndDate() != null) 
		{
			if (course.getCourseStartDate().compareTo(LocalDate.now()) < 0) {
				model.addAttribute("CourseAlreadyStartedErrorMsg", "Course has already started, you can't enrol for it now.");
				return "forward:/student/lecturercourses";	
			}
		}

		StudentCourseDetails enrolment = new StudentCourseDetails();
		enrolment.setCourse( course);
			
			
		enrolment.setStudent(stuservice.findUserByUserID(userid)); 

		enrolment.setDateOfEnrollment(LocalDate.now());
		enrolment.setEnrolmentStatus(EnrolmentStatus.PENDING);		
		stuservice.addEnrolment(enrolment);
		
		sendEmail.sendCourseEnrolmentConfirmation(course, stuservice.findUserByUserID(userid));
			
		return "redirect:/student/enrolmentlist";	
	}
		
	// find all enrolled courses by UserID
	@GetMapping("/enrolmentlist")
	public String showEnrolmentList(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
			
		if(userDetails == null) {
			return "redirect:/login";	
		}
		
		long userid = userDetails.getUserID();
		
		model.addAttribute("enrolmentlist", stuservice.findEnrolmentByUserID(userid));
		
		return "/student/enrolmentlist";
	}
	
	
	// withdraw enrolment
	@GetMapping("/withdrawenrolment/{enrolmentid}")
	public String withdrawEnrolment(Model model, @PathVariable long enrolmentid) {
		
		StudentCourseDetails enrolment = stuservice.findEnrolmentByEnrolmentID(enrolmentid);
		
		if (enrolment.getCourse().getCourseStartDate().compareTo(LocalDate.now()) <= 0)
		{
			model.addAttribute("CourseAlreadyStartedErrorMsg", "Course has already started, you can't withdraw from it now.");
			return "forward:/student/enrolmentlist";	
		}
		else
		{
			enrolment.setEnrolmentStatus(EnrolmentStatus.WITHDRAWN);
			
			stuservice.updateEnrolment(enrolment);
		}
		
		return "redirect:/student/enrolmentlist";
	}
	
	// find all enrolment by UserID and enrolment status = ACCEPTED
	@GetMapping("/gradesandgpa")
	public String showEnrolmentListAccepted1(Model model, @AuthenticationPrincipal MyUserDetails userDetails ) {
		
		if(userDetails == null) {
			return "redirect:/login";	
		}
		
		long userid = userDetails.getUserID();
		//System.out.println(userid);
		double totalCredits = 0;
		double cgpa = 0;
		double sum = 0.0;
		
		Map<String, Double> gradepointsmap= new HashMap<String, Double>();
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
			
		
		List<StudentCourseDetails> enrolmentaccepted = stuservice.findEnrolmentByUserIDAndEnrolmentStatus(userid, EnrolmentStatus.ACCEPTED);
	
		model.addAttribute("enrolmentsaccepted", enrolmentaccepted);		
		for(StudentCourseDetails e: enrolmentaccepted)
		{
			String grades = e.getGrades();	
			if (grades == null || grades.trim().isEmpty())
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
		}
		
		model.addAttribute("totalCredits", totalCredits); 		
		model.addAttribute("cgpa", Math.round(cgpa * 100.0) / 100.0); 
		return "/student/gradesandgpa";
	}
	
	
	// Users.Role=STUDENT?
	// find all enrolment by UserID and enrolment status = ACCEPTED
	@GetMapping("/mycourses")
	public String showEnrolmentListAccepted2(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
		
		if(userDetails == null) {
			return "redirect:/login";	
		}
		
		long userid = userDetails.getUserID();
		
		model.addAttribute("enrolmentsaccepted", stuservice.findEnrolmentByUserIDAndEnrolmentStatus(userid, EnrolmentStatus.ACCEPTED));
		
		return "/student/mycourses";
	}
	
	
		
	
}
