package sg.edu.iss.caps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;
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


}
