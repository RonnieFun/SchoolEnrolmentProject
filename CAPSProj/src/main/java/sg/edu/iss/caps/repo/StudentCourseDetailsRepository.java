package sg.edu.iss.caps.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface StudentCourseDetailsRepository extends JpaRepository<StudentCourseDetails, Long> {
	
//	Code for displaying all the students' course grades without filtering based on lecturer id
	@Query("SELECT sc FROM StudentCourseDetails sc JOIN sc.course c JOIN sc.student u WHERE u.userID = :userID AND u.role = :role")
	  List<StudentCourseDetails> findGradesByStudentId(@Param("userID") Long userID, @Param("role") Roles role);
	
	@Query("SELECT sc FROM StudentCourseDetails sc JOIN sc.course c JOIN sc.student u WHERE u.role= :role "
			+ "AND sc.enrolmentStatus = :enrolmentStatus "
			+ "AND c.courseID = :courseID "
			+ "AND c.courseStartDate = :courseStartDate AND c.courseID = sc.course")
	List<StudentCourseDetails> findByCourseNameCourseStart(
			@Param("role") Roles role,
			@Param("enrolmentStatus") EnrolmentStatus enrolmentStatus,
			@Param("courseID") Long courseID, 
			@Param("courseStartDate") LocalDate courseStartDate);
	
	
	@Query("SELECT sc FROM StudentCourseDetails sc JOIN sc.course c JOIN sc.student u WHERE u.role= :role "
			+ "AND sc.enrolmentStatus = :enrolmentStatus "
			+ "AND c.courseID = :courseID "
			+ "AND c.courseID = sc.course")
	List<StudentCourseDetails> findByRoleEnrolementStatusCourseID(
			@Param("role") Roles role,
			@Param("enrolmentStatus") EnrolmentStatus enrolmentStatus,
			@Param("courseID") Long courseID);
	
	
	@Query("SELECT scd.course.courseID FROM StudentCourseDetails scd")
	List<Long> getCourseIDsWithStudents();

	@Query("SELECT scd FROM StudentCourseDetails scd " + "WHERE scd.student.userID = :id")
	List<StudentCourseDetails> findEnrolmentByUserID(@Param("id") long userid);

	@Query("SELECT scd FROM StudentCourseDetails scd " + "WHERE scd.student.userID = :id "
			+ "AND scd.enrolmentStatus=:enrolmentStatus")
	List<StudentCourseDetails> findEnrolmentByUserIDAndEnrolmentStatus(@Param("id") long userid,
			@Param("enrolmentStatus") EnrolmentStatus enrolmentstatus);

	@Query("SELECT scd FROM StudentCourseDetails scd " + "WHERE scd.student.userID = :id "
			+ "AND scd.course.courseID=:courseid")
	List<StudentCourseDetails> findEnrolmentByUserIDAndCourseID(@Param("id") long userid,
			@Param("courseid") long courseid);

	@Query("SELECT scd FROM StudentCourseDetails scd WHERE scd.course.courseID=:courseid")
	List<StudentCourseDetails> findEnrolmentByCourseID(@Param("courseid") long courseid);

	@Query("Select scd.student from StudentCourseDetails scd where scd.course.courseID = :courseID")
	List<Users> getStudentsByCourseID(@Param("courseID") long courseID);

	@Query("Select scd from StudentCourseDetails scd where scd.course.courseID= :courseID")
	List<StudentCourseDetails> getStudentCourseDetailsByCourseID(@Param("courseID") long courseID);

	@Query("Select scd from StudentCourseDetails scd where scd.course.courseID=:courseID and scd.student.userID=:studentID")
	StudentCourseDetails getStudentCourseDetailsByStudentIDAndCourseID(long studentID, long courseID);
	
	@Query("SELECT scd FROM StudentCourseDetails scd " + "WHERE scd.course.courseID = :id "
			+ "AND scd.enrolmentStatus=:enrolmentStatus")
	List<StudentCourseDetails> findEnrolmentByCourseIDAndEnrolmentStatus(@Param("id") long courseid,
			@Param("enrolmentStatus") EnrolmentStatus enrolmentstatus);

	
	//Code to display CGPA based on a particular logged in lecturer. KIV for now. 
//	@Query(value = "SELECT * FROM caps.student_course_details scd where scd.course_courseid "
//			+ "IN(SELECT c1.courseid from caps.courses c1 INNER JOIN caps.users_courses uc\r\n"
//			+ "	on c1.courseid = uc.courses_courseid where uc.users_userid =:lecturerID) "
//			+ "and scd.student_userid =:userID", nativeQuery = true)
//	List<StudentCourseDetails> findGradesByStudentIDLecturerID(@Param("lecturerID") Long lecturerID, @Param("userID") Long userID);
}


