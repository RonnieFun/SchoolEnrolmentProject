package sg.edu.iss.caps.model;

import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Courses {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseID;
	
	private String courseName;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private LocalDate courseStartDate;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private LocalDate courseEndDate;
	
	private int credits;
	
	private int courseCapacity;
	
	private String description;
	
	private CourseStatus courseStatus;
	
	private String courseBatch;
	
	//Many to many relationship between StudentCoursedetails and Courses
	@ManyToMany(mappedBy = "stuCourse")
	private Collection<StudentCourseDetails> studentCourseDetails;
	
	//Many to many relationship between LecturerCourseDetails and Courses
	@ManyToMany(mappedBy = "lectCourse")
	private Collection<LecturerCourseDetails> lecturerCourseDetails;

	//No argument constructor
	public Courses() {
		super();
	}
	
	//Argument constructor
	public Courses(long courseID, String courseName, LocalDate courseStartDate, LocalDate courseEndDate, int credits,
			int courseCapacity, String description, String courseBatch, CourseStatus courseStatus) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.credits = credits;
		this.courseCapacity = courseCapacity;
		this.description = description;
		this.courseStatus = courseStatus;
		this.courseBatch = courseBatch;
	}


	//Argument constructor with all fields(without courseID)
	public Courses(String courseName, LocalDate courseStartDate, LocalDate courseEndDate, int credits,
			int courseCapacity, String description, String courseBatch, CourseStatus courseStatus,
			Collection<StudentCourseDetails> studentCourseDetails,
			Collection<LecturerCourseDetails> lecturerCourseDetails) {
		super();
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.credits = credits;
		this.courseCapacity = courseCapacity;
		this.description = description;
		this.courseStatus = courseStatus;
		this.courseBatch = courseBatch;
		this.studentCourseDetails = studentCourseDetails;
		this.lecturerCourseDetails = lecturerCourseDetails;
	}

	//Argument constructor with testing purpose
	public Courses(String courseName, LocalDate courseStartDate, LocalDate courseEndDate) {
		super();
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
	}

	//Argument constructor for testing coursestaught.html page (By Max)
	public Courses(String courseName, LocalDate courseStartDate, LocalDate courseEndDate, int credits,
			int courseCapacity, String description, String courseBatch, CourseStatus courseStatus) {
		super();
		this.courseName = courseName;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.credits = credits;
		this.courseCapacity = courseCapacity;
		this.description = description;
		this.courseStatus = courseStatus;
		this.courseBatch = courseBatch;
	}

	public long getCourseID() {
		return courseID;
	}

	public String getCourseBatch() {
		return courseBatch;
	}

	public void setCourseBatch(String courseBatch) {
		this.courseBatch = courseBatch;
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

	public Collection<LecturerCourseDetails> getLecturerCourseDetails() {
		return lecturerCourseDetails;
	}

	public void setLecturerCourseDetails(Collection<LecturerCourseDetails> lecturerCourseDetails) {
		this.lecturerCourseDetails = lecturerCourseDetails;
	}

}
