package sg.edu.iss.caps.Admin;

import java.util.List;

import org.springframework.data.domain.Page;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;

public interface AdminInterface {
	
	public void createUser(Users user);
	public void updateUser(Users user);
	public List<Users> listByRole(Roles role);
	public Page<Users> listAllUsers(int pageNumber, String sortField, String sortDir);
	public List<Users> listUsers();
	public void deleteUser(Users user);
	public Users findByName(String name);

}
