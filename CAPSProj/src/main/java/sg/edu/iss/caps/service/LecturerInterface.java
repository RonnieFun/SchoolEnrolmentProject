package sg.edu.iss.caps.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
	
	//For search function on course enrolments html page:
	List<Courses> getByCourseNameCourseStart(String courseName, LocalDate courseStartDate);
	
	//To get list of all users
	List<Users> getAllUsers();

	//To get list of users by Role
	List<Users> getAllUsersByRole(Roles role);
	
}
