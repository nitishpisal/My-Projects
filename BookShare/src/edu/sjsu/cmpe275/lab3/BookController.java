package edu.sjsu.cmpe275.lab3;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
	
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public ModelAndView getbooks(){
		
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		Query query = session.createQuery("from Books");
		List<?> list = query.list();
		ModelAndView mv = new ModelAndView("books");
		mv.addObject("books", list);
		return mv;
	}
		
}
