package sg.edu.iss.caps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class LecturerImplementation implements LecturerInterface {

	@Autowired
	CoursesRepository coursesRepository;
	
	@Autowired
	UsersRepository usersRepository;

	@Transactional
	public List<Courses> getAllCourses() {
		
		return coursesRepository.findAll();
	}

	@Transactional
	public Courses getCoursesById(Long id) {
		
		return coursesRepository.findById(id).get();
	}

	@Transactional
	public List<Users> getAllUsers() {
		
		return usersRepository.findAll();
	}

	@Transactional
	public Users getUsersById(Long id) {
		
		return usersRepository.findById(id).get();
	}
}
