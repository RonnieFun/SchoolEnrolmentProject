package sg.edu.iss.caps.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
import sg.edu.iss.caps.service.MyUserDetails;

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

	//display login
	@RequestMapping("/login")
	public String login(Model model) { 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null|| authentication instanceof AnonymousAuthenticationToken) {
			return"login";
		}
		return "redirect:/index";
		
	}
	
	// 403 customize page
	@RequestMapping("/403")
	public String ForbiddenError() {
		return "403";
	}
	
	//edit user profile
	@RequestMapping("/edituser")
	public String showEditProfileForm(Model model, 
			@AuthenticationPrincipal MyUserDetails userDetails) {
		model.addAttribute("user", adservice.findById(userDetails.getUserID()));
		return "userProfileForm";
	}
	
	//save user profile
	@RequestMapping("/profile/save")
	public String saveProfile(@Valid @ModelAttribute("user") Users user, 
			BindingResult bindingResult, Model model,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
			
		if (bindingResult.hasErrors()) {
			return "userProfileForm";
		}
		
	
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			user.setProfilePicture(fileName);
			if(user.getProfilePicture().contains("png") || user.getProfilePicture().contains("jpeg") ||
					user.getProfilePicture().contains("jpg")) {
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
	     adservice.save(user);
		return "updateSuccess";
	}
}
