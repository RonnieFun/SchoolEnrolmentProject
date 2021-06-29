package sg.edu.iss.caps.repo;

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

	
	@Query("SELECT scd FROM StudentCourseDetails scd "
			+ "WHERE scd.student.userID = :id")
	List<StudentCourseDetails> findEnrolmentByUserID(@Param("id")long userid);

	@Query("SELECT scd FROM StudentCourseDetails scd "
			+ "WHERE scd.student.userID = :id "
			+ "AND scd.enrolmentStatus=:enrolmentStatus")
	List<StudentCourseDetails> findEnrolmentByUserIDAndEnrolmentStatus(@Param("id") long userid, @Param("enrolmentStatus") EnrolmentStatus enrolmentstatus);

	@Query("SELECT scd FROM StudentCourseDetails scd "
			+ "WHERE scd.student.userID = :id "
			+ "AND scd.course.courseID=:courseid")
	List<StudentCourseDetails> findEnrolmentByUserIDAndCourseID(@Param("id") long userid,  @Param("courseid") long courseid);

	@Query("SELECT scd FROM StudentCourseDetails scd WHERE scd.course.courseID=:courseid")
	List<StudentCourseDetails> findEnrolmentByCourseID(@Param("courseid") long courseid);

	

}