package sg.edu.iss.caps.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseID;

	@NotNull (message = "Course name must be filled in.")
	private String courseName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate courseStartDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate courseEndDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate examDate;
	
	@Min(value = 0, message = "Value should be greater than or equal to 0.")
	private int credits;
	
	@Min(value = 0, message = "Value should be greater than or equal to 0.")
	private int courseCapacity;

	private String description;

	private CourseStatus courseStatus;

	// One to many relationship between StudentCoursedetails and Courses
	@OneToMany(mappedBy="course")
	private Collection<StudentCourseDetails> studentCourseDetails;

	// Many to many relation between Courses and Users
	@ManyToMany (mappedBy="courses")
	private Collection<Users> users;

	// Many to many relationship between LecturerCourseDetails and Courses
	@ManyToMany(mappedBy = "lectCourse")
	private Collection<LecturerCourseDetails> lecturerCourseDetails;

	// No argument constructor
	public Courses() {
		super();
	}

	// Argument constructor
	public Courses(String courseName, LocalDate courseStartDate, LocalDate courseEndDate, LocalDate examDate,
			int credits, int courseCapacity, String description, CourseStatus courseStatus) {
		super();
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.examDate = examDate;
		this.credits = credits;
		this.courseCapacity = courseCapacity;
		this.description = description;
		this.courseStatus = courseStatus;
	}

	// Argument constructor with all fields(without courseID)
	public Courses(String courseName, LocalDate courseStartDate, LocalDate courseEndDate, LocalDate examDate,
			int credits, int courseCapacity, String description, CourseStatus courseStatus,
			Collection<StudentCourseDetails> studentCourseDetails, Collection<Users> users) {
		super();
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.examDate = examDate;
		this.credits = credits;
		this.courseCapacity = courseCapacity;
		this.description = description;
		this.courseStatus = courseStatus;
		this.studentCourseDetails = studentCourseDetails;
		this.users = users;
	}

	// Argument constructor with testing purpose
	public Courses(String courseName, LocalDate courseStartDate, LocalDate courseEndDate, LocalDate examDate,
			int courseCapacity, CourseStatus courseStatus) {
		super();
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.examDate = examDate;
		this.courseCapacity = courseCapacity;
		this.courseStatus = courseStatus;
	}

	// Argument constructor for testing coursestaught.html page (By Max)
	public Courses(String courseName, LocalDate courseStartDate, LocalDate courseEndDate, int credits,
			int courseCapacity, String description, CourseStatus courseStatus) {
		super();
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.credits = credits;
		this.courseCapacity = courseCapacity;
		this.description = description;
		this.courseStatus = courseStatus;
	}
	
	public long getCourseID() {
		return courseID;
	}

	public void setCourseID(long courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDate getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(LocalDate courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public LocalDate getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(LocalDate courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getCourseCapacity() {
		return courseCapacity;
	}

	public void setCourseCapacity(int courseCapacity) {
		this.courseCapacity = courseCapacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CourseStatus getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	public Collection<StudentCourseDetails> getStudentCourseDetails() {
		return studentCourseDetails;
	}

	public void setStudentCourseDetails(Collection<StudentCourseDetails> studentCourseDetails) {
		this.studentCourseDetails = studentCourseDetails;
	}

	public Collection<Users> getUsers() {
		return users;
	}

	public void setUsers(Collection<Users> users) {
		this.users = users;
	}

	public LocalDate getExamDate() {
		return examDate;
	}

	public void setExamDate(LocalDate examDate) {
		this.examDate = examDate;
	}
}
