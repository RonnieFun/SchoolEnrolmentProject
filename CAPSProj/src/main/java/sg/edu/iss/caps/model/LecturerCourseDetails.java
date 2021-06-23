package sg.edu.iss.caps.model;

import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class LecturerCourseDetails {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToMany
	private Collection<Courses> lectCourse;
	
	@ManyToOne
	private Users lecturer;

	//No Argument constructor
	public LecturerCourseDetails() {
		super();
	}

	//Argument constructor
	public LecturerCourseDetails(Users lecturer) {
		super();
		this.lecturer = lecturer;
	}



	//Argument constructor with all fields(without id)
	public LecturerCourseDetails(Collection<Courses> lectCourse, Users lecturer) {
		super();
		this.lectCourse = lectCourse;
		this.lecturer = lecturer;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Collection<Courses> getLectCourse() {
		return lectCourse;
	}



	public void setLectCourse(Collection<Courses> lectCourse) {
		this.lectCourse = lectCourse;
	}



	public Users getLecturer() {
		return lecturer;
	}



	public void setLecturer(Users lecturer) {
		this.lecturer = lecturer;
	}
	
}
