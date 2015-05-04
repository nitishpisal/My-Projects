package edu.sjsu.cmpe275.lab3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
	
	/**
	 * Go to order fillup page i.e transaction details
	 * 
	 * @param bookid
	 * @param buyerid
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/buybook", method = RequestMethod.POST)
	public ModelAndView orderBook(@RequestParam("bookid") long bookid,
								@RequestParam("buyerid") long buyerid,
								@RequestParam("quantity") int quantity,
								@RequestParam("price") int price,
									HttpServletRequest request){
		System.out.println("I am here");
		ModelAndView mv = new ModelAndView("transaction");
		float amount = (float) quantity * price;
		mv.addObject("bookid", bookid);
		mv.addObject("amount", amount);
		mv.addObject("quantity", quantity);
		return mv;
	}
	
	/**
	 * Execute the transaction
	 * @param bookid
	 * @param buyerid
	 * @param request
	 * @return
	 */

	@RequestMapping(value="/transaction", method = RequestMethod.POST)
	public ModelAndView transaction(HttpServletRequest request){
		
		String bookid = (String) request.getParameter("bookid");
		String amount1 = (String) request.getParameter("amount");
		long userid = 0;
		try{
			userid = (Long) request.getSession().getAttribute("userid");
		}catch(ClassCastException e){
			System.err.println("unable to cast user id in ORDERCONTROLLER");
		}
		int quantity = Integer.parseInt((String) request.getParameter("quantity"));
		
		long bookId = Long.parseLong(bookid);
		float amount = Float.parseFloat(amount1);
		Books book = new Books();
		Userdetail user = new Userdetail();
		user = (Userdetail) request.getSession().getAttribute("userDetails");
		Orders order = new Orders(quantity, amount);
		Crud c = new Crud();
		book =(Books) c.get(book, bookId);
		order.setBookId(book);
		order.setUserId(user);
		c.save(order);
		ModelAndView mv = new ModelAndView("success");
		mv.addObject("bookid", bookid);
		mv.addObject("success", "Thankyou! Your order is Placed");
		mv.addObject("order", order);
		return mv;
	
	}
	

}
