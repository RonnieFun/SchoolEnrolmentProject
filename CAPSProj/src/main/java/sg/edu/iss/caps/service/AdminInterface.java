package sg.edu.iss.caps.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

public interface AdminInterface {
	
	List<StudentCourseDetails> getAllEnrolment();

	StudentCourseDetails getEnrolment(Long id);

	void deleteEnrolment(StudentCourseDetails enrolment);

	List<Users> getStudentList();

	void updateEnrolment(StudentCourseDetails enrolment);

	List<Courses> getCourseList();

	void saveEnrolment(StudentCourseDetails enrolment);
	
	public void createUser(Users user);
	public void updateUser(Users user);
	public List<Users> listByRole(Roles role);
	public Page<Users> listAllUsers(int pageNumber, String sortField, String sortDir);
	public List<Users> listUsers();
	public void deleteUser(Long id);
	public Users findByName(String name);
	public Users findById(Long ID);
	public String passwordGenerator();
	public Page<Users> listRoleUsers(int pageNumber, String sortField, String sortDir, Roles role);
	public Users save(Users user);
	public Users findByEmail(String email);

	List<Courses> getCourses();
	Courses getCourseById(long courseID);
	void deleteCourse(Courses course);
	void savecourse(Courses course);
	List<Long> getCoursesWithStudents();
	Optional<Courses> findCourseById(long courseID);

	List<Courses> getCoursesByStuId(Long studentId);

	Page<StudentCourseDetails> listAllEnrolment(int currentPage, String sortField, String sortDir);
	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException;
	public void updatePassword(Users user, String newPassword);
	public Users get(String resetPasswordToken);
}
