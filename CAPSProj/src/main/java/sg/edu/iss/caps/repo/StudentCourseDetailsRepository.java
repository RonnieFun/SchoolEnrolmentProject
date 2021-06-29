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

	@Query("SELECT sc FROM StudentCourseDetails sc JOIN sc.course c JOIN c.users u WHERE "
			+ "u.userID = :userID AND u.role = :role")
	List<StudentCourseDetails> findGradesByStudentId( @Param("userID") Long userID,
			@Param("role") Roles role);	

	@Query("SELECT scd.course.courseID FROM StudentCourseDetails scd")
	List<Long> getCourseIDsWithStudents();
}
