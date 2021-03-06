package sg.edu.iss.caps.repo;


import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Courses;

import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.StudentCourseDetails;

import sg.edu.iss.caps.model.Roles;


public interface CoursesRepository extends JpaRepository<Courses, Long> {
	
	
	@Query("SELECT c from Courses c WHERE c.courseName = :courseName AND c.courseStartDate = :courseStartDate")
	List<Courses> findByCourseSearch(@Param("courseName") String courseName,
			@Param("courseStartDate") LocalDate courseStartDate);

	@Query("SELECT c FROM Courses c JOIN c.users u where :lecturerId IN u.userID")
	List<Courses> findCoursesByLecturerId(@Param("lecturerId") Long userID);
	
	@Query("SELECT c FROM Courses c JOIN c.users u WHERE u.role = :role AND u.userID = :userID")
	List<Courses> findCoursesByRoleAndId(@Param("role") Roles role, @Param("userID") Long userID); 
	
	Courses findByCourseNameAndCourseStartDate(String courseName, LocalDate courseStartDate);
	
	@Query("SELECT DISTINCT c FROM Courses c WHERE c.courseName = :courseName AND c.courseStartDate = :courseStartDate AND c.courseID = :courseID")
	List<Courses> findCoursebyCourseNameStartDateCourseID(@Param("courseName") String courseName, @Param("courseStartDate") LocalDate courseStartDate, @Param("courseID") Long courseID);	

	Courses getById(Long id);

	@Query("SELECT c FROM Courses c where c.courseID NOT IN(select sc.course.courseID FROM StudentCourseDetails sc where sc.student.userID=:studentId) and c.courseStartDate> :currentDate")
	List<Courses> findCoursesByStuId(@Param("studentId") Long studentId, @Param("currentDate") LocalDate currentDate);
	
	@Query("Select c FROM Courses c where c.courseID = :courseID")
	List<Courses> findCoursesByCourseId(@Param ("courseID") Long courseID);
}
