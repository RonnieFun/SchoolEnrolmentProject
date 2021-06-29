package sg.edu.iss.caps.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface StudentCourseDetailsRepository extends JpaRepository<StudentCourseDetails, Long> {
	@Query("SELECT scd.course.courseID FROM StudentCourseDetails scd")
	List<Long> getCourseIDsWithStudents();
	
	@Query("Select scd.student from StudentCourseDetails scd where scd.course.courseID = :courseID")
	List<Users> getStudentsByCourseID (@Param("courseID") Long courseID);

	@Query("Select scd from StudentCourseDetails scd where scd.course.courseID= :courseID")
	List<StudentCourseDetails> getStudentCourseDetailsByCourseID(@Param("courseID") Long courseID);

	@Query("Select scd from StudentCourseDetails scd where scd.course.courseID=:courseID and scd.student.userID=:studentID")
	StudentCourseDetails getStudentCourseDetailsByStudentIDAndCourseID(long studentID, long courseID);
}