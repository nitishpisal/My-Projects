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
	 * Register a new user
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param description
	 * @param username
	 * @param password
	 * @param doi
	 * @param request
	 * @return
	 */
	
	/*@RequestMapping(value="/player", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Userdetail> registerPlayer(	@RequestParam("firstname") String firstname,
										@RequestParam("lastname") String lastname,
										@RequestParam("email") String email,
										@RequestParam("street") String street,
										@RequestParam("city") String city,
										@RequestParam("state") String state,
										@RequestParam("zip") String zip,
										@RequestParam("description") String description,
										@RequestParam("username") String username,
										@RequestParam("password") String password,
										@RequestParam("doi") String doi,
										@RequestParam("phno") String phno,
										HttpServletRequest request){
		
		System.out.println("I am here");
		
		Userdetail u = new Userdetail(firstname, lastname, email, description, doi,phno);
		Login login = new Login(username, password);
		Address address = new Address(street, city, state, zip);
		u.setAddress(address);
		u.setLogin(login);
		
		System.out.println(firstname +" " +  lastname +" " +  email+" " + description+" " + doi);
		System.out.println(street +" " + city +" " + state +" " + zip);
		System.out.println(username+" " + password);
		Crud c = new Crud();
		try{
		   long id = c.save(u);
			}catch(Exception e){
				System.out.println("Unable to insert record" + e);
				return new ResponseEntity<Userdetail>(HttpStatus.BAD_REQUEST);
			}
		request.getSession().setAttribute("uname", username);
		System.out.println("user " + (String)request.getSession().getAttribute("uname"));
		
		return new ResponseEntity<Userdetail> (u, HttpStatus.OK);
	}
	*/

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
		String phno = request.getParameter("phoneno");
		
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
