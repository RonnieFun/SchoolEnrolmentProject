package sg.edu.iss.caps.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Courses;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

//	@Query("SELECT c FROM Courses c JOIN c.lecturerCourseDetails lc JOIN lc.lecturer l WHERE l.id = :id")
//	List<Courses> findByLecturerId(@Param("id") Long id); 
	
	@Query("SELECT c from Courses c WHERE c.courseName = :courseName AND c.courseStartDate = :courseStartDate")
	List<Courses> findByCourseSearch(@Param("courseName") String courseName, @Param("courseStartDate") LocalDate courseStartDate);
	
}

