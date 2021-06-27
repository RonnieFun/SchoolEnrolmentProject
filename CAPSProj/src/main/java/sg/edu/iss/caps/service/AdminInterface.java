package sg.edu.iss.caps.service;
import java.util.List;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface AdminInterface {
	
	List<StudentCourseDetails> getAllEnrolment();

	StudentCourseDetails getEnrolment(Long id);

	void deleteEnrolment(StudentCourseDetails enrolment);

	List<Users> getStudentList();

	void updateEnrolment(StudentCourseDetails enrolment);

	List<Courses> getCourseList();

}
