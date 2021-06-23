package sg.edu.iss.caps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.repo.UsersRepository;

@Service
public class HomeImplementation implements HomeInterface{

	@Autowired
	UsersRepository urepo;
	
	
}
