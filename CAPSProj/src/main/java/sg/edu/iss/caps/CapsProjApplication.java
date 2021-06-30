package sg.edu.iss.caps;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.StudentCourseDetailsRepository;
import sg.edu.iss.caps.repo.UsersRepository;

@SpringBootApplication
public class CapsProjApplication {
	
	@Autowired
	UsersRepository urepo;

	@Autowired
	CoursesRepository coursesRepository;
	
	@Autowired
	StudentCourseDetailsRepository screpo;
	
	public static void main(String[] args) {
		SpringApplication.run(CapsProjApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> { 
//			Users user = new Users("test@gmail.com", "1234", Roles.STUDENT);
//			urepo.save(user);

			
			List<StudentCourseDetails> scCollection = new ArrayList<StudentCourseDetails>();
			List<Users> userCollection = new ArrayList<Users>();
			
			Courses cSharpProgramming = new Courses("C# Programming", LocalDate.of(2021, 6, 30), 
					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 6, 10), 8, 50, "Nice Course", CourseStatus.ONGOING, 
					scCollection, userCollection);
			
			Courses digitalProductManagement = new Courses("Digital Product Management", LocalDate.of(2021, 3, 15), 
					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 8, 15), 4, 60, "Nice Course", CourseStatus.ONGOING, 
					scCollection, userCollection);
			
			Courses javaProgramming = new Courses("Java Programming", LocalDate.of(2021, 6, 30), 
					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 10, 22), 8, 40, "Very Nice Course", CourseStatus.ONGOING, 
					scCollection, userCollection);
			
