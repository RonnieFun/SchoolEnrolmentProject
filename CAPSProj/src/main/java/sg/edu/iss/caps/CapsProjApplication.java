package sg.edu.iss.caps;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.LecturerCourseDetails;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;

import sg.edu.iss.caps.repo.CoursesRepository;
import sg.edu.iss.caps.repo.LecturerCourseDetailsRepository;
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
	
	@Autowired
	LecturerCourseDetailsRepository lcrepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CapsProjApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> { 
//			Users user = new Users("test@gmail.com", "1234", Roles.STUDENT);
//			urepo.save(user);
			
			Courses cSharpProgramming = new Courses("C# Programming", LocalDate.of(2021, 6, 30), 
					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 12, 29), 8, 50, "Nice Course", CourseStatus.ONGOING, 
					null, null);
			
			Courses digitalProductManagement = new Courses("Digital Product Management", LocalDate.of(2021, 3, 15), 
					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 12, 29), 4, 60, "Nice Course", CourseStatus.ONGOING, 
					null, null);
			
			Courses javaProgramming = new Courses("Java Programming", LocalDate.of(2021, 6, 30), 
					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 12, 29), 8, 40, "Very Nice Course", CourseStatus.ONGOING, 
					null, null);
			
			Courses machineLearning = new Courses("Machine Learning", LocalDate.of(1999, 5, 2), 
					LocalDate.of(2021, 8, 10), LocalDate.of(2021, 12, 29), 6, 100, "Very Nice Course", CourseStatus.ONGOING, 
					null, null);
			
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
			
			Users user1 = new Users("Max", "Chen", "max@gmail.com", "abc", Roles.STUDENT, "999", 
					"Blk 54 #12-123, Singapore 123456", LocalDate.of(1988, 1, 30), "Mr", null, DPM);
			urepo.save(user1);

			Users user2 = new Users("Cher Wah", "Tan", "cherwah@gmail.com", "abcde", Roles.LECTURER, "425",
					"Blk 123, #07-31, Singapore 564123", LocalDate.of(1993, 4, 25), "Mr", null, cSharpCourses);
			urepo.save(user2);
			
			Users user3 = new Users("Tri Tin", "Nguyen", "tin@gmail.com", "abcde", Roles.STUDENT, "123123",
					"Blk 654, #12-34, Singapore 564101", LocalDate.of(2000, 5, 25), "Mr", null, javaCourses);
			urepo.save(user3);
				
			Users user4 = new Users("Yuen Kwan", "Chia", "chia@gmail.com", "abcde", Roles.STUDENT, "5454",
					"Blk 754, #11-12, Singapore 543532", LocalDate.of(2001, 6, 19), "Mr", null, javaCourses);
			urepo.save(user4);
			
			Users user5 = new Users("Kevin", "Goh", "chia@gmail.com", "abcde", Roles.LECTURER, "5454",
					"Blk 754, #11-12, Singapore 543532", LocalDate.of(2001, 6, 19), "Mr", null, ML);
			urepo.save(user5);
			
			Users user6 = new Users("Alfred", "Wang", "chia@gmail.com", "abcde", Roles.LECTURER, "5454",
					"Blk 754, #11-12, Singapore 543532", LocalDate.of(2001, 6, 21), "Mr", null, javaCourses);
			urepo.save(user6);
			
			Users user7 = new Users("Rurouni", "Kenshin", "Kenshin@gmail.com", "abewq", Roles.STUDENT, "1234",
					"Blk 5467, #11-12, Singapore 543532", LocalDate.of(2000, 6, 19), "Mr", null, ML);
			urepo.save(user7);
			
			Users user8 = new Users("Demon", "Slayer", "slayer@gmail.com", "abewq", Roles.STUDENT, "1234",
					"Blk 123, #11-12, Singapore 543532", LocalDate.of(2020, 6, 15), "Mr", null, ML);
			urepo.save(user8);
			
			Users user9 = new Users("Naught", "Jugger", "juggernaught@gmail.com", "abewq", Roles.STUDENT, "1234",
					"Blk 854, #11-12, Singapore 5412332", LocalDate.of(2000, 6, 9), "Mr", null, DPM);
			urepo.save(user9);
			
			Users user10 = new Users("Blade", "Maiden", "blademaiden@gmail.com", "abewq", Roles.STUDENT, "1234",
					"Blk 854, #11-12, Singapore 5412332", LocalDate.of(2000, 6, 9), "Mr", null, cSharpCourses);
			urepo.save(user10);
			
		
			StudentCourseDetails studentCourseDetail1 = new StudentCourseDetails(LocalDate.of(2021, 01, 19), "A+", 
				EnrolmentStatus.ACCEPTED, user1, digitalProductManagement);
		
			StudentCourseDetails studentCourseDetail2 = new StudentCourseDetails(LocalDate.of(2021, 12, 10), "B", 
					EnrolmentStatus.REJECTED, user3, javaProgramming);
	
			StudentCourseDetails studentCourseDetail3 = new StudentCourseDetails(LocalDate.of(2021, 01, 10), "D", 
					EnrolmentStatus.ACCEPTED, user4, javaProgramming);
			
			StudentCourseDetails studentCourseDetail4 = new StudentCourseDetails(LocalDate.of(2021, 05, 21), "B+", 
					EnrolmentStatus.ACCEPTED, user7, machineLearning);

			StudentCourseDetails studentCourseDetail5 = new StudentCourseDetails(LocalDate.of(2020, 03, 11), "B-", 
					EnrolmentStatus.ACCEPTED, user8, machineLearning);
			
			StudentCourseDetails studentCourseDetail6 = new StudentCourseDetails(LocalDate.of(2019, 02, 10), "C+", 
					EnrolmentStatus.REJECTED, user9, digitalProductManagement);
			
			StudentCourseDetails studentCourseDetail7 = new StudentCourseDetails(LocalDate.of(2019, 03, 12), "A-", 
					EnrolmentStatus.ACCEPTED, user10, cSharpProgramming);
			
			
			screpo.save(studentCourseDetail1);
			screpo.save(studentCourseDetail2);
			screpo.save(studentCourseDetail3);
			screpo.save(studentCourseDetail4);
			screpo.save(studentCourseDetail5);
			screpo.save(studentCourseDetail6);
			screpo.save(studentCourseDetail7);
			
			LecturerCourseDetails lecturerCourseDetail1 = new LecturerCourseDetails(cSharpCourses, user2);
			LecturerCourseDetails lecturerCourseDetail2 = new LecturerCourseDetails(javaCourses, user6);
			LecturerCourseDetails lecturerCourseDetail3 = new LecturerCourseDetails(javaCourses, user5);
			
			lcrepo.save(lecturerCourseDetail1);
			lcrepo.save(lecturerCourseDetail2);
			lcrepo.save(lecturerCourseDetail3);
		};
	}

}
