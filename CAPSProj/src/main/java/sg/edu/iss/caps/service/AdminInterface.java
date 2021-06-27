package sg.edu.iss.caps.service;

import java.util.List;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.LecturerCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface AdminInterface {

	List<Courses> getCourses();
	Courses getCourseById(long courseID);
	void deleteCourse(Courses course);
	void savecourse(Courses course);
	List<Long> getCoursesWithStudents();
	void saveuser(Users user);
}
