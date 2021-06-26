package sg.edu.iss.caps.Admin;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class AdminImplementation implements AdminInterface{
	
	@Autowired
	UsersRepository urepo;

	@Transactional
	public void createUser(Users user) {
		// TODO Auto-generated method stub
		urepo.save(user);
		
	}

	@Transactional
	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		urepo.save(user);
		
	}

	@Transactional
	public List<Users> listByRole(Roles role) {
		// TODO Auto-generated method stub
		return urepo.findByRole(role);
	}

	@Transactional
	public void deleteUser(Users user) {
		// TODO Auto-generated method stub
		urepo.delete(user);		
	}

	@Transactional
	public Users findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<Users> listAllUsers() {
		// TODO Auto-generated method stub
		return urepo.findAll();
	}


}
