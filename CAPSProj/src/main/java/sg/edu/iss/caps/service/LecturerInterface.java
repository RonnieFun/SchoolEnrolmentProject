package sg.edu.iss.caps.service;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface LecturerInterface {

	// To get list of all courses
	List<Courses> getAllCourses();

	// To get list of all courses by Lecturer's Id
	List<Courses> getAllCoursesByRoleAndId(Roles role, Long userID);

	// To get all student grades by student id
	List<Users> getStudentResults(Long userID, Roles role);

	// To get list of all users
	List<Users> getAllUsers();

	// To get list of users by Role
	List<Users> getAllUsersByRole(Roles role);

	// To get list of users by Role, Course Name and Course Start
	List<Users> getAllUsersByRoleCourseNameStartDate(Roles role, EnrolmentStatus enrolmentStatus, String courseName,
			LocalDate courseStartDate);

	List<StudentCourseDetails> getGradesByStudentId(Long userID, Roles role);

	public void addCourseTaught(Users user, Courses course);

	void removeCourseTaught(Users user, Courses course);

	List<Users> getStudentsByCourseID(Long courseID);

	List<StudentCourseDetails> getStudentCourseDetailsByCourseID(Long courseID);

	StudentCourseDetails getStudentCourseDetailsByStudentIDAndCourseID(long parseLong, long parseLong2);

	void saveStudentCourseDetails(StudentCourseDetails studentCourseDetails);

	List<Courses> getAllCoursesByLecturerId(Long id);

	Courses getCoursesById(Long id);
}
