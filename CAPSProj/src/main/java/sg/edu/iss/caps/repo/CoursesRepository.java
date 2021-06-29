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

	@Query("SELECT c FROM Courses c JOIN c.users u WHERE u.role = :role AND u.userID = :userID")
	List<Courses> findCoursesByRoleAndId(@Param("role") Roles role, @Param("userID") Long userID);
	
	@Query("SELECT c from Courses c WHERE c.courseName = :courseName AND c.courseStartDate = :courseStartDate")
	List<Courses> findByCourseSearch(@Param("courseName") String courseName,
			@Param("courseStartDate") LocalDate courseStartDate);

	@Query("SELECT c FROM Courses c JOIN c.users u where :lecturerId IN u.userID")
	List<Courses> findCoursesByLecturerId(@Param("lecturerId") Long userID);

	// COMMENT BY MAX: KIV the below @Query methods. Please do not delete them for
	// now.

	// @Query("SELECT c FROM Courses c JOIN c.lecturerCourseDetails lc JOIN
	// lc.lecturer l WHERE l.id = :id")
//	List<Courses> findByLecturerId(@Param("id") Long id); 

//	@Query("SELECT c from Courses c WHERE c.courseName = :courseName AND c.courseStartDate = :courseStartDate")
//	List<Courses> findByCourseNameCourseStart(@Param("courseName") String courseName, 
//			@Param("courseStartDate") LocalDate courseStartDate);

//	List<Courses> findByCourseNameCourseStart(@Param("courseName") String courseName, 
//	@Param("courseStartDate") LocalDate courseStartDate);

	/*
	 * @Query("SELECT c.courseName FROM Courses c JOIN c.users u where :lecturerId IN u.userID GROUP BY c.courseName"
	 * ) List<Courses> findDistinctCourseNamesTaught (@Param("lecturerId") Long
	 * userID);
	 */
}
