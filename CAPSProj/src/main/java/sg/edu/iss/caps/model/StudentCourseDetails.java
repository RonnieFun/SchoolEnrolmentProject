package sg.edu.iss.caps.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class StudentCourseDetails {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private LocalDate dateOfEnrollment;
	
	private String grades;
	

	private double gradepoint;
	
	private EnrolmentStatus enrolmentStatus;
	
	// Many to one relationship between student User and StudentCourseDetails
	@ManyToOne
	private Users student;
	
	//Many to one relationship between Courses and StudentCourseDetails
	@ManyToOne
	private Courses course;

	//No Argument constructor
	public StudentCourseDetails() {
		super();
	}
	
	public StudentCourseDetails(LocalDate dateOfEnrollment, String grades, EnrolmentStatus enrolmentStatus,
			Users student, Courses course) {
		super();
		this.dateOfEnrollment = dateOfEnrollment;
		this.grades = grades;
		this.enrolmentStatus = enrolmentStatus;
		this.student = student;
		this.course = course;
	}

	public StudentCourseDetails(LocalDate dateOfEnrollment, String grades, EnrolmentStatus enrolmentStatus) {
		super();
		this.dateOfEnrollment = dateOfEnrollment;
		this.grades = grades;
		this.enrolmentStatus = enrolmentStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDateOfEnrollment() {
		return dateOfEnrollment;
	}

	public void setDateOfEnrollment(LocalDate dateOfEnrollment) {
		this.dateOfEnrollment = dateOfEnrollment;
	}

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public EnrolmentStatus getEnrolmentStatus() {
		return enrolmentStatus;
	}

	public void setEnrolmentStatus(EnrolmentStatus enrolmentStatus) {
		this.enrolmentStatus = enrolmentStatus;
	}

	public Users getStudent() {
		return student;
	}

	public void setStudent(Users student) {
		this.student = student;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}



}
