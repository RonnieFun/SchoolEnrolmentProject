package sg.edu.iss.caps.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;

public interface StudentInterface {
	
	List<Users> findAllUsersByRole(Roles role);

	List<Courses> findCoursesByRole(Roles role);

	

}
