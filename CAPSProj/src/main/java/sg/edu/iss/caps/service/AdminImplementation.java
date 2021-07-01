package sg.edu.iss.caps.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
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
		return crepo.getById(courseID);
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
	public List<Long> getCoursesWithStudents() {
		return scdrepo.getCourseIDsWithStudents();
	}

	@Override
	public void saveuser(Users user) {
		urepo.save(user);
	}

	@Override
	public Optional<Courses> findCourseById(long courseID) {
		return crepo.findById(courseID);
	}
	
	@Transactional
	public List<StudentCourseDetails> getAllEnrolment() {
		// TODO Auto-generated method stub
		return scdrepo.findAll();
	}

	@Transactional
	public StudentCourseDetails getEnrolment(Long id) {
		// TODO Auto-generated method stub
		return scdrepo.findById(id).get();
	}

	@Transactional
	public void deleteEnrolment(StudentCourseDetails enrolment) {
		// TODO Auto-generated method stub
		scdrepo.delete(enrolment);
	}
	@Transactional
	public List<Users> getStudentList() {
		// TODO Auto-generated method stub
		return urepo.findByRole(Roles.STUDENT);
	}

	@Transactional
	public void updateEnrolment(StudentCourseDetails enrolment) {
		// TODO Auto-generated method stub
		scdrepo.save(enrolment);
	}
  
	@Transactional
	public List<Courses> getCourseList() {
		// TODO Auto-generated method stub
		return crepo.findAll();
	}

	@Transactional
	public void saveEnrolment(StudentCourseDetails enrolment) {
		// TODO Auto-generated method stub
		scdrepo.save(enrolment);
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
		return urepo.findByRole(role, pageable);
	}

	@Transactional
	public List<Users> listUsers() {
		return urepo.findAll();
	}

	@Transactional
	public String passwordGenerator() {
		int rand = (int) (10000 + (Math.random() * 99999));
		String pw = Integer.toString(rand);
		return pw;
	}
	
	@Transactional
	public Users save(Users user) {
		return urepo.save(user);
	}

	@Override
	public List<Courses> getCoursesByStuId(Long studentId) {
		// TODO Auto-generated method stub
		LocalDate currentDate = LocalDate.now();
		return crepo.findCoursesByStuId(studentId,currentDate);
	}

}
