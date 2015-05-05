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
	
	
	/**'
	 * Rishi khurana, Index page redirection
	 * @return
	 */
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView getHome(){
		
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	
	/**
	 * get Login page
	 * @return
	 * Agam
	 */
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("login");
		
		return mv;
	}
	
	/**
	 * Pranjal
	 * 
	 * Login post call for buying or fulfilling the request
	 */
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView getLoginPageWithAction(	
										HttpServletRequest request){
		
		String bookId = (String )request.getParameter("bookId");
		System.out.println("book Id " + bookId);
		String postId = (String )request.getParameter("postId");
		String action = (String )request.getParameter("action");

		ModelAndView mv = new ModelAndView("login");
		if(action.equalsIgnoreCase("buy")){
				//mv = new ModelAndView("specificBook");
				mv.addObject("bookOrPostId", bookId);
				mv.addObject("what", "buy");
		}
		else{
			mv.addObject("bookOrPostId", postId);
			mv.addObject("what", "fulfill");
		}
		return mv;
	}

	
	/**
	 *validate login 
	 * Badal Jain validate function
	 */
	@RequestMapping(value="/validate", method = RequestMethod.POST)
	public String validateLogin(HttpServletRequest request){
		
		String ret = "redirect:/";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String what = request.getParameter("what");
		String bookOrPostId = request.getParameter("bookOrPostId");
		long bookOrPost = 0;
		try{
			bookOrPost = Long.parseLong(bookOrPostId);
		}catch(NumberFormatException e){
			System.out.println("Normal login");
		}
	//	String bookId = request.getParameter("bookId");
	//	System.out.println("book "+ bookId);
		Crud c = new Crud();
		ModelAndView mv;
		Session session = (Session) c.crudOpen();
		Query query = session.createQuery("from Login where username = :uname");
		query.setParameter("uname", username);
		List<?> list = query.list();
		if(list.size() ==0){
			mv = new ModelAndView("login");
			mv.addObject("bookOrPostId", bookOrPost);
			mv.addObject("what", "buy");
			return ret;
		}
		Login details = (Login)list.get(0);
		query = session.createQuery("from Userdetail where userid = :uid");
		query.setParameter("uid", details.getUserid());
		List<?> list2 = query.list();
		Userdetail userDetail = (Userdetail)list2.get(0);
		if(details != null && details.getPassword().equals(password)){
			request.getSession().setAttribute("login", "true");
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("firstname", userDetail.getFirstname());
			request.getSession().setAttribute("userid", details.getUserid());
			request.getSession().setAttribute("userDetails", userDetail);
			request.getSession().setAttribute("loginDetails", details);
			if(what != null && what.equalsIgnoreCase("buy")){
				ret = "redirect:/books/?action=available";
			}
			else if(what != null && what.equalsIgnoreCase("fulfill")){
				mv = new ModelAndView("fulfillProposal");
				RequiredBooks post = new RequiredBooks();
				post = (RequiredBooks) c.get(post, bookOrPost);
				mv.addObject("post", post);
			}
			else{
				ret = "redirect:/books/?action=available";
			}
		}
		else{
			mv = new ModelAndView("login");
			mv.addObject("bookOrPostId", bookOrPost);
			mv.addObject("what", "buy");
		}
		//c.crudClose();
		return ret;
	}
	
	/**
	 * Logout from the account
	 * Badal Jain
	 */

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		
		String ret = "redirect:/";
		return ret;
	
	}
	
	
}
