package sg.edu.iss.caps.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;


public interface LecturerInterface {

	//To get list of all courses
	List<Courses> getAllCourses();
	
	//To get list of all courses by Lecturer's Id
	List<Courses> getAllCoursesByLecturerId(Roles role, Long id);
		
	//To get list of all users
	List<Users> getAllUsers();

	//To get list of users by Role
	List<Users> getAllUsersByRole(Roles role);
	
	//To get list of users by Role, Course Name and Course Start
	List<Users> getAllUsersByRoleCourseNameStartDate(Roles role, EnrolmentStatus enrolmentStatus, String courseName, 
			LocalDate courseStartDate);
	
	
	// COMMENT BY MAX: KIV the below method. Please do not delete for now.
	//For search function on course enrolments html page and pagination:
//	List<Courses> getByCourseNameCourseStart(String courseName, LocalDate courseStartDate);

}
