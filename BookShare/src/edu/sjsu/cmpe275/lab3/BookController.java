package edu.sjsu.cmpe275.lab3;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
	
	/**
	 * Get the book from DB and display it to books.jsp
	 * @return
	 */
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public ModelAndView getbooks(HttpServletRequest request){
		long uid;
		try{
			uid = (Long) request.getSession().getAttribute("userid");
		}catch(NullPointerException e){
			System.out.println("User not logged in");
			uid = 0;
		}
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		Query query = session.createQuery("from Books where owner.userid <>:usid");
		query.setParameter("usid", uid);
		List<?> list = query.list();
		ModelAndView mv = new ModelAndView("books");
		mv.addObject("books", list);
		return mv;
	}
	
	/**
	 * Post ad for selling the book
	 * 
	 */
	

	@RequestMapping(value="/bookpost", method = RequestMethod.GET)
	public ModelAndView postBooks(){
		
		/*Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		Query query = session.createQuery("from Books");
		List<?> list = query.list();*/
		ModelAndView mv = new ModelAndView("bookRegister");
		//mv.addObject("books", list);
		mv.addObject("what", "sellRequest");
		return mv;
	}
	
	/**
	 * Request book controller redirecting to post book requirements
	 * 
	 */
	@RequestMapping(value="/requestbook", method = RequestMethod.GET)
	public ModelAndView requestBooks(){
		ModelAndView mv = new ModelAndView("bookRegister");
		mv.addObject("what", "buyRequest");
		return mv;
	}
	
	@RequestMapping(value="/sellbook", method = RequestMethod.GET)
	public ModelAndView sellBooks(){
		ModelAndView mv = new ModelAndView("bookRegister");
		mv.addObject("what", "sellRequest");
		return mv;
	}
	
	/**
	 * get the details of the books required by user and add it to DB
	 * @return
	 */
	
	@RequestMapping(value="/postrequirements", method = RequestMethod.POST)
	public ModelAndView requestedBooksDetails(HttpServletRequest request){
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String quantity = request.getParameter("quantity");
		
		int yr = Integer.parseInt(year);
		int qty = Integer.parseInt(quantity);
		
		//get the user details using the username
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		Query query = session.createQuery("from Login where username = :uname");
		query.setParameter("uname", "badal.jain77@gmail.com");
		List<?> list = query.list();
		Login details = (Login)list.get(0);
		c.crudClose();
		
		//set the book object
		RequiredBooks rb = new RequiredBooks(isbn, title, author, publisher, yr, qty);
		rb.setPostUserId(details);
		c.save(rb);
		
		ModelAndView mv = new ModelAndView("bookRegister");
		mv.addObject("what", "buyRequest");
		return mv;		
	}
	
	/**
	 * Post your book to sell
	 * @param request
	 * @return
	 */
	
	
	@RequestMapping(value="/postBookToSell", method = RequestMethod.POST)
	public ModelAndView sellBooksDetails(HttpServletRequest request){
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");
		String bid = request.getParameter("bid");
		int yr = Integer.parseInt(year);
		int qty = Integer.parseInt(quantity);
		int prc = Integer.parseInt(price);
		
		//get the user details using the username
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		Query query = session.createQuery("from Login where username = :uname");
		query.setParameter("uname", "badal.jain77@gmail.com");
		List<?> list = query.list();
		Login details = (Login)list.get(0);
		c.crudClose();
		
		//set the book object
		Books book = new Books(isbn, title, author, publisher, yr, prc, qty, bid);
		book.setOwner(details);
		c.save(book);
		
		ModelAndView mv = new ModelAndView("success");
		mv.addObject("success", "Thank you! Your book details are posted");
		return mv;		
	}
	
	
	
	
	
	
		
}
