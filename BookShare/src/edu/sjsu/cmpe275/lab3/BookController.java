package edu.sjsu.cmpe275.lab3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.internal.ws.resources.HttpserverMessages;

@Controller
public class BookController {
	
	/**
	 * Get the book from DB and display it to books.jsp
	 * @return
	 */
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public ModelAndView getbooks(HttpServletRequest request){
		String action = (String) request.getParameter("action");
		long uid;
		try{
			uid = (Long) request.getSession().getAttribute("userid");
		}catch(NullPointerException e){
			System.out.println("User not logged in");
			uid = 0;
		}
		
		List<?> list;
		ModelAndView mv = new ModelAndView("books");
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		if(action.equalsIgnoreCase("available")){
			Query query = session.createQuery("from Books where owner.userid <>:usid");
			query.setParameter("usid", uid);
			mv.addObject("what", "available");
			list = query.list();
		}
		else{
			Query query = session.createQuery("from RequiredBooks where postUserId.userid <>:usid");
			query.setParameter("usid", uid);
			mv.addObject("what", "required");
			list = query.list();
		}
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
	
	/**
	 * this is called when a user presses to buy a paricular book
	 * and specfbook page is called
	 * 
	 * @param bookId
	 * @return
	 */
	@RequestMapping(value="/buy", method = RequestMethod.POST)
	public ModelAndView buyBook(@RequestParam("bookId") long bookId){
		
		ModelAndView mv;
		Crud c = new Crud();
		mv = new ModelAndView("specificBook");
		Books book = new Books();
		book = (Books)c.get(book, bookId);
		mv.addObject("book", book);
		
		return mv;
	}
	
	@RequestMapping(value="/fulfill", method = RequestMethod.POST)
	public ModelAndView fulfillProposal(@RequestParam("postId") long postId){
		
		ModelAndView mv;
		Crud c = new Crud();
		mv = new ModelAndView("fulfillProposal");
		RequiredBooks post = new RequiredBooks();
		post = (RequiredBooks) c.get(post, postId);
		mv.addObject("post", post);
		
		return mv;
	}
	
	
	/**
	 * Submit the user proposal
	 */
	
	@RequestMapping(value="/proposal", method=RequestMethod.POST)
	public ModelAndView submitProposal(@RequestParam("postId") long postId,
										HttpServletRequest request){
		
		System.out.println("I am here");
		Crud c = new Crud();
		String desc = (String) request.getParameter("desc");
		ModelAndView mv = new ModelAndView("success");
		Login detail = new Login();
		RequiredBooks post = new RequiredBooks();
		detail = (Login) request.getSession().getAttribute("loginDetails");
		String success = "";
		/*try{*/
			Userdetail userdetail = (Userdetail) request.getSession().getAttribute("userDetails");
			String username = userdetail.getFirstname() +  " " + userdetail.getLastname();
			String phno = userdetail.getPhoneno();
			String bookdetails = post.getIsbn() + " " + post.getAuthor() + " " +post.getTitle();
			Proposals proposal = new Proposals(desc, 'N', username, phno, bookdetails);
			post = (RequiredBooks) c.get(post, postId);
			proposal.setProposerId(detail);
			proposal.setProposalForPostId(post);
			
			c.save(proposal);
			success = "Thank you, Your proposal has been submitted successfully";
		/*}catch (Exception e){
			success = "Sorry! Could not process your proposal at this moment";
		}*/
		mv.addObject("success", success);
		return mv;
		
	}
	
	/**
	 * 
	 * My account redirection
	 */
	@RequestMapping (value="/myaccount" , method=RequestMethod.GET)
	public ModelAndView myAccount(HttpServletRequest request){
		
		String action = (String) request.getParameter("action");
		long uid = (Long) request.getSession().getAttribute("userid");
		Query query;
		ModelAndView mv = new ModelAndView("myAccount");
		List<?> list;
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		if(action == null || action.equalsIgnoreCase("mybooks")){
			query = session.createQuery("from Books where owner.userid =:usid");
			mv.addObject("what", "mybooks");
			query.setParameter("usid", uid);
			list = query.list();
			mv.addObject("books", list);
		}else{
			if(action.equalsIgnoreCase("myrequestedbooks")){
				query = session.createQuery("from RequiredBooks where postUserId.userid =:usid");
				mv.addObject("what", "reqbooks");
				System.out.println("uid is " + uid);
				query.setParameter("usid", uid);
				list = query.list();
				mv.addObject("books", list);
				mv.addObject("what", "myrequestedbooks");
			}
			else if(action.equalsIgnoreCase("myproposals")){
				/*query = session.createQuery("from Proposals p inner join edu.sjsu.cmpe275.lab3.RequiredBooks r "
													+ " inner join edu.sjsu.cmpe275.lab3.Userdetail u"
													+ " where p.proposerId <> : uid");*/
				query = session.createQuery("from Proposals where proposerId.userid <> :usid"
						+ "	and accepted = 'N'");
				
				System.out.println("userid   " + uid);
				query.setParameter("usid", uid);
				list = query.list();
				mv.addObject("details", list);
				mv.addObject("what", "myproposals");
				
			}
			else{
				DBconnect db = new DBconnect();
				List<Bids> bids = db.getBids(uid);
				mv.addObject("bids", bids);
				mv.addObject("what", "mybids");
			}
		}
		
		return mv;
	}
	
	
		
}
