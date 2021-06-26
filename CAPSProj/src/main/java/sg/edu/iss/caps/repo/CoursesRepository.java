package sg.edu.iss.caps.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Courses;

public interface CoursesRepository extends JpaRepository<Courses, Long> {

	@Query("SELECT c FROM Courses c JOIN c.lecturerCourseDetails lc WHERE lc.id = :id")
	List<Courses> findByLecturerId(@Param("id") Long id); 
	
}
