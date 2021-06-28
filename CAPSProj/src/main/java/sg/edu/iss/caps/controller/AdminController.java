package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.AdminInterface;
import java.util.List;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Arrays;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminInterface adservice;

	@Autowired
	public void setAdminInterface(AdminInterface ads) {
		this.adservice = ads;
	}

	// retrieve enrolment list
	@GetMapping("/enrolment")
	public String showEnrolments(Model model) {
//		List<StudentCourseDetails> enrollist = adservice.getAllEnrolment();
//		for(int i=0;i< enrollist.size();i++) {
//			System.out.println(enrollist.get(i).getC().getCourseName());
//		}
		model.addAttribute("enrolmentlist", adservice.getAllEnrolment());
		return "admin/enrolment";
	}

	// show enrolment form
	@GetMapping("/addenrolment")
	public String showEnrolmentForm(Model model) {
		StudentCourseDetails enrolment = new StudentCourseDetails();
		model.addAttribute("enrolment", enrolment);
		// getting courseIDList
		model.addAttribute("courseList", retrieveStudentList());

		// getting studentIDList
		model.addAttribute("studentList", retrieveCourseList());
		return "admin/enrolmentform";
	}

	private List<Long> retrieveCourseList() {
		// TODO Auto-generated method stub
		List<Long> studentIDList = new ArrayList<>();
		List<Users> studentList = adservice.getStudentList();
		for (int i = 0; i < studentList.size(); i++) {
			studentIDList.add(studentList.get(i).getUserID());
		}
		return studentIDList;
	}

	private List<Long> retrieveStudentList() {
		// TODO Auto-generated method stub
		List<Long> courseIDList = new ArrayList<>();
		List<Courses> courseList = adservice.getCourseList();
		for (int i = 0; i < courseList.size(); i++) {
			courseIDList.add(courseList.get(i).getCourseID());
		}
		return courseIDList;
	}

	@GetMapping("/editenrolment/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id) {
		model.addAttribute("enrolment", adservice.getEnrolment(id));
		// getting courseIDList
		model.addAttribute("courseList", retrieveStudentList());

		// getting studentIDList
		model.addAttribute("studentList", retrieveCourseList());
		return "admin/enrolmentform";
	}

	@GetMapping("/approveenrolment/{id}")
	public String approveEnrolment(Model model, @PathVariable("id") Long id) {
		StudentCourseDetails enrolment = adservice.getEnrolment(id);
		// approve enrolment
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

	// save enrolment
	@RequestMapping("/saveenrolment")
	public String saveEnrolment(@ModelAttribute("enrolment") StudentCourseDetails enrolment) {
		adservice.saveEnrolment(enrolment);
		return "forward:/admin/enrolment";
	}

	@RequestMapping(value = "list")
	public String listUser(Model model) {
		return listByPage(model, 1, "lastName", "asc", "all");
	}

	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir,
			@RequestParam(value = "role", required = false, defaultValue = "all") String role) {

		List<Users> ulist;
		Page<Users> page;

		if (!role.equals("all")) {
			// ulist = adservice.listByRole(Roles.valueOf(role));
			page = adservice.listRoleUsers(currentPage, sortField, sortDir, Roles.valueOf(role));
			model.addAttribute("RoleType", role);
		} else {
			page = adservice.listAllUsers(currentPage, sortField, sortDir);
			model.addAttribute("RoleType", "all");
		}

		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		ulist = page.getContent();
		model.addAttribute("ulist", ulist);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);

		return "admin/Admin";
	}

	@RequestMapping("/listfilter")
	public String listRoleAll(@RequestParam(value = "role", required = false) String role, Model model) {
		if (role != null) {
			List<Users> ulist = adservice.listByRole(Roles.valueOf(role));
			model.addAttribute("ulist", ulist);
		} else {
			List<Users> ulist = adservice.listUsers();
			model.addAttribute("ulist", ulist);
			model.addAttribute("RoleType", "All Users");
		}

		return "admin/Admin";
	}

	@RequestMapping("/deleteuser")
	public String DeleteUser(@RequestParam(name = "id", required = true) long id) {
		adservice.deleteUser(id);
		return "redirect:/admin/admin/page/1?sortField=userID&sortDir=asc";
	}
	// Path variable method (need to change html)
//		public String deleteUser(@PathVariable(value = "id") long id) {
//			//Users user = adservice.findById(id);
//			adservice.deleteUser(id);
//			return "redirect:/admin/list";
//		}

	@RequestMapping("/edit/{id}")
	public String ShowEditUserForm(Model model, @PathVariable("id") Long id) {
		List<String> salutationList = Arrays.asList("Mr", "Ms", "Mrs");
		model.addAttribute("salutationList", salutationList);
		model.addAttribute("user", adservice.findById(id));
		return "admin/EditUser";
	}

	@RequestMapping("/user/save")
	public String saveUserForm(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult, Model model) {

		String password = null;

		if (bindingResult.hasErrors()) {
			return "EditUser";
		}

		if (user.getPassword() == "") {
			password = adservice.passwordGenerator();
			BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
			user.setPassword(pass.encode(password));
		} else {
			password = user.getPassword();
		}

		model.addAttribute("password", password);
		adservice.updateUser(user);
		return "admin/success";
	}

	@RequestMapping("/user/create")
	public String createUser(Model model) {
		Users user = new Users();

		List<String> salutationList = Arrays.asList("Mr", "Ms", "Mrs");
		model.addAttribute("salutationList", salutationList);
		model.addAttribute("user", user);
		return "admin/EditUser";
	}

}
