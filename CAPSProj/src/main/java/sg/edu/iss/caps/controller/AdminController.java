package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.service.AdminInterface;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminInterface adservice;
	
	@Autowired
	public void setAdminInterface(AdminInterface ads) {
		this.adservice = ads;
	}
	
	//retrieve enrolment list 
	@GetMapping("/enrolment")
	public String showEnrolments(Model model) {
//		List<StudentCourseDetails> enrollist = adservice.getAllEnrolment();
//		for(int i=0;i< enrollist.size();i++) {
//			System.out.println(enrollist.get(i).getC().getCourseName());
//		}
		model.addAttribute("enrolmentlist", adservice.getAllEnrolment());
		return "admin/enrolment";
	}
	
	//show enrolment form
	@GetMapping("/addenrolment")
	public String showEnrolmentForm(Model model) {
		StudentCourseDetails enrolment = new StudentCourseDetails();
		model.addAttribute("enrolment", enrolment);
		model.addAttribute("courseList", adservice.getCourseList());
		model.addAttribute("studentList", adservice.getStudentList());
		return "admin/enrolmentform";
	}
	
	
	@GetMapping("/editenrolment/{id}")
	  public String showEditForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("enrolment", adservice.getEnrolment(id));
		return "admin/enrolmentform";
	  }
	
	@GetMapping("/approveenrolment/{id}")
	  public String approveEnrolment(Model model, @PathVariable("id") Long id) {
		StudentCourseDetails enrolment = adservice.getEnrolment(id);
		//approve enrolment
		enrolment.setEnrolmentStatus(EnrolmentStatus.ACCEPTED);
		adservice.updateEnrolment(enrolment);
		return "forward:/admin/enrolment";
	  }
	  
	  @GetMapping("/deleteenrolment/{id}")
	  public String deleteMethod(Model model, @PathVariable("id") Long id) {
		  StudentCourseDetails enrolment = adservice.getEnrolment(id);
		  adservice.deleteEnrolment(enrolment);
		return "forward:/admin/enrolment";
	  }


}
