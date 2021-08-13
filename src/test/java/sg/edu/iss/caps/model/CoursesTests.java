package sg.edu.iss.caps.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.caps.CapsProjApplication;
import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsProjApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CoursesTests {

	@Autowired
	private CoursesRepository crepo;
	
	@Test
	@Order (1)
	public void testListCourses() {
		//given
		List<Courses> list = new ArrayList<Courses>();
		
		//when
		list = crepo.findAll();
		
		//then
		assertTrue(list.size() > 0);
	}
	
	
	@Test
	@Order (2)
	public void testfindCoursesByRoleAndId() {
		//given
		List<Courses> list = new ArrayList<Courses>();
		
		//when
		list = crepo.findCoursesByRoleAndId(Roles.LECTURER, 103L);
		
		//then
		assertTrue(list.size() > 0);
	}
	
	@Test
	@Order (3)
	public void testfindCoursesByCourseId() {
		//given
		List<Courses> list = new ArrayList<Courses>();
		
		//when
		list = crepo.findCoursesByCourseId(1L);
		
		//then
		assertTrue(list.size() > 0);
	}
		
	@Test
	@Order (4)
	public void testfindCoursesByLecturerId() {
		//given
		List<Courses> list = new ArrayList<Courses>();
		
		//when
		list = crepo.findCoursesByLecturerId(103L);
		
		//then
		assertTrue(list.size() > 0);
	}

}
