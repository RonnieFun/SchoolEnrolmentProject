package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.AdminInterface;
import java.util.List;
import java.util.ArrayList;

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
		//getting courseIDList
		model.addAttribute("courseList",retrieveStudentList());

		//getting studentIDList
		model.addAttribute("studentList", retrieveCourseList());
		return "admin/enrolmentform";
	}
	
	
	private List<Long> retrieveCourseList() {
		// TODO Auto-generated method stub
		List<Long> studentIDList = new ArrayList<>();
		List<Users> studentList = adservice.getStudentList();
		for(int i=0;i<studentList.size();i++) {
			studentIDList.add(studentList.get(i).getUserID());
		}
		return studentIDList;
	}

	private List<Long> retrieveStudentList() {
		// TODO Auto-generated method stub
		List<Long> courseIDList = new ArrayList<>();
		List<Courses> courseList = adservice.getCourseList();
		for(int i=0;i<courseList.size();i++) {
			courseIDList.add(courseList.get(i).getCourseID());
		}
		return courseIDList;
	}

	@GetMapping("/editenrolment/{id}")
	  public String showEditForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("enrolment", adservice.getEnrolment(id));
		//getting courseIDList
		model.addAttribute("courseList",retrieveStudentList());

		//getting studentIDList
		model.addAttribute("studentList", retrieveCourseList());
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
	  
	  //save enrolment 
	  @RequestMapping("/saveenrolment")
		public String saveEnrolment(@ModelAttribute("enrolment") StudentCourseDetails enrolment) 
		{
		  	adservice.saveEnrolment(enrolment);
			return "forward:/admin/enrolment";
		}


}
