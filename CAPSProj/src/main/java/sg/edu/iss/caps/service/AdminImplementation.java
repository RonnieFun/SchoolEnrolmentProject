package sg.edu.iss.caps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.repo.CoursesRepository;

@Service
public class AdminImplementation implements AdminInterface{
	@Autowired
	private CoursesRepository crepo;

	@Override
	public List<Courses> getCourses() {
		return crepo.findAll();
	}
	
	public Courses getCourseById(long courseID) {
		return crepo.findById(courseID).get();
	}
	
	public void deleteCourse(Courses course) {
		crepo.delete(course);
	}
}
