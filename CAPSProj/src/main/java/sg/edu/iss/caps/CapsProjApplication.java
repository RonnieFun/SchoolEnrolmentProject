package sg.edu.iss.caps;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.UsersRepository;

@SpringBootApplication
public class CapsProjApplication {
	
	@Autowired
	UsersRepository urepo;

	public static void main(String[] args) {
		SpringApplication.run(CapsProjApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args -> { 
//			Users user = new Users("test@gmail.com", "1234", Roles.STUDENT);
//			urepo.save(user);
			
//			Users u1 = new Users("Alejandro", "Hou Lu", "alejandro@gmail.com", "123", 
//					Roles.STUDENT, "90685997", "123 street", LocalDate.of(1990, 03, 04), "Mr");
//			urepo.save(u1);
//			
//			Users u2 = new Users("Aloysius", "Chee", "aloysius@gmail.com", "aaa", 
//					Roles.STUDENT, "91111111", "a street", LocalDate.of(1996, 06, 02), "Mr");
//			Users u3 = new Users("Metilda", "Chee", "Metilda@gmail.com", "bbb", 
//					Roles.STUDENT, "92222222", "b street", LocalDate.of(1997, 12, 22), "Ms");
//			Users u4 = new Users("Stephanie", "Chee", "Stephanie@gmail.com", "ccc", 
//					Roles.STUDENT, "93333333", "c street", LocalDate.of(1992, 8, 15), "Mr");
//			Users u5 = new Users("Tin", "Nyugen", "Tin@gmail.com", "ddd", 
//					Roles.LECTURER, "94444444", "d street", LocalDate.of(1980, 06, 07), "Mr");
//			Users u6 = new Users("Cher Wah", "Tan", "Cherwah@gmail.com", "eee", 
//					Roles.LECTURER, "95555555", "e street", LocalDate.of(1985, 04, 05), "Mr");
//			Users u7 = new Users("Esther", "Tan", "Esther@gmail.com", "fff", 
//					Roles.LECTURER, "96666666", "f street", LocalDate.of(1970, 8, 9), "Mrs");
//			Users u8 = new Users("Suriya", "kanthan", "Suriya@gmail.com", "ggg", 
//					Roles.LECTURER, "97777777", "g street", LocalDate.of(1972, 10, 12), "Mrs");
//			Users u9 = new Users("Fan", "Liu", "Liufan@gmail.com", "hhh", 
//					Roles.LECTURER, "98888888", "h street", LocalDate.of(1987, 01, 02), "Ms");
//			Users u10 = new Users("Felicitas", "Tan", "felicitas@gmail.com", "iii", 
//					Roles.LECTURER, "99999999", "i street", LocalDate.of(1969, 07, 28), "Mrs");
//			Users u11 = new Users("Boon Kee", "Lee", "boonkee@gmail.com", "jjj", 
//					Roles.LECTURER, "90000000", "j street", LocalDate.of(1974, 11, 26), "Mr");
//			Users u12 = new Users("Max", "Chen", "Max@gmail.com", "kkk", 
//					Roles.LECTURER, "91222222", "k street", LocalDate.of(1986, 05, 12), "Mr");
//			Users u13 = new Users("Brandon", "Chia", "brandon@gmail.com", "lll", 
//					Roles.LECTURER, "91333333", "l street", LocalDate.of(1991, 04, 21), "Mr");
//			urepo.save(u2);urepo.save(u3);urepo.save(u4);urepo.save(u5);urepo.save(u6);urepo.save(u7);
//			urepo.save(u8);urepo.save(u9);urepo.save(u10);urepo.save(u11);
//			urepo.save(u12);urepo.save(u13);
//			Users u14 = new Users("Qi", "Zhao", "zhaoqi@gmail.com", "mmm", 
//					Roles.STUDENT, "91333333", "m street", LocalDate.of(1989, 9, 04), "Ms");
//			
//			Users u15 = new Users("Phyu Sin", "Aye", "phyusin@gmail.com", "nnn", 
//					Roles.STUDENT, "91444444", "n street", LocalDate.of(1994, 02, 17), "Ms");
//			
//			Users u16 = new Users("Ronnie", "Fun", "Ronnie@gmail.com", "ooo", 
//					Roles.STUDENT, "91555555", "o street", LocalDate.of(1986, 01, 18), "Mr");
//			
//			Users u17 = new Users("Willard", "Toh", "willard@gmail.com", "ppp", 
//					Roles.STUDENT, "91666666", "p street", LocalDate.of(1993, 10, 14), "Mr");
//			
//			Users u18 = new Users("Kim Son", "Troung", "kimson@gmail.com", "qqq", 
//					Roles.STUDENT, "91666666", "q street", LocalDate.of(1988, 05, 02), "Mr");
//			
//			Users u19 = new Users("Zerita", "Lim", "zerita@gmail.com", "rrr", 
//					Roles.STUDENT, "91777777", "r street", LocalDate.of(1994, 06, 21), "Ms");
//			
//			Users u20 = new Users("Austin", "Ma", "Austin@gmail.com", "sss", 
//					Roles.STUDENT, "91888888", "s street", LocalDate.of(1991, 02, 01), "Mr");
//			
//			Users u21 = new Users("John", "Tan", "John@gmail.com", "ttt", 
//					Roles.LECTURER, "91999999", "t street", LocalDate.of(1960, 04, 13), "Mr");
//			
//			Users u22 = new Users("Tom", "Tan", "Tom@gmail.com", "uuu", 
//					Roles.LECTURER, "92111111", "u street", LocalDate.of(1973, 9, 02), "Mr");
//			
//			Users u23 = new Users("Michelle", "Koh", "michelle@gmail.com", "vvv", 
//					Roles.LECTURER, "92222222", "v street", LocalDate.of(1965, 11, 8), "Mrs");
//			urepo.save(u14);urepo.save(u15);urepo.save(u16);urepo.save(u17);urepo.save(u18);urepo.save(u19);
//			urepo.save(u20);urepo.save(u21);urepo.save(u22);urepo.save(u23);
			
//			Users u24 = new Users("Thomas", "Lim", "thomas@gmail.com", "www", 
//				Roles.ADMIN, "92333333", "w street", LocalDate.of(1964, 3, 28), "Mr");
//			Users u25 = new Users("Scarlet", "Johansson", "scarlet@gmail.com", "xxx", 
//					Roles.ADMIN, "92444444", "x street", LocalDate.of(1985, 2, 21), "Ms");
//			Users u26 = new Users("Angela", "baby", "angela@gmail.com", "yyy", 
//					Roles.ADMIN, "92555555", "y street", LocalDate.of(1992, 04, 20), "Ms");
//			Users u27 = new Users("Jennie", "Kim", "jennie@gmail.com", "zzz", 
//					Roles.ADMIN, "92666666", "z street", LocalDate.of(1997, 5, 19), "Ms");
//			urepo.save(u24);urepo.save(u25);urepo.save(u26);urepo.save(u27);

			
		};
	}

}
