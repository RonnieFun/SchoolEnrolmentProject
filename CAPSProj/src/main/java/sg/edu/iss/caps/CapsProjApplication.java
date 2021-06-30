package sg.edu.iss.caps;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;
import sg.edu.iss.caps.repo.UsersRepository;
import sg.edu.iss.caps.service.LecturerInterface;
import sg.edu.iss.caps.service.StudentInterface;

@SpringBootApplication
public class CapsProjApplication {

	@Autowired
	UsersRepository urepo;

	@Autowired
	CoursesRepository cRepository;

	@Autowired
	StudentCourseDetailsRepository screpo;

	@Autowired
	LecturerInterface leservice;

	public static void main(String[] args) {
		SpringApplication.run(CapsProjApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			System.out.println("Adding Users");
			System.out.println("Loading Users");
			List<List<String>> userscsv = new ArrayList<List<String>>();
			try (CSVReader csvReader = new CSVReaderBuilder(new FileReader("populatedata/populateusers.csv"))
					.withSkipLines(1).build();) {
				String[] values = null;
				while ((values = csvReader.readNext()) != null) {
					userscsv.add(Arrays.asList(values));
				}
				System.out.println("Loaded Users csv");
			}

			for (int i = 0; i < userscsv.size(); i++) {
				Users user = new Users();

				user.setFirstName(userscsv.get(i).get(0));
				user.setLastName(userscsv.get(i).get(1));
				user.setEmail(userscsv.get(i).get(2));
				BCryptPasswordEncoder password = new BCryptPasswordEncoder();
				user.setPassword(password.encode(userscsv.get(i).get(3)));
				user.setRole(Roles.valueOf(userscsv.get(i).get(4)));
				user.setPhoneNumber(userscsv.get(i).get(5));
				user.setAddress(userscsv.get(i).get(6));
				user.setBirthday(LocalDate.of(
						Integer.parseInt(userscsv.get(i).get(7)),
						Integer.parseInt(userscsv.get(i).get(8)), 
						Integer.parseInt(userscsv.get(i).get(9))));
				user.setEnabled(true);
				user.setSalutation(userscsv.get(i).get(11));

				urepo.save(user);

			}
			System.out.println("Users Added");

			System.out.println("Adding Courses");
			System.out.println("Loading Courses");
			List<List<String>> coursescsv = new ArrayList<List<String>>();
			try (CSVReader csvReader2 = new CSVReaderBuilder(new FileReader("populatedata/populatecoursesfinal.csv"))
					.withSkipLines(2).build();) {
				String[] values2 = null;
				while ((values2 = csvReader2.readNext()) != null) {
					coursescsv.add(Arrays.asList(values2));
				}
				System.out.println("Loaded Users csv");
			}

			for (int i = 0; i < coursescsv.size(); i++) {
				Courses course = new Courses();

				course.setCourseName(coursescsv.get(i).get(0));
				course.setCourseStartDate(LocalDate.of(
						Integer.parseInt(coursescsv.get(i).get(1)),
						Integer.parseInt(coursescsv.get(i).get(2)), 
						Integer.parseInt(coursescsv.get(i).get(3))));
				course.setCourseEndDate(LocalDate.of(
						Integer.parseInt(coursescsv.get(i).get(4)),
						Integer.parseInt(coursescsv.get(i).get(5)), 
						Integer.parseInt(coursescsv.get(i).get(6))));
				course.setExamDate(LocalDate.of(
						Integer.parseInt(coursescsv.get(i).get(7)),
						Integer.parseInt(coursescsv.get(i).get(8)), 
						Integer.parseInt(coursescsv.get(i).get(9))));
				course.setCredits(Integer.parseInt(coursescsv.get(i).get(10)));
				course.setCourseCapacity(Integer.parseInt(coursescsv.get(i).get(11)));
				course.setDescription(coursescsv.get(i).get(12));
				course.setCourseStatus(CourseStatus.valueOf(coursescsv.get(i).get(13)));

				cRepository.save(course);
			}
			System.out.println("Courses Added");

			System.out.println("Assigning Lecturer to Courses");
			List<List<String>> lecturercsv = new ArrayList<List<String>>();
			try (CSVReader csvReader3 = new CSVReaderBuilder(new FileReader("populatedata/assignlecturer.csv"))
					.withSkipLines(1).build();) {
				String[] values3 = null;
				while ((values3 = csvReader3.readNext()) != null) {
					lecturercsv.add(Arrays.asList(values3));
				}
				System.out.println("Loaded Lecturer csv");
			}

			for (int i = 0; i < lecturercsv.size(); i++) {

				Courses course = new Courses();
				course = cRepository.getById(Long.parseLong(lecturercsv.get(i).get(1)));

				leservice.addCourseTaught(Long.parseLong(lecturercsv.get(i).get(0)), course);

			}
			System.out.println("Lecturers assigned to course");

			System.out.println("Assigning Student to Courses");
			List<List<String>> studentcsv = new ArrayList<List<String>>();
			try (CSVReader csvReader4 = new CSVReaderBuilder(new FileReader("populatedata/assignstudents.csv"))
					.withSkipLines(2).build();) {
				String[] values4 = null;
				while ((values4 = csvReader4.readNext()) != null) {
					studentcsv.add(Arrays.asList(values4));
				}
				System.out.println("Loaded Student csv");
			}System.out.println("Assigning Student to courses");

			for (int i = 0; i < studentcsv.size(); i++) {
				
				StudentCourseDetails scdetails = new StudentCourseDetails();
				scdetails.setDateOfEnrollment(LocalDate.of(
						Integer.parseInt(studentcsv.get(i).get(0)),
						Integer.parseInt(studentcsv.get(i).get(1)), 
						Integer.parseInt(studentcsv.get(i).get(2))));
				scdetails.setGrades(studentcsv.get(i).get(3));
				scdetails.setEnrolmentStatus(EnrolmentStatus.valueOf(studentcsv.get(i).get(4)));
				scdetails.setStudent(urepo.getById(Long.parseLong(studentcsv.get(i).get(5))));
				scdetails.setCourse(cRepository.getById(Long.parseLong(studentcsv.get(i).get(6))));				
				
				screpo.save(scdetails);
				
			}System.out.println("Finished Assigning Student to courses");
			
			System.out.println("App Ready");

//			Users user = new Users("test@gmail.com", "1234", Roles.STUDENT);
//			urepo.save(user);
//			
//			Courses course1 = new Courses("C# Programming", LocalDate.of(2021, 6, 30), 
//					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 12, 29), 15, 30, "Nice Course", CourseStatus.ONGOING);
//
//			
//			Courses course2 = new Courses("Java Programming", LocalDate.of(2021, 6, 30), 
//					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 12, 29), 15, 30, "Very Nice Course", CourseStatus.ONGOING);
//			
//			Courses course3 = new Courses("Java Programming Advanced", LocalDate.of(2021, 6, 30), 
//					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 12, 29), 15, 30, "Very Nice Course", CourseStatus.ONGOING);
//			
//			Courses course4 = new Courses("Python", LocalDate.of(2021, 5, 10), 
//					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 12, 29), 15, 10, "Python courses for ml", CourseStatus.ONGOING);
//			
//			
//			coursesRepository.save(course1);
//			coursesRepository.save(course2);
//			coursesRepository.save(course3);
//			coursesRepository.save(course4);
//			
//			List<Courses> cSharpCourse = new ArrayList<Courses>();
//			cSharpCourse.add(course1);
//			
//			List<Courses> PythonAndML = new ArrayList<Courses>();
//			PythonAndML.add(course4);
//
//			
//			List<Courses> javaCourse = new ArrayList<Courses>();
//			javaCourse.add(course2);
//			javaCourse.add(course3);
//			
//			Users user1 = new Users("Max", "Chen", "max@gmail.com", "abc", Roles.STUDENT, "999", 
//					"Blk 54 #12-123, Singapore 123456", LocalDate.of(1988, 1, 30), "Mr");
//			urepo.save(user1);
//
//			
//			Users user2 = new Users("Cher Wah", "Tan", "cherwah@gmail.com", "abcde", Roles.LECTURER, "425",
//					"Blk 123, #07-31, Singapore 564123", LocalDate.of(1993, 4, 25), "Mr");
//			urepo.save(user2);
//			
//			Users user3 = new Users("Tri Tin", "Nguyen", "tin@gmail.com", "abcde", Roles.STUDENT, "123123",
//					"Blk 654, #12-34, Singapore 564101", LocalDate.of(2000, 5, 25), "Mr");
//			urepo.save(user3);
//			
//			Users user4 = new Users("Yuen Kwan", "Chia", "chia@gmail.com", "abcde", Roles.STUDENT, "5454",
//					"Blk 754, #11-12, Singapore 543532", LocalDate.of(2001, 6, 19), "Mr");
//			urepo.save(user4);
//			
//			Users user5 = new Users("Kevin", "Goh", "chia@gmail.com", "abcde", Roles.LECTURER, "5454",
//					"Blk 754, #11-12, Singapore 543532", LocalDate.of(2001, 6, 19), "Mr");
//			urepo.save(user5);
//			
//			Users user6 = new Users("Alfred", "Wang", "chia@gmail.com", "abcde", Roles.LECTURER, "5454",
//					"Blk 754, #11-12, Singapore 543532", LocalDate.of(2001, 6, 19), "Mr");
//			urepo.save(user6);
//			
//			
//			StudentCourseDetails studentCourseDetail1 = new StudentCourseDetails(LocalDate.of(2021, 07, 21), "A", 
//					EnrolmentStatus.ACCEPTED, user1, course1);
//			
//			StudentCourseDetails studentCourseDetail2 = new StudentCourseDetails(LocalDate.of(2021, 07, 21), "A", 
//					EnrolmentStatus.ACCEPTED, user1, course4);
//			
//			
//			StudentCourseDetails studentCourseDetail3 = new StudentCourseDetails(LocalDate.of(2021, 12, 10), "B", 
//					EnrolmentStatus.REJECTED, user3, course2);
//			
//			StudentCourseDetails studentCourseDetail4 = new StudentCourseDetails(LocalDate.of(2021, 07, 19), "D", 
//					EnrolmentStatus.ACCEPTED, user4, course2);
//			
//			
//
//			screpo.save(studentCourseDetail1);
//			screpo.save(studentCourseDetail2);
//			screpo.save(studentCourseDetail3);
//			screpo.save(studentCourseDetail4);
//
//			
//			user2.setCourses(javaCourse);
//			user2.setCourses(PythonAndML);
//			user5.setCourses(cSharpCourse);		
//			urepo.save(user2);
//			urepo.save(user5);

		};
	}

}
