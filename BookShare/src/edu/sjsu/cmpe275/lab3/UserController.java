package edu.sjsu.cmpe275.lab3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="/player", method = RequestMethod.POST)
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
										HttpServletRequest request){
		
		System.out.println("I am here");
		
		Userdetail u = new Userdetail(firstname, lastname, email, description, doi);
		Login login = new Login(username, password);
		Address address = new Address(street, city, state, zip);
		u.setAddress(address);
		u.setLogin(login);
		
		/*System.out.println(firstname +" " +  lastname +" " +  email+" " + description+" " + doi);
		System.out.println(street +" " + city +" " + state +" " + zip);
		System.out.println(username+" " + password);*/
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
	
	
	
}
