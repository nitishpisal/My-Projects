package edu.sjsu.cmpe275.lab3;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GlobalController {
	
	/**
	 * get Login page
	 * @return
	 */
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(	
										HttpServletRequest request){
		
		//String loginOrNot = (String)request.getAttribute("login");
		//System.out.println(loginVal);
		ModelAndView mv = new ModelAndView("login");
		
		return mv;
	}
	
	/**
	 * login before buying or bidding
	 */
	@RequestMapping(value="/login/{loginVal}", method = RequestMethod.POST)
	public ModelAndView getLoginPage2(	@PathVariable("loginVal") String loginVal,
										@RequestParam("bookId") String bookId,
										HttpServletRequest request){
		//System.out.println(loginVal + bookId);
		ModelAndView mv = new ModelAndView("login");
		int bookid = Integer.parseInt(bookId);
		mv.addObject("bookId",bookid);
		return mv;
	}
	
	/**
	 *validate login 
	 * 
	 */
	@RequestMapping(value="/validate", method = RequestMethod.GET)
	public String validateLogin(HttpServletRequest request){
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String bookId = request.getParameter("bookId");
		System.out.println("book "+ bookId);
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		Query query = session.createQuery("from Login where username = :uname");
		query.setParameter("uname", username);
		List<?> list = query.list();
		Login details = (Login)list.get(0);
		query = session.createQuery("from Userdetail where userid = :uid");
		query.setParameter("uid", details.getUserid());
		List<?> list2 = query.list();
		Userdetail userDetail = (Userdetail)list2.get(0);
		c.crudClose();
		if(details.getPassword().equals(password)){
			request.getSession().setAttribute("login", "true");
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("firstname", userDetail.getFirstname());
			request.getSession().setAttribute("userid", details.getUserid());
		}
		else{
			
		}
		
		String ret = "redirect:/books";
		//return new ResponseEntity<String>(details.getUserName(), HttpStatus.BAD_REQUEST);
		
		return ret;
	}
	
	/**
	 * Logout from the account
	 */

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		
		String ret = "redirect:/";
		return ret;
	
	}
	
	/**
	 * Register to Bookshare
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("register");
		mv.addObject("email", request.getParameter("email"));
		mv.addObject("password1", request.getParameter("password1"));
		mv.addObject("confirmPass2", request.getParameter("confirmPass2"));
		
		return mv;
	
	}
	
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
		
		Login newLogin = new Login(email, password);
		Address address = new Address(street, city, state, zip);
		Userdetail userdetail = new Userdetail(firstname, lastname, email, "I am " + doi, doi);
		userdetail.setAddress(address);
		userdetail.setLogin(newLogin);
		Crud c= new Crud();
		c.save(userdetail);
		
		String ret = "redirect:/";
		return ret;
	
	}
	
	
	
	
}
