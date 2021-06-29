package sg.edu.iss.caps.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface LecturerInterface {

	//To get list of all courses
	List<Courses> getAllCourses();
	
	//To get list of all courses by Lecturer's Id
	List<Courses> getAllCoursesByRoleAndId(Roles role, Long userID);
	
	//To get all student grades by student id
	List<Users> getStudentResults(Long userID, Roles role);
		
	//To get list of all users
	List<Users> getAllUsers();

	//To get list of users by Role
	List<Users> getAllUsersByRole(Roles role);
	
	//To get list of users by Role, Course Name and Course Start
	List<Users> getAllUsersByRoleCourseNameStartDate(Roles role, EnrolmentStatus enrolmentStatus, String courseName, 
			LocalDate courseStartDate);
	
	List<StudentCourseDetails> getGradesByStudentId(Long userID, Roles role);
	
  public void addCourseTaught(Long id, Courses course);

	void removeCourseTaught(Long id, Courses course);
<<<<<<< HEAD
	
	List<Users> getStudentsByCourseID(Long courseID);
	
	List<StudentCourseDetails> getStudentCourseDetailsByCourseID(Long courseID);

	StudentCourseDetails getStudentCourseDetailsByStudentIDAndCourseID(long parseLong, long parseLong2);
	
	void saveStudentCourseDetails(StudentCourseDetails studentCourseDetails);
=======

>>>>>>> refs/remotes/origin/master
}


