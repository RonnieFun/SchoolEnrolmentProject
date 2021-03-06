package sg.edu.iss.caps.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.AdminInterface;
import sg.edu.iss.caps.service.EmailSendingInterface;
import sg.edu.iss.caps.service.LecturerInterface;
import sg.edu.iss.caps.service.MyUserDetails;
import sg.edu.iss.caps.service.UserValidationService;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

	@Autowired
	AdminInterface adservice;

	@Autowired
	LecturerInterface leservice;

	@Autowired
	UserValidationService validservice;

	@Autowired
	EmailSendingInterface sendEmail;

	// display course list
	@RequestMapping("/courselist")
	public String courseList(Model model) {
		model.addAttribute("courses", adservice.getCourses());
		return "admin/courselist";
	}

	//display enrolment list
	@RequestMapping(value = "/enrolment")
	public String showEnrolments(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
		return listEnrolmentByPage(model, 1, "id", "asc", userDetails);
	}

	//pagination for enrolment
	@GetMapping("/enrolpage/{pageNumber}")
	public String listEnrolmentByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir,
			@AuthenticationPrincipal MyUserDetails userDetails) {

		List<StudentCourseDetails> clist;
		Page<StudentCourseDetails> page;

		page = adservice.listAllEnrolment(currentPage, sortField, sortDir);

		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		clist = page.getContent();
		model.addAttribute("enrolmentlist", clist);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);

		return "admin/enrolment";
	}

	// show enrolment form
	@GetMapping("/addenrolment")
	public String showEnrolmentForm(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
		if (userDetails == null) {
			return "redirect:/login";
		}

		StudentCourseDetails enrolment = new StudentCourseDetails();
		model.addAttribute("enrolment", enrolment);
		// getting studentIDList
		Map<Long, String> studentIDList = new HashMap<Long, String>();
		Long studentId = null;
		List<Users> studentList = adservice.getStudentList();
		for (int i = 0; i < studentList.size(); i++) {
			studentId = studentList.get(0).getUserID();
			studentIDList.put(studentList.get(i).getUserID(),
					studentList.get(i).getFirstName() + ' ' + studentList.get(i).getLastName());
		}

		// get CourseIDList relate with first student
		Map<Long, String> courseIDList = new HashMap<Long, String>();
		List<Courses> courseList = adservice.getCoursesByStuId(studentId);
		for (int i = 0; i < courseList.size(); i++) {
			courseIDList.put(courseList.get(i).getCourseID(), courseList.get(i).getCourseName());
		}

		model.addAttribute("studentList", studentIDList);
		model.addAttribute("courseList", courseIDList);
		return "admin/addenrolment";
	}

	//edit enrolment 
	@GetMapping("/editenrolment/{id}")
	public String showEditForm(Model model, @PathVariable("id") Long id,
			@AuthenticationPrincipal MyUserDetails userDetails) {
		if (userDetails == null) {
			return "redirect:/login";
		}

		model.addAttribute("enrolment", adservice.getEnrolment(id));
		return "admin/enrolmentform";
	}

	//approve enrolment
	@GetMapping("/approveenrolment/{id}")
	public String approveEnrolment(Model model, @PathVariable("id") Long id,
			@AuthenticationPrincipal MyUserDetails userDetails) {
		if (userDetails == null) {
			return "redirect:/login";
		}

		StudentCourseDetails enrolment = adservice.getEnrolment(id);
		// approve enrolment
		enrolment.setEnrolmentStatus(EnrolmentStatus.ACCEPTED);
		adservice.updateEnrolment(enrolment);
		return "redirect:/admin/enrolment";
	}

	//reject enrolment
	@GetMapping("/rejectenrolment/{id}")
	public String rejectEnrolment(Model model, @PathVariable("id") Long id,
			@AuthenticationPrincipal MyUserDetails userDetails) {
		if (userDetails == null) {
			return "redirect:/login";
		}

		StudentCourseDetails enrolment = adservice.getEnrolment(id);
		// approve enrolment
		enrolment.setEnrolmentStatus(EnrolmentStatus.REJECTED);
		adservice.updateEnrolment(enrolment);
		return "redirect:/admin/enrolment";
	}

	//delete enrolment
	@GetMapping("/deleteenrolment/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Long id,
			@AuthenticationPrincipal MyUserDetails userDetails) {
		if (userDetails == null) {
			return "redirect:/login";
		}

		StudentCourseDetails enrolment = adservice.getEnrolment(id);
		adservice.deleteEnrolment(enrolment);
		return "redirect:/admin/enrolment";
	}

	//create enrolment
	@RequestMapping("/enrolment/create")
	public String saveEnrolmentForm(@ModelAttribute("enrolment") @Valid StudentCourseDetails enrolment,
			BindingResult bindingResult, Model model, @AuthenticationPrincipal MyUserDetails userDetails) {

		if (bindingResult.hasErrors()) {
			return "enrolmentform";
		}
		if (userDetails == null) {
			return "redirect:/login";
		}

		LocalDate lt = LocalDate.now();
		enrolment.setDateOfEnrollment(lt);
		enrolment.setEnrolmentStatus(EnrolmentStatus.PENDING);
		adservice.saveEnrolment(enrolment);
		model.addAttribute("enrolmentlist", adservice.getAllEnrolment());
		return "redirect:/admin/enrolment";
	}

	//update enrolment
	@RequestMapping("/enrolment/update")
	public String updateEnrolmentForm(@ModelAttribute("enrolment") @Valid StudentCourseDetails enrolment,
			BindingResult bindingResult, Model model, @AuthenticationPrincipal MyUserDetails userDetails) {

		if (bindingResult.hasErrors()) {
			return "enrolmentform";
		}
		if (userDetails == null) {
			return "redirect:/login";
		}

		adservice.saveEnrolment(enrolment);
		model.addAttribute("enrolmentlist", adservice.getAllEnrolment());
		return "redirect:/admin/enrolment";
	}

	// ajax call
	@RequestMapping(value = "/getCourses", method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestParam(value = "studentId") Long studentId) throws Exception {

		List<Courses> course = adservice.getCoursesByStuId(studentId);
		Map<Long, String> courseList = new HashMap<Long, String>();
		for (int i = 0; i < course.size(); i++) {
			courseList.put(course.get(i).getCourseID(), course.get(i).getCourseName());
		}
		String jsSon = null;
		ObjectMapper mapper = new ObjectMapper();
		if (!courseList.isEmpty()) {
			try {
				jsSon = mapper.writeValueAsString(courseList);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			}
		}
		return jsSon;
	}

	// BRANDON AND ALE
	//display user list
	@RequestMapping(value = "list")
	public String listUser(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
		return listByPage(model, 1, "lastName", "asc", "all", userDetails);
	}

	//pagination for user
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir,
			@RequestParam(value = "role", required = false, defaultValue = "all") String role,
			@AuthenticationPrincipal MyUserDetails userDetails) {

		String firstName = "firstNamePlaceHolder";
		String lastName = "lastNamePlaceHolder";
		String roletag = "roletag";

		if (userDetails != null) {
			firstName = userDetails.getFirstName();
			lastName = userDetails.getLastName();
			roletag = userDetails.getRole().toString();
			model.addAttribute("firstName", firstName);
			model.addAttribute("lastName", lastName);
			model.addAttribute("roleTag", roletag);
		}

		List<Users> ulist;
		Page<Users> page;

		if (!role.equals("all")) {
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

	//delete user
	@RequestMapping("/deleteuser")
	public String DeleteUser(@RequestParam(name = "id", required = true) long id) {
		adservice.deleteUser(id);
		return "redirect:/admin/page/1?sortField=userID&sortDir=asc";
	}

	//edit user
	@RequestMapping("/edit/{id}")
	public String ShowEditUserForm(Model model, @PathVariable("id") Long id) {
		List<String> salutationList = Arrays.asList("Mr", "Ms", "Mrs");
		model.addAttribute("salutationList", salutationList);
		model.addAttribute("user", adservice.findById(id));
		return "admin/EditUser";
	}

	//save user
	@RequestMapping("/user/save")
	public String saveUserForm(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult, Model model) {

		String err = validservice.validateUserEmail(user);

		List<String> salutationList = Arrays.asList("Mr", "Ms", "Mrs");
		model.addAttribute("salutationList", salutationList);

		if (!err.isEmpty()) {
			ObjectError error = new ObjectError("globalError", err);
			bindingResult.addError(error);
		}

		String password = null;
		String unhashedpassword = null;

		if (user.getPassword() == "") {
			password = adservice.passwordGenerator();
			model.addAttribute("password", password);
			unhashedpassword = password;
			// System.out.println(password);
			BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
			user.setPassword(pass.encode(password));
		} else {
			model.addAttribute("password", null);
		}

		if (bindingResult.hasErrors()) {
			if (unhashedpassword != null)
				user.setPassword(null);
			return "admin/EditUser";
		}

		adservice.updateUser(user);

		sendEmail.sendAccountCreatedEmail(user, unhashedpassword);

		return "admin/success";
	}

	//create user
	@RequestMapping("/user/create")
	public String createUser(Model model) {
		Users user = new Users();

		List<String> salutationList = Arrays.asList("Mr", "Ms", "Mrs");
		model.addAttribute("salutationList", salutationList);
		model.addAttribute("user", user);
		return "admin/EditUser";
	}

	// delete course with validations on whether course still has students/lecturers
	@RequestMapping("/deletecourse/{courseID}")
	public String deleteCourse(@PathVariable("courseID") long courseID, Model model) {
		if (adservice.getCoursesWithStudents().contains(courseID)) {
			model.addAttribute("ErrorMessage", "Course " + courseID
					+ " still has students associated with it. Please remove all students still associated with the course before deletion.");
			return "forward:/admin/courselist";
		} else if (adservice.getCourseById(courseID).getUsers().size() != 0) {
			model.addAttribute("ErrorMessage", "Course " + courseID
					+ " still has lecturers associated with it. Please remove all lecturers still associated with the course before deletion.");
			return "forward:/admin/courselist";
		} else {
			model.addAttribute("SuccessMessage", "Course " + courseID + " was deleted successfully.");
			adservice.deleteCourse(adservice.getCourseById(courseID));
			return "forward:/admin/courselist";
		}
	}

	// edit course
	@RequestMapping("/editcourse/{courseID}")
	public String editCourse(@PathVariable("courseID") long courseID, Model model) {
		List<Users> lecturers = leservice.getAllUsersByRole(Roles.LECTURER);
		model.addAttribute("course", adservice.getCourseById(courseID));
		model.addAttribute("lecturers", lecturers);
		model.addAttribute("statusupcoming", CourseStatus.UPCOMING);
		model.addAttribute("statusongoing", CourseStatus.ONGOING);
		model.addAttribute("statuscompleted", CourseStatus.COMPLETED);
		return "admin/courseform";
	}

	// add course
	@RequestMapping("/addcourse")
	public String addCourse(Model model) {
		Courses course = new Courses();
		List<Users> lecturers = leservice.getAllUsersByRole(Roles.LECTURER);
		model.addAttribute("course", course);
		model.addAttribute("lecturers", lecturers);
		model.addAttribute("statusupcoming", CourseStatus.UPCOMING);
		model.addAttribute("statusongoing", CourseStatus.ONGOING);
		model.addAttribute("statuscompleted", CourseStatus.COMPLETED);
		return "admin/courseform";
	}

	// save course
	@RequestMapping("/savecourse")
	public String saveCourse(@Valid @ModelAttribute("course") Courses course, BindingResult bindingResult,
			Model model) {
		Courses course1 = new Courses();
		List<Users> lecturers = leservice.getAllUsersByRole(Roles.LECTURER);
		model.addAttribute("course", course);
		model.addAttribute("lecturers", lecturers);
		model.addAttribute("statusupcoming", CourseStatus.UPCOMING);
		model.addAttribute("statusongoing", CourseStatus.ONGOING);
		model.addAttribute("statuscompleted", CourseStatus.COMPLETED);

		// check if submission has any class annotation validation errors
		if (bindingResult.hasErrors()) {
			return "admin/courseform";
		}

		// validate if submission's start date is before end date
		if (course.getCourseStartDate() != null && course.getCourseEndDate() != null) {
			if (course.getCourseStartDate().compareTo(course.getCourseEndDate()) > 0) {
				model.addAttribute("enddateerror", "End date should not be before start date.");
				return "admin/courseform";
			}
		}

		// validate if submission's start date is before exam date
		if (course.getCourseStartDate() != null && course.getExamDate() != null) {
			if (course.getCourseStartDate().compareTo(course.getExamDate()) > 0) {
				model.addAttribute("examdateerror", "Exam date should not be before start date.");
				return "admin/courseform";
			}
		}

		// validate if submission's start date and end date are populated for automatic
		// course status
		if (course.getCourseStatus() == null) {
			if (course.getCourseStartDate() == null || course.getCourseEndDate() == null) {
				model.addAttribute("datemissingerror",
						"Course Start Date and Course End Date need to be filled in for automatic detection of course status.");
				return "admin/courseform";
			} else {
				if (course.getCourseStartDate().compareTo(LocalDate.now()) > 0) {
					course.setCourseStatus(CourseStatus.UPCOMING);
				} else if (course.getCourseStartDate().compareTo(LocalDate.now()) <= 0
						&& course.getCourseEndDate().compareTo(LocalDate.now()) >= 0) {
					course.setCourseStatus(CourseStatus.ONGOING);
				} else if (course.getCourseEndDate().compareTo(LocalDate.now()) < 0) {
					course.setCourseStatus(CourseStatus.COMPLETED);
				}
			}
		}

		// save course + save course to selected lecturers
		if (adservice.findCourseById(course.getCourseID()).isPresent()) {
			course1 = adservice.getCourseById(course.getCourseID());
		}
		if (course1.getUsers() != null) {
			for (Users a : course1.getUsers()) {
				leservice.removeCourseTaught(a.getUserID(), course1);
				adservice.save(a);
			}
		}
		adservice.savecourse(course);
		if (course.getUsers() != null) {
			for (Users a : course.getUsers()) {
				leservice.addCourseTaught(a.getUserID(), course);
				adservice.save(a);
			}
		}
		return "forward:/admin/courselist";

	}

}
