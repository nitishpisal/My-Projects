package edu.sjsu.cmpe275.lab3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	

	/**
	 * Register to Bookshare phase 1
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("email", request.getParameter("email"));
		mv.addObject("password1", request.getParameter("password1"));
		mv.addObject("confirmPass2", request.getParameter("confirmPass2"));
		
		return mv;
	
	}
	/**
	 * Register complete user details
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="/registerUserDetails", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest request){
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String doi = request.getParameter("doi");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String street = request.getParameter("street");
		String city= request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String phno = request.getParameter("contact");
		
		Login newLogin = new Login(email, password);
		Address address = new Address(street, city, state, zip);
		Userdetail userdetail = new Userdetail(firstname, lastname, email, "I am " + doi, doi, phno);
		userdetail.setAddress(address);
		userdetail.setLogin(newLogin);
		Crud c= new Crud();
		c.save(userdetail);
		
		String ret = "redirect:/";
		return ret;
	
	}
	
	
	
	
	
	
}
