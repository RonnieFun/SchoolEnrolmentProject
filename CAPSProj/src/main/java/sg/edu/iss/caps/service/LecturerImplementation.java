package sg.edu.iss.caps.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class LecturerImplementation implements LecturerInterface {

	@Autowired
	CoursesRepository coursesRepository;

	@Autowired
	UsersRepository usersRepository;

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
	public List<Courses> getAllCoursesByRoleAndId(Roles role, Long userID) {


		return coursesRepository.findCoursesByRoleAndId(role, userID);

		//return coursesRepository.findByLecturerId(id);
		//return null;

	}

	@Transactional
	public List<Courses> getByCourseNameCourseStart(String courseName, LocalDate courseStartDate) {

		return coursesRepository.findByCourseSearch(courseName, courseStartDate);
	}
	
	
	@Transactional
	public List<Courses> findCoursebyCourseName(String courseName) {
		
		return coursesRepository.findCoursebyCourseName(courseName);
	}
	
	@Transactional
	public List<Users> getAllUsersByUserID(Long userID) {
		
		return usersRepository.findByUserID(userID);
	}
	
	@Transactional
	public Users getUsersById(Long id) {

		return usersRepository.findById(id).get();
	}

	@Transactional
	public List<Users> getAllUsers() {

		return usersRepository.findAll();
	}

	@Transactional
	public List<Users> getAllUsersByRole(Roles role) {

		return usersRepository.findByRole(role);
	}

	@Transactional
	public List<Users> findUsersByRoleAndId(Long userID, Roles role) {

		return usersRepository.findUsersByRoleAndId(userID, role);
	}

	@Transactional
	public List<StudentCourseDetails> getGradesByStudentId(Long userID, Roles role) {
		
		return studentCourseDetailsRepository.findGradesByStudentId(userID, role);
	}

	@Transactional
	public void addCourseTaught(Long id, Courses course) {
		Users user = this.getUsersById(id);
		Collection<Courses> userCurrentTaughtCourses = user.getCourses();
		userCurrentTaughtCourses.add(course);
		user.setCourses(userCurrentTaughtCourses);
	}

	@Transactional
	public void removeCourseTaught(Long id, Courses course) {
		Users user = this.getUsersById(id);
		Collection<Courses> userCurrentTaughtCourses = user.getCourses();
		userCurrentTaughtCourses.remove(course);
		user.setCourses(userCurrentTaughtCourses);
	}
	
	@Transactional
	public List<StudentCourseDetails> getAllStudentCourseDetails() {
		
		return studentCourseDetailsRepository.findAll();
	}
	
	@Transactional
	public List<StudentCourseDetails> getAllUsersByRoleCourseNameStartDate(Roles role, EnrolmentStatus enrolmentStatus, String courseName,
			LocalDate courseStartDate) {
		
		Long courseID = coursesRepository.findByCourseNameAndCourseStartDate(courseName, courseStartDate).getCourseID();
		return studentCourseDetailsRepository.findByCourseNameCourseStart(role, enrolmentStatus, courseID, courseStartDate);
	}
	
	@Transactional
	public List<Users> getStudentsByCourseID(Long courseID) {
		return studentCourseDetailsRepository.getStudentsByCourseID(courseID);
	}

	@Transactional
	public List<StudentCourseDetails> getStudentCourseDetailsByCourseID(Long courseID) {
		return studentCourseDetailsRepository.getStudentCourseDetailsByCourseID(courseID);
	}

	@Transactional
	public StudentCourseDetails getStudentCourseDetailsByStudentIDAndCourseID(long studentID, long courseID) {
		return studentCourseDetailsRepository.getStudentCourseDetailsByStudentIDAndCourseID(studentID, courseID);
	}

	@Transactional
	public void saveStudentCourseDetails(StudentCourseDetails studentCourseDetails) {
		studentCourseDetailsRepository.save(studentCourseDetails);

	}

	@Transactional
	public List<Courses> getAllCoursesByLecturerId(Long id) {
		return coursesRepository.findCoursesByLecturerId(id);
	}
}
