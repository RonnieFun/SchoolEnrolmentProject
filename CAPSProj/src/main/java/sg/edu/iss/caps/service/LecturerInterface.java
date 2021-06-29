package sg.edu.iss.caps.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface LecturerInterface {

	List<Courses> getAllCourses();

	List<Courses> getAllCoursesByLecturerId(Long id);

	List<Courses> getByCourseNameCourseStart(String courseName, LocalDate courseStartDate);

	Courses getCoursesById(Long id);

	List<Users> getAllUsers();

	Users getUsersById(Long id);

	List<Users> getAllUsersByRole(Roles role);

	public void addCourseTaught(Long id, Courses course);

	void removeCourseTaught(Long id, Courses course);
	
	List<Users> getStudentsByCourseID(Long courseID);
	
	List<StudentCourseDetails> getStudentCourseDetailsByCourseID(Long courseID);

	StudentCourseDetails getStudentCourseDetailsByStudentIDAndCourseID(long parseLong, long parseLong2);
	
	void saveStudentCourseDetails(StudentCourseDetails studentCourseDetails);
}
