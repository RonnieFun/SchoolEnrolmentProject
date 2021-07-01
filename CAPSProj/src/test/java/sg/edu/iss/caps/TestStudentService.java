package sg.edu.iss.caps;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;
import sg.edu.iss.caps.repo.UsersRepository;
import sg.edu.iss.caps.service.StudentInterface;
import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;


@SpringBootTest
public class TestStudentService {
	
	@Autowired
	StudentInterface stuservice;
	
	// create a mock implementation of the UsersRepository
	@MockBean
	private UsersRepository urepo;
	
	// create a mock implementation of the StudentCourseDetailsRepository
	@MockBean
	private StudentCourseDetailsRepository scdrepo;
	
	// create a mock implementation of the StudentCourseDetailsRepository
	@MockBean
	CoursesRepository crepo;
				
	
	@Test
	@DisplayName("Test addEnrolment() Success")
	public void testAddEnrolment1() {
		StudentCourseDetails scd = new StudentCourseDetails(LocalDate.now(), null, EnrolmentStatus.PENDING);
		stuservice.addEnrolment(scd);
		verify(scdrepo, times(1)).save(scd);
	}
	
	@Test
	@DisplayName("Test findEnrolmentByCourseID Success")
	public void testFindEnrolmentByCourseID() {
		
		// Setup our mock repository	
		Courses course = new Courses("C# Programming", 
									LocalDate.of(2021, 8, 1), 
									LocalDate.of(2021, 8, 15),
									LocalDate.of(2021, 9, 1),
									3,
									20,
									"Very nice course",
									CourseStatus.UPCOMING);
		course.setCourseID((long)2);
		
		Users stu1 = new Users("abc@gmail.com", "123456", Roles.STUDENT);
		Users stu2 = new Users("cba@gmail.com", "654321", Roles.STUDENT);
		
		StudentCourseDetails scd1 = new StudentCourseDetails(LocalDate.now(), 
															null, 
															EnrolmentStatus.PENDING,
															stu1,
															course);
		StudentCourseDetails scd2 = new StudentCourseDetails(LocalDate.now(), 
				null, 
				EnrolmentStatus.PENDING,
				stu2,
				course);
		doReturn(Arrays.asList(scd1, scd2)).when(scdrepo).findById((long) 2);

		// Execute the service call
		List<StudentCourseDetails> results = stuservice.findEnrolmentByCourseID((long) 2);
		
		// Assert the response
		Assertions.assertNotNull(results, "The results should not be null");
		Assertions.assertEquals(2, results.size(), "findEnrolmentByCourseID should return 2 enrolments");
		
	}
	
	@Test
	@DisplayName("Test findEnrolmentByUserID Success")
	public void testFindEnrolmentByByUserID() {
		
		// Setup our mock repository

		
		Courses course1 = new Courses("C# Programming", 
				LocalDate.of(2021, 8, 1), 
				LocalDate.of(2021, 8, 15),
				LocalDate.of(2021, 9, 1),
				3,
				20,
				"Very nice course",
				CourseStatus.UPCOMING);
				course1.setCourseID((long)2);

				Users stu1 = new Users("abc@gmail.com", "123456", Roles.STUDENT);
				stu1.setUserID((long) 1);

				StudentCourseDetails scd1 = new StudentCourseDetails(LocalDate.now(), 
											null, 
											EnrolmentStatus.PENDING,
											stu1,
											course1);		
		
			// Execute the service call
			List<StudentCourseDetails> results = stuservice.findEnrolmentByUserID((long) 1);
			
			// Assert the response
			Assertions.assertNotNull(results, "The results should not be null");
			Assertions.assertEquals(1, results.size(), "findEnrolmentByUserID should return 1 enrolment");
	}

}

