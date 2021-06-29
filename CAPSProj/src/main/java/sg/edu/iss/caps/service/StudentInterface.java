package sg.edu.iss.caps.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface StudentInterface {
	
	List<Users> findAllUsersByRole(Roles role);


	List<Courses> findCoursesByRole(Roles role);
	

	Users findUserByUserID(long userid);

	void addEnrolment(StudentCourseDetails enrolment);

	List<StudentCourseDetails> findEnrolmentByUserID(long userid);
	List<StudentCourseDetails> findEnrolmentByUserIDAndEnrolmentStatus(long userid, EnrolmentStatus enrolmentstatus);
	List<StudentCourseDetails> findEnrolmentByUserIDAndCourseID(long userid, long courseid);
	Courses findCoursebyCourseID(long courseid);

	StudentCourseDetails findEnrolmentByEnrolmentID(long enrolmentid);


	void updateEnrolment(StudentCourseDetails enrolment);

	List<StudentCourseDetails> findEnrolmentByCourseID(long courseid);

	
}


