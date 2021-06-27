package sg.edu.iss.caps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class AdminImplementation implements AdminInterface {
	@Autowired
	private CoursesRepository crepo;
	@Autowired
	private StudentCourseDetailsRepository scdrepo;
	@Autowired
	private UsersRepository urepo;

	@Override
	public List<Courses> getCourses() {
		return crepo.findAll();
	}

	@Override
	public Courses getCourseById(long courseID) {
		return crepo.findById(courseID).get();
	}

	@Override
	public void deleteCourse(Courses course) {
		crepo.delete(course);
	}

	@Override
	public void savecourse(Courses course) {
		crepo.save(course);
		}

	@Override
	public List<Long> getCoursesWithStudents()
	{
		return scdrepo.getCourseIDsWithStudents();
	}
	
	@Override
	public void saveuser(Users user) {
		urepo.save(user);
	}
}
