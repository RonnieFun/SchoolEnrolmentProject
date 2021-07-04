package sg.edu.iss.caps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class UserValidationService {

	@Autowired
	private UsersRepository urepo;

	public String validateUserEmail(Users user) {

		String message = "";

		if (urepo.getUserByEmail(user.getEmail()) != null) {
			if (urepo.getUserByEmail(user.getEmail()) != urepo.getById(user.getUserID())) {
				message = "An account associated with the email is already register to a user.";
			}
		}

		return message;

	}
}
