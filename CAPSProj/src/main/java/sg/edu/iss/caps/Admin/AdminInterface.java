package sg.edu.iss.caps.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;

public interface AdminInterface {
	
	public void createUser(Users user);
	public void updateUser(Users user);
	public List<Users> listByRole(Roles role);
	public Page<Users> listAllUsers(int pageNumber, String sortField, String sortDir);
	public List<Users> listUsers();
	public void deleteUser(Long id);
	public Users findByName(String name);
	public Users findById(Long ID);
	public String passwordGenerator();

}
