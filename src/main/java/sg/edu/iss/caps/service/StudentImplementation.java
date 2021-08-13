package sg.edu.iss.caps.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class StudentImplementation implements StudentInterface {

	@Autowired
	UsersRepository urepo;

	@Autowired
	StudentCourseDetailsRepository scdrepo;

	@Autowired
	CoursesRepository crepo;

	@Autowired
	private JavaMailSender emailSender;

	@Transactional
	public List<Users> findAllUsersByRole(Roles role) {

		return urepo.findByRole(role);
	}

	@Transactional
	public List<Courses> findCoursesByRole(Roles role) {
		return urepo.findCoursesByRole(role);
	}

	@Transactional
	public Users findUserByUserID(long userid) {

		Users user = urepo.getById(userid);
		return user;
	}

	@Transactional
	public void addEnrolment(StudentCourseDetails enrolment) {
		scdrepo.save(enrolment);
	}

	@Transactional
	public List<StudentCourseDetails> findEnrolmentByUserID(long userid) {

		return scdrepo.findEnrolmentByUserID(userid);
	}

	@Transactional
	public Courses findCoursebyCourseID(long courseid) {

		crepo.findById(courseid);
		return crepo.getById(courseid);
	}

	@Transactional
	public StudentCourseDetails findEnrolmentByEnrolmentID(long enrolmentid) {

		return scdrepo.getById(enrolmentid);
	}

	@Transactional
	public void updateEnrolment(StudentCourseDetails enrolment) {

		scdrepo.save(enrolment);
	}

	@Transactional
	public List<StudentCourseDetails> findEnrolmentByUserIDAndEnrolmentStatus(long userid,
			EnrolmentStatus enrolmentstatus) {

		return scdrepo.findEnrolmentByUserIDAndEnrolmentStatus(userid, enrolmentstatus);

	}

	@Transactional
	public List<StudentCourseDetails> findEnrolmentByUserIDAndCourseID(long userid, long courseid) {

		return scdrepo.findEnrolmentByUserIDAndCourseID(userid, courseid);
	}

	@Transactional
	public List<StudentCourseDetails> findEnrolmentByCourseID(long courseid) {

		return scdrepo.findEnrolmentByCourseID(courseid);
	}

	@Transactional
	public List<StudentCourseDetails> findEnrolmentByCourseIDAndEnrolmentStatus(long courseID,
			EnrolmentStatus enrolmentstatus) {
		return scdrepo.findEnrolmentByCourseIDAndEnrolmentStatus(courseID, enrolmentstatus);
	}
}
