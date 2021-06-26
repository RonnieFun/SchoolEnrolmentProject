package sg.edu.iss.caps.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class StudentImplementation implements StudentInterface {

	@Autowired
	UsersRepository urepo;

	@Transactional
	public List<Users> findAllUsersByRole(Roles role) {
		
		return urepo.findByRole(role);
	}
}