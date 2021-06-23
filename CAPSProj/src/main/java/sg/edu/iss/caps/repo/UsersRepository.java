package sg.edu.iss.caps.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
