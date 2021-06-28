package sg.edu.iss.caps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;
import sg.edu.iss.caps.repo.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class AdminImplementation implements AdminInterface{

	@Autowired
	StudentCourseDetailsRepository studentCourseDetailsRepository;
	
	@Autowired
	UsersRepository urepo;
	
	@Autowired
	CoursesRepository crepo;
	
	@Override
	public List<StudentCourseDetails> getAllEnrolment() {
		// TODO Auto-generated method stub
		return studentCourseDetailsRepository.findAll();
	}
	@Override
	public StudentCourseDetails getEnrolment(Long id) {
		// TODO Auto-generated method stub
		return studentCourseDetailsRepository.findById(id).get();
	}
	@Override
	public void deleteEnrolment(StudentCourseDetails enrolment) {
		// TODO Auto-generated method stub
		studentCourseDetailsRepository.delete(enrolment);;
	}
	@Override
	public List<Users> getStudentList() {
		// TODO Auto-generated method stub
		return urepo.findByRole(Roles.STUDENT);
	}
	@Override
	public void updateEnrolment(StudentCourseDetails enrolment) {
		// TODO Auto-generated method stub
		studentCourseDetailsRepository.save(enrolment);
	}
	@Override
	public List<Courses> getCourseList() {
		// TODO Auto-generated method stub
		return crepo.findAll();
	}
	@Override
	public void saveEnrolment(StudentCourseDetails enrolment) {
		// TODO Auto-generated method stub
		studentCourseDetailsRepository.save(enrolment);
	}
	
	@Transactional
	public void createUser(Users user) {
		// TODO Auto-generated method stub
		urepo.save(user);
		
	}

	@Transactional
	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		urepo.save(user);
		
	}

	@Transactional
	public List<Users> listByRole(Roles role) {
		// TODO Auto-generated method stub
		return urepo.findByRole(role);
	}

	@Transactional
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		urepo.deleteById(id);		
	}
	
	@Transactional
	public Users findById(Long ID) {
		return urepo.findById(ID).get();
	}
	
	@Transactional
	public Users findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Page<Users> listAllUsers(int pageNumber, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
		return urepo.findAll(pageable);
	}
	
	@Transactional
	public Page<Users> listRoleUsers(int pageNumber, String sortField, String sortDir, Roles role) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
		return urepo.findByRole(role,pageable);
	}
	
	@Transactional
	public List<Users> listUsers(){
		return urepo.findAll();
	}
	
	@Transactional
	public String passwordGenerator() {
		int rand = (int) (10000 + (Math.random() * 99999));
		String pw = Integer.toString(rand);
		return pw;
	}



}
