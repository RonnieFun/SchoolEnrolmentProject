package sg.edu.iss.caps.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.LecturerCourseDetails;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.LecturerCourseDetailsRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class LecturerImplementation implements LecturerInterface {

	@Autowired
	CoursesRepository coursesRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	LecturerCourseDetailsRepository lecturerCourseDetailsRepository;
	
	@Autowired
	StudentCourseDetailsRepository studentCourseDetailsRepository;

	@Transactional
	public List<Courses> getAllCourses() {
		
		return coursesRepository.findAll();
	}

	@Transactional
	public Courses getCoursesById(Long id) {
		
		return coursesRepository.findById(id).get();
	}

	@Transactional
	public Users getUsersById(Long id) {
		
		return usersRepository.findById(id).get();
	}

	@Transactional
	public List<Users> getAllUsers() {

		return usersRepository.findAll();
	}


}
