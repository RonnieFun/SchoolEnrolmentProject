package sg.edu.iss.caps.service;

import java.util.List;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Users;

public interface LecturerInterface {

	List<Courses> getAllCourses();
	
	Courses getCoursesById(Long id);
	
	List<Users> getAllUsers();
	
	Users getUsersById(Long id);
}
