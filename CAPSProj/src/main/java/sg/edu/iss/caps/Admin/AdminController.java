package sg.edu.iss.caps.Admin;

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
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.iss.caps.model.Roles;
import sg.edu.iss.caps.model.Users;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminInterface adservice;
	
	@Autowired
	public void setAdminInterface(AdminInterface ads) {
		this.adservice = ads;
	}
	
	@RequestMapping(value = "list")
	public String listUser(Model model) {
		return listByPage(model,1, "lastName", "asc");
	}
	
	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir) {
		Page<Users> page = adservice.listAllUsers(currentPage, sortField, sortDir);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Users> ulist = page.getContent();
			
		model.addAttribute("ulist", ulist);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		
		return "Admin";
	}
	
	@RequestMapping("/listfilter")
	  public String listRoleAll(@RequestParam(value="role", required =false) String role,
	      Model model) {
	    if(role != null) {
	      List<Users> ulist = adservice.listByRole(Roles.valueOf(role));
	      model.addAttribute("ulist", ulist);
	    }else {
	      List<Users> ulist = adservice.listUsers();
	      model.addAttribute("ulist", ulist);
	      model.addAttribute("RoleType", "All Users");
	    }
	    
	    return "Admin";
	  }
	
	@RequestMapping("/deleteuser")
	public String DeleteUser(@RequestParam(name="id", required = true) long id)
	{		
		adservice.deleteUser(id);		
		return "redirect:/admin/page/1?sortField=userID&sortDir=asc";
	}
	//Path vaiable method (need to change html)
//	public String deleteUser(@PathVariable(value = "id") long id) {
//		//Users user = adservice.findById(id);
//		adservice.deleteUser(id);
//		return "redirect:/admin/list";
//	}
	
	
	
	@RequestMapping("/edit/{id}")
	public String ShowEditUserForm(Model model, @PathVariable("id") Long id)
	{
		List<String> salutationList = Arrays.asList("Mr", "Ms", "Mrs");
		model.addAttribute("salutationList", salutationList);
		model.addAttribute("user",adservice.findById(id));
		return "EditUser";
	}
	
	@RequestMapping("/user/save")
	public String saveUserForm(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "EditUser";
		}
		adservice.updateUser(user);
		return "success";
	}
	
	@RequestMapping("/user/create")
	public String createUser(Model model) {
		Users user = new Users();
		String password = adservice.passwordGenerator();
		user.setPassword(password);
		
		List<String> salutationList = Arrays.asList("Mr", "Ms", "Mrs");
		model.addAttribute("salutationList", salutationList);
		model.addAttribute("user", user);
		return "EditUser";
	}
	
	
	
	


}
