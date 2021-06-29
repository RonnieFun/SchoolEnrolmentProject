package sg.edu.iss.caps.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;


public interface UsersRepository extends JpaRepository<Users, Long> {
	
	@Query("SELECT u FROM Users u WHERE u.role=:role")
	List<Users> findByRole(@Param("role") Roles role);
	
	public Users findUserByfirstNameAndLastName(String firstName, String lastName);
	
	Page<Users> findByRole(Roles role, Pageable pageable);
	
	public Users getUserByEmail(String email);

}
