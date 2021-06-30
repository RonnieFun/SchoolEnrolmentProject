package sg.edu.iss.caps.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.AdminInterface;
import sg.edu.iss.caps.service.HomeInterface;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	AdminInterface adservice;

	@Autowired
	public void setAdminInterface(AdminInterface ads) {
		this.adservice = ads;
	}
	
	@Autowired
	HomeInterface hservice;
	
	@Autowired
	public void setHomeInterface(HomeInterface hs) {
		this.hservice = hs;
	}

	@RequestMapping("/login")
	public String login(Model model) {
		Users u = new Users();
		model.addAttribute("user", u);
		return "login";
	}
	
	@RequestMapping("/403")
	public String ForbiddenError() {
		return "403";
	}
	
	@RequestMapping("/edituser/{id}")
	public String showEditProfileForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("user", adservice.findById(id));
		return "userProfileForm";
	}
	
	@RequestMapping("/profile/save")
	public String saveProfile(@ModelAttribute("user") @Valid Users user, 
			BindingResult bindingResult, Model model,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setProfilePicture(fileName);
			
			BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
			user.setPassword(pass.encode(user.getPassword()));
			String password = user.getPassword();

		model.addAttribute("password", password);
		Users savedUser = adservice.save(user);
		
		String uploadDir = "./profile-pic/" + savedUser.getUserID();
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		try {
			InputStream inputStream = multipartFile.getInputStream();
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			
			throw new IOException("Could not save uploaded file: " + fileName);
		}
		
		return "updateSuccess";
	}
}
