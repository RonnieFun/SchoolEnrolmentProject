package sg.edu.iss.caps.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Collection;

@Entity
public class Users {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userID;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private Roles role;
	
	private String phoneNumber;
	
	private String address;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;
	
	private String salutation;
	
	// One to Many relationship between Users and StudentCourseDetails
	@OneToMany(mappedBy = "student")
	private Collection<StudentCourseDetails> studentCourseDetail;
	
	// One to Many relationship between Users and LecturerCourseDetails
	@OneToMany(mappedBy = "lecturer")
	private Collection<LecturerCourseDetails> lecturerCourseDetail;

	//No Argument constructor
	public Users() {
		super();
	}

	//Argument constructor with all fields(without userID)
	public Users(String firstName, String lastName, String email, String password, Roles role, String phoneNumber,
			String address, LocalDate birthday, String salutation, Collection<StudentCourseDetails> studentCourseDetail,
			Collection<LecturerCourseDetails> lecturerCourseDetail) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.birthday = birthday;
		this.salutation = salutation;
		this.studentCourseDetail = studentCourseDetail;
		this.lecturerCourseDetail = lecturerCourseDetail;
	}

	public Users(String firstName, String lastName, String email, String password, Roles role, String phoneNumber,
			String address, LocalDate birthday, String salutation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.birthday = birthday;
		this.salutation = salutation;
	}

	//Argument constructor for testing purpose
	public Users(String email, String password, Roles role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	

	public Users(String firstName, String lastName, String email, Roles role, String phoneNumber, String address,
			LocalDate birthday, String salutation) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.birthday = birthday;
		this.salutation = salutation;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public Collection<StudentCourseDetails> getStudentCourseDetail() {
		return studentCourseDetail;
	}

	public void setStudentCourseDetail(Collection<StudentCourseDetails> studentCourseDetail) {
		this.studentCourseDetail = studentCourseDetail;
	}

	public Collection<LecturerCourseDetails> getLecturerCourseDetail() {
		return lecturerCourseDetail;
	}

	public void setLecturerCourseDetail(Collection<LecturerCourseDetails> lecturerCourseDetail) {
		this.lecturerCourseDetail = lecturerCourseDetail;
	}
	
	
	
	
	

}
