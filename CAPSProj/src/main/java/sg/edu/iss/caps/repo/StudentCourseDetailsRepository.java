package sg.edu.iss.caps.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.edu.iss.caps.model.StudentCourseDetails;

public interface StudentCourseDetailsRepository extends JpaRepository<StudentCourseDetails, Long> {
	@Query("SELECT scd.stuCourse.courseID FROM StudentCourseDetails scd")
	List<Long> getCourseIDsWithStudents();
}
