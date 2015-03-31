package crud.servlets;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomePageController {
	
	@ExceptionHandler(ResourceNotFoundException.class)
		    public ModelAndView handleResourceNotFoundException(HttpServletRequest request) {
			ModelAndView mv = new ModelAndView("notfound");
			String err = (String)request.getAttribute("err");
			mv.addObject("msg", err);
			return mv;
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView gethome(){
		
		ModelAndView mv = new ModelAndView("homepage");
		return mv;
	}
	
	@RequestMapping(value="/homepage", method = RequestMethod.GET)
	public ModelAndView getCreateForm(){
		
		ModelAndView mv = new ModelAndView("homepage");
		return mv;
	}
	
	@RequestMapping(value="/submitForm.html", method = RequestMethod.POST)
	public String submitDetails(@ModelAttribute("details") HomePage details , RedirectAttributes redir){
		String id = details.getId();
		DBconnect db = new DBconnect();
		db.insertUserDetails(details);
		db.close();
		String ret = "redirect:/homepage/"+id;
		redir.addFlashAttribute("details", details);
		redir.addFlashAttribute("header" , "Created User Details");
		return ret;
	}
	
	@RequestMapping(value="/homepage/{id}", method = RequestMethod.GET)
	public ModelAndView getUserDetails(@PathVariable("id") String id, 
										@RequestParam(value="brief", required = false) String brief,
										@ModelAttribute("header") String hdr,
										HttpServletRequest request){
		HomePage details = new HomePage();
		DBconnect db = new DBconnect();
		details = db.getUserDetails(id);
		db.close();
		ModelAndView mv;
		System.out.println(details.getId());
		if(details.getId() != null)
			mv = new ModelAndView("userdetails");
		else{
			String err = "Sorry, the requested user with ID " + id + " does not exist";
			request.setAttribute("err", err);
			throw new ResourceNotFoundException();
		}
		mv.addObject("details", details);
		System.out.println("header" + hdr);
		mv.addObject("hdr", hdr);
		if(brief != null && "true".equalsIgnoreCase(brief))
			mv.addObject("json", "yes");
		else
			mv.addObject("json", "no");
		
		System.out.println("json " + brief);
		
		return mv;
	}
	
	
	@RequestMapping(value="/homepage/{id}", method=RequestMethod.POST)
	public String updateUserDetails(@ModelAttribute("details") HomePage details,
									RedirectAttributes redir){
		System.out.println("lastname " + details.getLastname());
		String ret = "redirect:/homepage/"+ details.getId();
		DBconnect db = new DBconnect();
		db.updateUserDetails(details);
		db.close();
		redir.addFlashAttribute("details", details);
		redir.addFlashAttribute("header" , "Updated user details");
		return ret;		
	}
	
	@RequestMapping(value="/homepage/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable("id") String id, HttpServletRequest request){
		System.out.println("Under delete function");
		DBconnect db = new DBconnect();
		int res = db.deleteUser(id);
		if(res != 1){
			String err = "Sorry, the requested user with ID " + id + " does not exist";
			request.setAttribute("err", err);
			throw new ResourceNotFoundException();
		}
		db.close();
		String ret = "redirect:/homepage";
		//ModelAndView mv = new ModelAndView("homepage");
		return ret;
		
	}
	
}
