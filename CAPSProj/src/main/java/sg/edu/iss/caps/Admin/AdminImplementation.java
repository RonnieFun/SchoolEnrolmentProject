package sg.edu.iss.caps.Admin;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Users findById(Long ID) {
		return urepo.findById(ID).get();
	}
	
	@Transactional
	public Users findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public Page<Users> listAllUsers(int pageNumber, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
		return urepo.findAll(pageable);
	}
	
	@Transactional
	public List<Users> listUsers(){
		return urepo.findAll();
	}
	
	


}
