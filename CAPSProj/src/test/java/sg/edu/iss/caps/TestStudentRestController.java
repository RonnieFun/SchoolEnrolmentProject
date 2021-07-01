package sg.edu.iss.caps;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.StudentInterface;

@SpringBootTest
@AutoConfigureMockMvc
public class TestStudentRestController {
	
	@MockBean
	private StudentInterface stuservice;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("Get /courses success")
	void testGetAllLectuererCourses() throws Exception {
		// Setup our mocked service
		Courses course1 = new Courses("C# Programming", 
				LocalDate.of(2021, 8, 1), 
				LocalDate.of(2021, 8, 15),
				LocalDate.of(2021, 9, 1),
				3,
				20,
				"Very nice course",
				CourseStatus.UPCOMING);
		course1.setCourseID((long) 1);
		
		Courses course2 = new Courses("Java Programming", 
				LocalDate.of(2021, 10, 1), 
				LocalDate.of(2021, 10, 15),
				LocalDate.of(2021, 11, 1),
				4,
				30,
				"Very nice programming course",
				CourseStatus.UPCOMING);
		course2.setCourseID((long) 2);
		
		Courses course3 = new Courses("Machine Learning", 
				LocalDate.of(2021, 11, 1), 
				LocalDate.of(2021, 11, 15),
				LocalDate.of(2021, 12, 1),
				4,
				30,
				"Machine Learning course is very popular",
				CourseStatus.UPCOMING);
		course3.setCourseID((long) 3);
		
		Users lecturer1 = new Users("abc@gmail.com", "123456", Roles.LECTURER);
		lecturer1.setCourses(Lists.newArrayList(course2, course3));
		Users lecturer2 = new Users("cba@gmail.com", "654321", Roles.LECTURER);
		lecturer2.setCourses(Lists.newArrayList(course2));
		
		doReturn(Lists.newArrayList(course1, course2)).when(stuservice).findCoursesByRole(Roles.LECTURER);
		
		/// Execute the GET request
        mockMvc.perform(get("/studentapi/lecturercourses"))
        
        // Validate the response code and content type
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        
        // Validate headers
        .andExpect(header().string(HttpHeaders.LOCATION, "/studentapi/lecturercourses")) 
        // Validate the returned fields
        .andExpect(jsonPath("$[0].courseID", is(2)))
        .andExpect(jsonPath("$[0].courseName", is("Java Programming")))
        .andExpect(jsonPath("$[0].courseStartDate", is(LocalDate.of(2021, 10, 1))))
        .andExpect(jsonPath("$[0].courseEndDate", is(LocalDate.of(2021, 10, 15))))
        .andExpect(jsonPath("$[0].courseExamDate", is(LocalDate.of(2021, 11, 1))))
        .andExpect(jsonPath("$[0].credits", is(4)))
        .andExpect(jsonPath("$[0].courseCapatcity", is(30)))
        
        .andExpect(jsonPath("$[0].courseID", is(3)))
        .andExpect(jsonPath("$[0].courseName", is("Machine Learning")))
        .andExpect(jsonPath("$[0].courseStartDate", is(LocalDate.of(2021, 11, 1))))
        .andExpect(jsonPath("$[0].courseEndDate", is(LocalDate.of(2021, 11, 15))))
        .andExpect(jsonPath("$[0].courseExamDate", is(LocalDate.of(2021, 12, 1))))
        .andExpect(jsonPath("$[0].credits", is(4)))
        .andExpect(jsonPath("$[0].courseCapatcity", is(30)));
	
		
	}

}