			Courses machineLearning = new Courses("Machine Learning", LocalDate.of(1999, 5, 2), 
					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 12, 30), 6, 100, "Very Nice Course", CourseStatus.ONGOING, 
					scCollection, userCollection);
			
			coursesRepository.save(cSharpProgramming);
			coursesRepository.save(digitalProductManagement);
			coursesRepository.save(javaProgramming);
			coursesRepository.save(machineLearning);

			List<Courses> cSharpCourses = new ArrayList<Courses>();
			cSharpCourses.add(cSharpProgramming);
			
			List<Courses> DPM = new ArrayList<Courses>();
			DPM.add(digitalProductManagement);
			
			List<Courses> javaCourses = new ArrayList<Courses>();
			javaCourses.add(javaProgramming);
			
			List<Courses> ML = new ArrayList<Courses>();
			ML.add(machineLearning);
			
			Users student1 = new Users("Max", "Chen", "max@gmail.com", "abc", Roles.STUDENT, "999", 
					"Blk 54 #12-123, Singapore 123456", LocalDate.of(1988, 1, 30), "Mr", scCollection, DPM);
			urepo.save(student1);

			Users student2 = new Users("Cher Wah", "Tan", "cherwah@gmail.com", "abcde", Roles.STUDENT, "425",
					"Blk 123, #07-31, Singapore 564123", LocalDate.of(1993, 4, 25), "Mr", scCollection, cSharpCourses);
			urepo.save(student2);
			
			Users student3 = new Users("Tri Tin", "Nguyen", "tin@gmail.com", "abcde", Roles.STUDENT, "123123",
					"Blk 654, #12-34, Singapore 564101", LocalDate.of(2000, 5, 25), "Mr", scCollection, javaCourses);
			urepo.save(student3);
			
			Users student4 = new Users("Yuen Kwan", "Chia", "chia@gmail.com", "abcde", Roles.STUDENT, "5454",
					"Blk 754, #11-12, Singapore 543532", LocalDate.of(2001, 6, 19), "Mr", scCollection, javaCourses);
			urepo.save(student4);
			
			Users student5 = new Users("Kevin", "Goh", "chia@gmail.com", "abcde", Roles.STUDENT, "5454",
					"Blk 754, #11-12, Singapore 543532", LocalDate.of(2001, 6, 19), "Mr", scCollection, ML);
			urepo.save(student5);
			
			Users student6 = new Users("Alfred", "Wang", "chia@gmail.com", "abcde", Roles.STUDENT, "5454",
					"Blk 754, #11-12, Singapore 543532", LocalDate.of(2001, 6, 21), "Mr", scCollection, javaCourses);
			urepo.save(student6);
			
			Users lecturer1 = new Users("Rurouni", "Kenshin", "Kenshin@gmail.com", "abewq", Roles.LECTURER, "1234",
					"Blk 5467, #11-12, Singapore 543532", LocalDate.of(2000, 6, 19), "Mr", scCollection, ML);
			urepo.save(lecturer1); 
			
			Users lecturer2 = new Users("Demon", "Slayer", "slayer@gmail.com", "abewq", Roles.LECTURER, "1234",
					"Blk 123, #11-12, Singapore 543532", LocalDate.of(2020, 6, 15), "Mr", scCollection, javaCourses);
			urepo.save(lecturer2);
			
			Users lecturer3 = new Users("Naught", "Jugger", "juggernaught@gmail.com", "abewq", Roles.LECTURER, "1234",
					"Blk 854, #11-12, Singapore 5412332", LocalDate.of(2000, 6, 9), "Mr", scCollection, DPM);
			urepo.save(lecturer3);
			
			Users lecturer4 = new Users("Blade", "Maiden", "blademaiden@gmail.com", "abewq", Roles.LECTURER, "1234",
					"Blk 854, #11-12, Singapore 5412332", LocalDate.of(2000, 6, 9), "Mr", scCollection, cSharpCourses);
			urepo.save(lecturer4);
					
			userCollection.add(student1);
			userCollection.add(student2);
			userCollection.add(student3);
			userCollection.add(student4);
			userCollection.add(student5);
			userCollection.add(student6);
			userCollection.add(lecturer1);
			userCollection.add(lecturer2);
			userCollection.add(lecturer3);
			userCollection.add(lecturer4);
		
			StudentCourseDetails studentCourseDetail1 = new StudentCourseDetails(LocalDate.of(2021, 01, 19), "A+", 
				EnrolmentStatus.ACCEPTED, student1, digitalProductManagement);
		
			StudentCourseDetails studentCourseDetail2 = new StudentCourseDetails(LocalDate.of(2021, 12, 10), "B", 
					EnrolmentStatus.ACCEPTED, student2, cSharpProgramming);
	
			StudentCourseDetails studentCourseDetail3 = new StudentCourseDetails(LocalDate.of(2021, 01, 10), "D", 
					EnrolmentStatus.ACCEPTED, student3, javaProgramming);
			
			StudentCourseDetails studentCourseDetail4 = new StudentCourseDetails(LocalDate.of(2021, 05, 21), "B+", 
					EnrolmentStatus.ACCEPTED, student4, javaProgramming);

			StudentCourseDetails studentCourseDetail5 = new StudentCourseDetails(LocalDate.of(2020, 03, 11), "B-", 
					EnrolmentStatus.ACCEPTED, student5, machineLearning);
		
			StudentCourseDetails studentCourseDetail6 = new StudentCourseDetails(LocalDate.of(2019, 03, 12), "A-", 
					EnrolmentStatus.ACCEPTED, student6, javaProgramming);
			
			scCollection.add(studentCourseDetail1);
			scCollection.add(studentCourseDetail2);
			scCollection.add(studentCourseDetail3);
			scCollection.add(studentCourseDetail4);
			scCollection.add(studentCourseDetail5);
			scCollection.add(studentCourseDetail6);
			
			screpo.save(studentCourseDetail1);
			screpo.save(studentCourseDetail2);
			screpo.save(studentCourseDetail3);
			screpo.save(studentCourseDetail4);
			screpo.save(studentCourseDetail5);
			screpo.save(studentCourseDetail6);
		};
	}

}

