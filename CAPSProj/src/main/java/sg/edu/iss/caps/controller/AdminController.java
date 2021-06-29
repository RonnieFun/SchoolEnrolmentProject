package sg.edu.iss.caps.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.caps.model.CourseStatus;
import sg.edu.iss.caps.model.Courses;
import sg.edu.iss.caps.model.EnrolmentStatus;
import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.StudentCourseDetails;
import sg.edu.iss.caps.model.Users;
import sg.edu.iss.caps.service.AdminInterface;
import sg.edu.iss.caps.service.LecturerInterface;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminInterface adservice;
	@Autowired
	LecturerInterface leservice;

//	@Autowired
//	public void setAdminInterface(AdminInterface ads) {
//		this.adservice = ads;
//	}

	// display course list
	@RequestMapping("/courselist")
	public String courseList(Model model) {
		model.addAttribute("courses", adservice.getCourses());
		return "admin/courselist";
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
		return "admin/addenrolment";
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
		return "admin/enrolmentform";
	}

	@GetMapping("/approveenrolment/{id}")
	public String approveEnrolment(Model model, @PathVariable("id") Long id) {
		StudentCourseDetails enrolment = adservice.getEnrolment(id);
		// approve enrolment
		enrolment.setEnrolmentStatus(EnrolmentStatus.ACCEPTED);
		adservice.updateEnrolment(enrolment);
		return "redirect:/admin/enrolment";
	}

	@GetMapping("/deleteenrolment/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Long id) {
		StudentCourseDetails enrolment = adservice.getEnrolment(id);
		adservice.deleteEnrolment(enrolment);
		return "redirect:/admin/enrolment";
	}

	@RequestMapping("/enrolment/create")
	public String saveEnrolmentForm(@ModelAttribute("enrolment") @Valid StudentCourseDetails enrolment,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "enrolmentform";
		}

		LocalDate lt = LocalDate.now();
		enrolment.setDateOfEnrollment(lt);
		enrolment.setEnrolmentStatus(EnrolmentStatus.PENDING);
		adservice.saveEnrolment(enrolment);
		return "admin/enrolment";
	}

	@RequestMapping("/enrolment/update")
	public String updateEnrolmentForm(@ModelAttribute("enrolment") @Valid StudentCourseDetails enrolment,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "enrolmentform";
		}
		adservice.saveEnrolment(enrolment);
		model.addAttribute("enrolmentlist", adservice.getAllEnrolment());
		return "admin/enrolment";
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

		if (bindingResult.hasErrors()) {
			return "EditUser";
		}
		adservice.updateUser(user);
		return "admin/success";
	}

	@RequestMapping("/user/create")
	public String createUser(Model model) {
		Users user = new Users();
		String password = adservice.passwordGenerator();
		user.setPassword(password);

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
				adservice.saveuser(a);
			}
		}
		adservice.savecourse(course);
		if (course.getUsers() != null) {
			for (Users a : course.getUsers()) {
				leservice.addCourseTaught(a.getUserID(), course);
				adservice.saveuser(a);
			}
		}
		return "forward:/admin/courselist";
	}
}