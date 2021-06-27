package sg.edu.iss.caps.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	@Query("SELECT u FROM Users u WHERE u.role=:role")
	List<Users> findByRole(@Param("role") Roles role);
	
	@Query("SELECT u FROM Users u JOIN u.studentCourseDetail sc JOIN sc.course c WHERE u.role= :role "
			+ "AND sc.enrolmentStatus = :enrolmentStatus "
			+ "AND c.courseName = :courseName "
			+ "AND c.courseStartDate = :courseStartDate ")
	List<Users> findByCourseNameCourseStart(
			@Param("role") Roles role,
			@Param("enrolmentStatus") EnrolmentStatus enrolmentStatus,
			@Param("courseName") String courseName, 
			@Param("courseStartDate") LocalDate courseStartDate);
}