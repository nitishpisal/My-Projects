package edu.sjsu.cmpe275.lab3;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
	public ModelAndView getLoginPage(){
		
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	/**
	 *validate login 
	 * 
	 */
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> validateLogin(@RequestParam("username") String username,
															  @RequestParam("password") String password,
															  HttpServletRequest request){
		
		Crud c = new Crud();
		Session session = (Session) c.crudOpen();
		Query query = session.createQuery("from Login where username = :uname");
		query.setParameter("uname", username);
		List<?> list = query.list();
		Login details = (Login)list.get(0);
		if(details.getPassword().equals(password)){
			request.getSession().setAttribute("login", "true");
			request.getSession().setAttribute("username", username);
		}
		c.crudClose();
		return new ResponseEntity<String>(details.getUserName(), HttpStatus.BAD_REQUEST);
	}
	
	
}
