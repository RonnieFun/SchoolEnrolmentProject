package sg.edu.iss.caps.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface LecturerInterface {

	List<Courses> getAllCourses();
	
	List<Courses> findCoursesByCourseId(Long courseID);
	
	List<Courses> getAllCoursesByLecturerId(Long id);
		
	List<Courses> getAllCoursesByRoleAndId(Roles role, Long userID);
	
	List<Courses> findCoursebyCourseNameStartDateCourseID(String courseName, LocalDate courseStartDate);
		
	List<Users> findUsersByRoleAndId(Long userID, Roles role);
			
	List<Users> getStudentsByCourseID(Long courseID);
	
	List<Users> getAllUsers();

	List<Users> getAllUsersByRole(Roles role);
	
	List<Users> getAllUsersByUserID(Long userID);
	
	List<StudentCourseDetails> getAllStudentCourseDetails();
	
	List<StudentCourseDetails> getStudentCourseDetailsByCourseID(Long courseID);
	
	List<StudentCourseDetails> getAllUsersByRoleCourseNameStartDate(Roles role, EnrolmentStatus enrolmentStatus, String courseName, 
			LocalDate courseStartDate);

	List<StudentCourseDetails> getAllUsersByRoleCourseID(Roles role, EnrolmentStatus enrolmentStatus, Long courseID);
		
	List<StudentCourseDetails> getGradesByStudentId(EnrolmentStatus enrolmentStatus, Long userID, Roles role);

	void addCourseTaught(Long id, Courses course);

	void removeCourseTaught(Long id, Courses course);

	StudentCourseDetails getStudentCourseDetailsByStudentIDAndCourseID(long parseLong, long parseLong2);

	void saveStudentCourseDetails(StudentCourseDetails studentCourseDetails);

	Courses getCoursesById(Long id);
}
