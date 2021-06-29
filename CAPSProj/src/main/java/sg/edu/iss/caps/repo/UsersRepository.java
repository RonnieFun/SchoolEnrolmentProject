package sg.edu.iss.caps.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query("SELECT u FROM Users u WHERE u.role=:role")
	List<Users> findByRole(@Param("role") Roles role);


	@Query("SELECT c FROM Courses c JOIN c.users u WHERE u.role = :role")
	List<Courses> findCoursesByRole(@Param("role") Roles role);



	@Query("SELECT u FROM Users u JOIN u.studentCourseDetail sc JOIN sc.course c WHERE u.role= :role "
			+ "AND sc.enrolmentStatus = :enrolmentStatus " + "AND c.courseName = :courseName "
			+ "AND c.courseStartDate = :courseStartDate ")
	List<Users> findByCourseNameCourseStart(@Param("role") Roles role,
			@Param("enrolmentStatus") EnrolmentStatus enrolmentStatus, @Param("courseName") String courseName,
			@Param("courseStartDate") LocalDate courseStartDate);

	@Query("SELECT u FROM Users u JOIN u.courses c JOIN c.studentCourseDetails sc " + "WHERE u.userID = :userID "
			+ "AND u.role = :role")
	List<Users> findUsersByRoleAndId(@Param("userID") Long userID, @Param("role") Roles role);

	public Users findUserByfirstNameAndLastName(String firstName, String lastName);

	Page<Users> findByRole(Roles role, Pageable pageable);
	
	public Users getUserByEmail(String email);

}
