package sg.edu.iss.caps.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.bytebuddy.utility.RandomString;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.AdminInterface;
import sg.edu.iss.caps.service.UserNotFoundException;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	AdminInterface adservice;
	
	@Autowired
	JavaMailSender mailSender;
	
	//display forget password form
	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		model.addAttribute("pageTitle", "Forgot Password");
		return"forgot_password_form";
	}
	
	//update password
	@PostMapping("/forgot_password")
	public String processForgotPasswordForm(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token =RandomString.make(45);
		
		System.out.println("Email: " + email);
		System.out.println("Token: " + token);
		try {
		adservice.updateResetPasswordToken(token, email);
		
		String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
		sendEmail(email, resetPasswordLink);
		
		model.addAttribute("message","We have sent a reset password link to your email, please check.");
		
		System.out.println(resetPasswordLink);
		
		} catch(UserNotFoundException ex) {
			model.addAttribute("error",ex.getMessage());
		} catch(UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error","Error while sending email.");
		} 
		
		model.addAttribute("pageTitle", "Forgot Password");
		return"forgot_password_form";
	}
	
	//email send function
	private void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("contact@gmail.com" , "Support");
		helper.setTo(email);
		
		String subject = "Here's the link to reset your password";
		String content = "<p>Hello,</p>" + "You have requested to reset your password. </p>"
						+ "<p>Click the link below to change your password: </p>"
						+ "<p><b><a href=\"" + resetPasswordLink + "\">Change my password</a></b></p>"
						+ "<p>Ignore this email if you do remember your password, or you have not made the request</p>";
						
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
	}
	
	//reset password
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token")String token, Model model) {
		
		Users user = adservice.get(token);
		if(user ==null) {
			model.addAttribute("title","Reset your password");
			model.addAttribute("message", "Invalid Token");
			return "message";
		}
		
		model.addAttribute("token", token);
		model.addAttribute("pageTitle","Reset your password");
		return "reset_password_form";
		
	}
	
	@RequestMapping("/reset_password")
	public String processRestPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");
		
		Users user = adservice.get(token);
		adservice.updatePassword(user, password);
		
		model.addAttribute("message", "You have successfully changed your password.");
		
		return "message";
	}
	

}
