package sg.edu.iss.caps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	
	@Autowired
	UsersRepository urepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = urepo.getUserByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException("User does not exist");
		}
		return new MyUserDetails(user);
	}

}
