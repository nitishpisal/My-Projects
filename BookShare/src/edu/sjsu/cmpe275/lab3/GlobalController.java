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
	 * 
	 * 
	 * Login post call for buying or fulfilling the request
	 */
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView getLoginPageWithAction(	
										HttpServletRequest request){
		
		String bookId = (String )request.getParameter("bookId");
		String postId = (String )request.getParameter("postId");
		String action = (String )request.getParameter("action");
		/*int bookid = 0; int postid = 0;
		if(bookId != null)
			bookid = Integer.parseInt(bookId);
		if(postId != null)
			postid = Integer.parseInt(postId);
		if(action.equals("buy")){
			
		}*/
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
	 * login before buying or bidding
	 */
	/*@RequestMapping(value="/login/{loginVal}", method = RequestMethod.POST)
	public ModelAndView getLoginPage2(	@PathVariable("loginVal") String loginVal,
										HttpServletRequest request){
		String bookId = (String) request.getParameter("bookId");
		//System.out.println(loginVal + bookId);
		ModelAndView mv;
		int bookid = Integer.parseInt(bookId);
		System.out.println(loginVal);
		if(loginVal.equalsIgnoreCase("nologin")){
			mv = new ModelAndView("login");
		}
		else{
			mv = new ModelAndView("specificBook");
			Books book = new Books();
			Crud c = new Crud();
			book = (Books)c.get(book, bookid);
			mv.addObject("book", book);
		}
		mv.addObject("bookId",bookid);
		return mv;
	}*/
	
	/**
	 *validate login 
	 * 
	 */
	@RequestMapping(value="/validate", method = RequestMethod.POST)
	public ModelAndView validateLogin(HttpServletRequest request){
		
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
		Session session = (Session) c.crudOpen();
		Query query = session.createQuery("from Login where username = :uname");
		query.setParameter("uname", username);
		List<?> list = query.list();
		Login details = (Login)list.get(0);
		query = session.createQuery("from Userdetail where userid = :uid");
		query.setParameter("uid", details.getUserid());
		List<?> list2 = query.list();
		Userdetail userDetail = (Userdetail)list2.get(0);
		ModelAndView mv;
		if(details.getPassword().equals(password)){
			request.getSession().setAttribute("login", "true");
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("firstname", userDetail.getFirstname());
			request.getSession().setAttribute("userid", details.getUserid());
			request.getSession().setAttribute("userDetails", userDetail);
			request.getSession().setAttribute("loginDetails", details);
			if(what != null && what.equalsIgnoreCase("buy")){
				mv = new ModelAndView("specificBook");
				Books book = new Books();
				book = (Books)c.get(book, bookOrPost);
				mv.addObject("book", book);
			}
			else if(what != null && what.equalsIgnoreCase("fulfill")){
				mv = new ModelAndView("fulfillProposal");
				RequiredBooks post = new RequiredBooks();
				post = (RequiredBooks) c.get(post, bookOrPost);
				mv.addObject("post", post);
			}
			else{
				mv = new ModelAndView("books");
				query = session.createQuery("from Books where owner.userid <>:usid");
				query.setParameter("usid", details.getUserid());
				mv.addObject("what", "available");
				list = query.list();
				mv.addObject("books", list);
			}
		}
		else{
			mv = new ModelAndView("login");
			mv.addObject("bookOrPostId", bookOrPost);
			mv.addObject("what", "buy");
		}
		c.crudClose();
		return mv;
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
	
	
}
