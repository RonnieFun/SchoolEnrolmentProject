package sg.edu.iss.caps.service;

import java.util.List;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;

public interface StudentInterface {
	
	List<Users> findAllUsersByRole(Roles role);
	



	

}
