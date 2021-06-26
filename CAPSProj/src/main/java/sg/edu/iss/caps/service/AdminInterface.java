package sg.edu.iss.caps.service;

import java.util.List;

import sg.edu.iss.caps.model.Courses;

public interface AdminInterface {

	List<Courses> getCourses();
	Courses getCourseById(long courseID);
	void deleteCourse(Courses course);
}
