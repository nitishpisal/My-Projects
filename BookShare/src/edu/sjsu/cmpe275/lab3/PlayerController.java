package edu.sjsu.cmpe275.lab3;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.context.request.WebRequest;

import com.sun.javafx.collections.MappingChange.Map;
@Controller
public class PlayerController {
	
	//Home page
	
	
	/*//Register player page
	@RequestMapping(value="/register/{who}", method = RequestMethod.GET)
	public ModelAndView getplayerRegister(@PathVariable("who") String who){
		ModelAndView mv = new ModelAndView("create");
		if(who.equalsIgnoreCase("player")){
			mv.addObject("who", "player");
		}
		else{
			mv.addObject("who","sponsor");
		}
		return mv;
	}*/
	
	
}
