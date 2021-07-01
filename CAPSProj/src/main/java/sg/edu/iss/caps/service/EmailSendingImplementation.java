package sg.edu.iss.caps.service;

import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Users;

@Service
public class EmailSendingImplementation implements EmailSendingInterface {

	@Autowired
	private JavaMailSender emailSender;

	@Transactional
	@Async
	public void sendCourseEnrolmentConfirmation(Courses course, Users user) {
		MimeMessage mimemessage = emailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimemessage);
		try {
			message.setFrom("auto-confirm@Team2CapsProj.edu");
			message.setTo(user.getEmail());
			message.setSubject("Confirmation on Course Enrolment for " + course.getCourseName() + " starting on " + course.getCourseStartDate().format(DateTimeFormatter.ofPattern("d MMMM yyyy")));
			message.setText("Dear " + user.getSalutation() + ' ' + user.getFirstName() + ' ' + user.getLastName() 
					+ "<br><br>Your enrolment for " + course.getCourseName() + " starting on " + course.getCourseStartDate().format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + " has been received."
					+ "<br><br> Please check our <a href='http://localhost:8080/student/enrolmentlist'>our website</a> for the status of your enrolment. "
					+ "<br><br><br><br><hr>\""
					+ "Please note: This email was sent from a notification-only address that can't accept incoming email. Please do not reply to this message.", true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		emailSender.send(mimemessage);
	}

	@Transactional
	@Async
	public void sendAccountCreatedEmail(Users user, String unhashedpassword) {
		MimeMessage mimemessage = emailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimemessage);
		try {
			message.setFrom("auto-confirm@Team2CapsProj.edu");
			message.setTo(user.getEmail());
			message.setSubject("A new " + user.getRole() + " account has been created for you on CAPS.");
			message.setText("Dear " + user.getSalutation() + ' ' + user.getFirstName() + ' ' + user.getLastName()
					+ "<br><br>A new " + user.getRole() + " account has been created for you on CAPS. Please confirm if your details are correct</h2>"
					+ "<br><br>First name: " + user.getFirstName()
					+ "<br><br>Last name: " + user.getLastName()
					+ "<br><br>Role: " + user.getRole()
					+ "<br><br>Date of Birth: " + user.getBirthday()
					+ "<br><br>Phone number: " + user.getPhoneNumber()
					+ "<br><br>Email: " + user.getEmail()
					+ "<br><br>Address: " + user.getAddress()
					+ "<br><br><br>Password: " + unhashedpassword
					+ "<br><span style=color:red> Please change the default password <a href='http://localhost:8080'>on our website</a> within 7 days.</span>"
					+ "<br><br><br><br><hr>"
					+ "Please note: This email was sent from a notification-only address that can't accept incoming email. Please do not reply to this message.", true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		emailSender.send(mimemessage);
	}
}
