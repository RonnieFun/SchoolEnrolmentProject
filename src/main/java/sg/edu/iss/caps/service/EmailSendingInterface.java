package sg.edu.iss.caps.service;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.Users;

public interface EmailSendingInterface {
	public void sendCourseEnrolmentConfirmation(Courses course, Users user);
	public void sendAccountCreatedEmail(Users user, String unhashedpassword);
}
