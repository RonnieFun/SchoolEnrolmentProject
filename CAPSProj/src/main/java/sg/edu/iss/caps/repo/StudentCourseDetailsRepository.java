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

	@Query("SELECT sc FROM StudentCourseDetails sc JOIN sc.course c JOIN c.users u WHERE "
			+ "u.userID = :userID AND u.role = :role AND c.courseID = sc.course AND u.userID = sc.student")
	List<StudentCourseDetails> findGradesByStudentId(@Param("userID") Long userID, @Param("role") Roles role);

	@Query("SELECT sc FROM StudentCourseDetails sc JOIN sc.student u JOIN u.courses c WHERE u.role= :role "
			+ "AND sc.enrolmentStatus = :enrolmentStatus " + "AND c.courseID = :courseID "
			+ "AND c.courseStartDate = :courseStartDate AND c.courseID = sc.course")
	List<StudentCourseDetails> findByCourseNameCourseStart(@Param("role") Roles role,
			@Param("enrolmentStatus") EnrolmentStatus enrolmentStatus, @Param("courseID") Long courseID,
			@Param("courseStartDate") LocalDate courseStartDate);

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
}
