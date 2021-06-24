package sg.edu.iss.caps;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			Users user = new Users("test@gmail.com", "1234", Roles.STUDENT);
			urepo.save(user);
			
			Users user1 = new Users("Max", "Chen", "max@gmail.com");
			urepo.save(user1);
////			
			Users user2 = new Users("Mark", "Zuckerberg", "mark@gmail.com");
			urepo.save(user2);
		
			
			StudentCourseDetails test = new StudentCourseDetails(LocalDate.of(2021, 07, 21), "A", EnrolmentStatus.ACCEPTED);
			screpo.save(test);
//			
			Courses course1 = new Courses("C# Programming By Tin", 100, "GDipSA 52");
			coursesRepository.save(course1);
////			
			Courses course2 = new Courses("Java by Suriya", 80, "GDipSA 52");
			coursesRepository.save(course2);
//			
			Courses course3 = new Courses("Android by Cher Wah", 90, "GDipSA 52");
			coursesRepository.save(course3);
			
			Courses course4 = new Courses("C# Foundations by Liu Fan", 120, "GDipSA 51");
			coursesRepository.save(course4);
			
			Courses course5 = new Courses("Machine Learning by Yuen Kwan", 80, "GDipSA 51");
			coursesRepository.save(course5);
			
			Courses course6 = new Courses("DPM by Felicitas", 60, "GDipSA 53");
			coursesRepository.save(course6);
			
			Courses course7 = new Courses("Software Design & Analysis by Esther", 70, "GDipSA 54");
			coursesRepository.save(course7);
			
			Courses course8 = new Courses("Breakdance by Kevin Goh", 55, "GDipSA 50");
			coursesRepository.save(course8);
			
			Courses course9 = new Courses("Trolling by Jim Ho", 45, "GDipSA 55");
			coursesRepository.save(course8);
		};
	}

}
