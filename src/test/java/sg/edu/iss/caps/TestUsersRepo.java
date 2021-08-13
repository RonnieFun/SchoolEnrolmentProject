package sg.edu.iss.caps;

import static org.junit.jupiter.api.Assertions.*;

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

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.UsersRepository;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TestUsersRepo {

	@Autowired
	private UsersRepository urepo;
	
	@Test
	@Order(1)
	void testCreateUser() {
		//given
		Users stu1 = new Users("abc@gmail.com", "123456", Roles.STUDENT);
		// when
		Users saved = urepo.save(stu1);
		// then
		assertNotNull(saved);
	}
	
	
	@Test
	@Order(2)
	void testGetUserByEmail() {
		
		//given
		String email= "abc@gmail.com";
		
		//when
		Users saved = urepo.getUserByEmail(email);
		
		//then
		assertNotNull(saved);
	}
	
	@Test
	@Order(3)
	public void testUpdateUser() {
		
		//given		
		String email= "abc@gmail.com";
		Users given = urepo.getUserByEmail(email);
		
		//when
		given.setEmail("def@gmail.com");
		Users saved = urepo.save(given);
		
		//then
		assertNotNull(saved);

	}	
	
	
	@Test
	@Order(4)
	public void testListUsers() {
		// given
		List<Users> list = new ArrayList<Users>();
		// when
		list = urepo.findAll();
		// then
		assertTrue(list.size() > 0);
	}
	
	@Test
	@Order(5)
	public void testDeleteUsers() {
		// given
		String email = "def@gmail.com";
		// when
		Users selected = urepo.getUserByEmail(email);
		urepo.delete(selected);
		// then
		assertTrue(urepo.getUserByEmail(email) == null);
	}

}
