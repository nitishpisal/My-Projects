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

import sun.security.krb5.internal.crypto.crc32;

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
			Query query = session.createQuery("from Books where owner.userid <>:usid and available = 'Y'");
			query.setParameter("usid", uid);
			mv.addObject("what", "available");
			list = query.list();
		}
		else{
			Query query = session.createQuery("from RequiredBooks where postUserId.userid <>:usid and fulfilled = 'N'");
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
		
		ModelAndView mv = new ModelAndView("bookRegister");
		mv.addObject("what", "sellRequest");
		return mv;
	}
	
	/**
	 * Request book controller redirecting to post book requirements
	 * 
	 */
	@RequestMapping(value="/requestbook", method = RequestMethod.GET)
	public ModelAndView requestBooks(HttpServletRequest request){
		ModelAndView mv;
		String login = (String) request.getSession().getAttribute("login");
		if(login != null && login.equalsIgnoreCase("true")){
			mv = new ModelAndView("bookRegister");
			mv.addObject("what", "buyRequest");
		}else{
			mv = new ModelAndView("login");
		}
		return mv;
	}
	
	@RequestMapping(value="/sellbook", method = RequestMethod.GET)
	public ModelAndView sellBooks(HttpServletRequest request){
		ModelAndView mv;
		String login = (String) request.getSession().getAttribute("login");
		if(login != null && login.equalsIgnoreCase("true")){
			mv = new ModelAndView("bookRegister");
			mv.addObject("what", "sellRequest");
		}else{
			mv = new ModelAndView("login");
		}
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
		
		ModelAndView mv = new ModelAndView("success");
		mv.addObject("success", "Thank you! your requirements are successfully posted");
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
			/*String username = userdetail.getFirstname() +  " " + userdetail.getLastname();
			String phno = userdetail.getPhoneno();
			String bookdetails = post.getIsbn() + " " + post.getAuthor() + " " +post.getTitle();*/
			Proposals proposal = new Proposals(desc, 'N');
			post = (RequiredBooks) c.get(post, postId);
			proposal.setProposerId(userdetail);
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
			System.out.println("userid is " + uid);
			query = session.createQuery("from Books where owner.userid =:usid and available = 'Y'");
			mv.addObject("what", "mybooks");
			query.setParameter("usid", uid);
			list = query.list();
			mv.addObject("books", list);
		}else{
			if(action.equalsIgnoreCase("myrequestedbooks")){
				query = session.createQuery("from RequiredBooks where postUserId.userid =:usid and fulfilled = 'N'");
				mv.addObject("what", "reqbooks");
				System.out.println("uid is " + uid);
				query.setParameter("usid", uid);
				list = query.list();
				mv.addObject("books", list);
				mv.addObject("what", "myrequestedbooks");
			}
			else if(action.equalsIgnoreCase("myproposals")){
				 query = session.createQuery("from Proposals P inner join P.proposerId UD"
						 					+ " inner join P.proposalForPostId RB where P.proposerId.userid <> :usid "
						 					+ " and RB.postUserId.userid = :usid"
						 					+ " and P.accepted ='N' and P.active = 'yes'");
				
				query.setParameter("usid", uid);
				list = query.list();
				mv.addObject("props", list);
				mv.addObject("what", "myproposals");
				
			}
			else{
				query = session.createQuery("from Bids Bi inner join Bi.bookId B where Bi.bidder.userid <> :usid"
						+ " and B.owner.userid = :usid and Bi.active = 'yes'");
				query.setParameter("usid", uid);
				list = query.list();
				List<Object> bidList = new ArrayList<Object>();
				List<Object> bookList = new ArrayList<Object>();
				
				for(int i=0; i<list.size(); i++){
				//	System.out.println(((Object[]) list.get(i))[0]);
				//	System.out.println(((Object[]) list.get(i))[1]);
					bidList.add(((Object[]) list.get(i))[0]);
					bookList.add(((Object[]) list.get(i))[1]);
				}
				
				
				mv.addObject("bids", list);
				mv.addObject("what", "mybids");
			}
		}
		
		return mv;
	}
	/**
	 * 
	 * Place bid for a Book
	 * @param bookid
	 * @param bidPrice
	 * @param buyerId
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="/placebid" , method=RequestMethod.POST)
	public ModelAndView placeBid(@RequestParam("bookid") long bookid,
									@RequestParam("bidPrice") int bidPrice,
									@RequestParam("buyerid") long buyerId,
									HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView("success");
		String success ="";
		System.out.println("Bid price is  " + bidPrice);
		Crud c = new Crud();
		Userdetail user = new Userdetail();
		user = (Userdetail) request.getSession().getAttribute("userDetails");
		Books book = new Books();
		book = (Books) c.get(book, bookid);
		Bids bid = new Bids(bidPrice, 'N');
		bid.setBidder(user);
		bid.setBookId(book);
		try{
			c.save(bid);
		}catch(Exception e){
			System.out.println("Exception in placing bid : " + e);
			success = "Sorry, unable to place bid at this time";
		}
		success = "Thank you, Your proposal has been submitted successfully";
		mv.addObject("success", success);
		return mv;
	}
	
	/**
	 * 
	 * Accept bid for your book
	 */
	
	@RequestMapping(value="/acceptbid", method=RequestMethod.POST)
	public ModelAndView acceptBid(@RequestParam("bidId") long bidId,
									@RequestParam("bookId") long bookId,
									HttpServletRequest request){
		
		long uid = (Long) request.getSession().getAttribute("userid");
		Query query;
		ModelAndView mv = new ModelAndView("myAccount");
		List<?> list;
		
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		Bids bid = new Bids();
		//get the bid from DB
		bid = (Bids) c.get(bid, bidId);
		bid.setAccepted('Y');
		DBconnect db = new DBconnect();
		//update the accepted to Y in DB
		try{
			c.update(bid);
			db.updateBids(bookId);
		}catch(Exception e){
			System.out.println("unable to update the bid in Bookcontroller");
		}
		
		query = session.createQuery("from Bids Bi inner join Bi.bookId B where Bi.bidder.userid <> :usid"
				+ " and B.owner.userid = :usid and Bi.active = 'yes'");
		query.setParameter("usid", uid);
		list = query.list();
		
		mv.addObject("bids", list);
		mv.addObject("what", "mybids");
		
		return mv;
		
	}
	
	
	/**
	 * 
	 * Search for a book based on title, author or ISBN
	 * 
	 */
	
	@RequestMapping (value="/search" , method=RequestMethod.POST)
	public ModelAndView searchBooks(HttpServletRequest request){
		String searchStr = (String) request.getParameter("value");
		
		ModelAndView mv = new ModelAndView("search");
		
		Query query;
		List<?> list;
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		Userdetail user = new Userdetail();
		user = (Userdetail) request.getSession().getAttribute("userDetails");
		if (user != null){
			query = session.createQuery("from Books where isbn like :si or title like :st or author like :sa"
					+ " and owner.userid <> :uid and available = 'Y'");
			query.setParameter("uid", user.getUserid());
		}else{
			query = session.createQuery("from Books where available = 'Y' and isbn like :si or title like :st or author like :sa");
		}
		query.setString("si", '%'+searchStr+'%');
		query.setString("st", '%'+searchStr+'%');
		query.setString("sa", '%'+searchStr+'%');
		list = query.list();
		
		mv.addObject("books", list);
		mv.addObject("what", "available");
		 
		return mv;
		
	}
	
	/**
	 * Badal Changes 3rd May 2015
	 * after 6 PM
	 * 
	 */
	
	@RequestMapping(value="/removebook" , method=RequestMethod.POST)
	public ModelAndView removeBook(@RequestParam("bookid") long bookid,
									HttpServletRequest request){
		
		String removeWhat = request.getParameter("removewhat");
		
		long uid = (Long) request.getSession().getAttribute("userid");
		Query query;
		ModelAndView mv = new ModelAndView("myAccount");
		List<?> list;
		Crud c = new Crud();
		//Check if user wants to remove his available books
		//update the book inventory set available to false
		if(removeWhat.equalsIgnoreCase("postedbook")){
			Books book = new Books();
			book = (Books)c.get(book, bookid);
			book.setAvailable('N');
			c.update(book);
		}
		//Check if user wants to remove his requirements
		else if(removeWhat.equalsIgnoreCase("requiredbook")){
			RequiredBooks rb = new RequiredBooks();
			rb = (RequiredBooks) c.get(rb, bookid);
			rb.setFulfilled('Y');
			c.update(rb);
		}
		
		
		//Get the user inventory for available books
		Session session = (Session) c.crudOpen();
		System.out.println("userid is " + uid);
		query = session.createQuery("from Books where owner.userid =:usid and available = 'Y'");
		query.setParameter("usid", uid);
		list = query.list();
		mv.addObject("what", "mybooks");
		mv.addObject("books", list);
			
		return mv;
	}
	
	@RequestMapping(value="/acceptproposal", method=RequestMethod.POST)
	public ModelAndView acceptProposal(@RequestParam("proposalId") long proposalId,
										@RequestParam("proposalforpostId") long postId,
										HttpServletRequest request){
		
		long uid = (Long) request.getSession().getAttribute("userid");
		Query query;
		ModelAndView mv = new ModelAndView("myAccount");
		List<?> list;
		Crud c = new Crud();
		//Open the session
		//Update the proposal to accepted state
		Proposals proposal = new Proposals();
		proposal = (Proposals) c.get(proposal, proposalId);
		proposal.setAccepted('Y');
		c.update(proposal);
		
		//Update the required book's fulfill proposal status to Yes
		
		RequiredBooks rb = new RequiredBooks();
		rb = (RequiredBooks) c.get(rb, postId);
		rb.setFulfilled('Y');
		c.update(rb);
		
		//get the updated list of proposals from the database
		Session session = (Session) c.crudOpen();
		
	/*	//Set all the proposals for this requirement to inactive
		query = session.createQuery("update Proposals set active = 'no' where proposalForPostId.postId = :postId");
		query.setLong("postId", postId);
		query.executeUpdate();*/
		
		query = session.createQuery("from Proposals P inner join P.proposerId UD"
					+ " inner join P.proposalForPostId RB where P.proposerId.userid <> :usid "
					+ " and RB.postUserId.userid = :usid"
					+ " and P.accepted ='N' and P.active = 'yes'");
		
		//System.out.println("userid   " + uid);
		query.setParameter("usid", uid);
		list = query.list();
		mv.addObject("props", list);
		mv.addObject("what", "myproposals");
		
		return mv;
		
	}
	
	/**
	 * Feedback to Buyer and seller
	 * Aditya Feedback function
	 */
	
	@RequestMapping(value="/feedback", method = RequestMethod.GET)
	public ModelAndView provideFeedback(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("getreviews");
		String action = (String) request.getParameter("action");
		String role = (String) request.getParameter("role");
		if (null == action)
			return mv;
		Crud c = new Crud();
		List<?> list;
		long uid;
		try{
			uid = (Long) request.getSession().getAttribute("userid");
		}catch(NullPointerException e){
			System.out.println("User not logged in");
			uid = 0;
		}
		Session session = c.crudOpen();
		if(action.equalsIgnoreCase("rateBuyer")){
			Query query = session.createQuery("from Orders o inner join o.userId u inner join o.bookId b where b.owner.userid =:uid");
			query.setParameter("uid", uid);
			list = query.list();
		} else {
			Query query = session.createQuery("from Orders o inner join o.userId u inner join o.bookId b where b.owner.userid =:uid");
			query.setParameter("uid", uid);
			list = query.list();
			System.out.println(list.size());
		}
		mv.addObject("ratingsTo",list);
		mv.addObject("role", role);
		return mv;
	}
	
	@RequestMapping(value="/feedback/save", method=RequestMethod.POST)
	public ModelAndView saveFeedback(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("getreviews");
		if(null != request.getParameter("rating")){
			Crud c = new Crud();
			Feedback feedback = new Feedback();
			Userdetail user = new Userdetail();
			user = (Userdetail) request.getSession().getAttribute("userDetails");
			feedback.setComments(request.getParameter("comment"));
			feedback.setRating(Integer.parseInt(request.getParameter("rating")));
			feedback.setUserId(user);
			feedback.setRole(request.getParameter("userRole"));
			//System.out.println("usertype" + (String)request.getParameter("userRole"));
			feedback.setRatingForUser(Integer.parseInt(request.getParameter("ratingTo")));
			c.save(feedback);
			mv = new ModelAndView("success");
			mv.addObject("success", "Thank you! Your Valuable feedback is posted");
		}
		return mv;
	}
	
		
}
