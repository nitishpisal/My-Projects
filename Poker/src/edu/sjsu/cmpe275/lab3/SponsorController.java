package edu.sjsu.cmpe275.lab3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SponsorController {

	//Creating sponsor
		@RequestMapping(value="/sponsor", method = RequestMethod.POST)
		public @ResponseBody ResponseEntity<Sponsor> registerSponsor(@RequestParam("name") String name,
											@RequestParam("street") String street,
											@RequestParam("city") String city,
											@RequestParam("state") String state,
											@RequestParam("zip") String zip,
											@RequestParam("description") String description){
			
			if(!name.isEmpty()){
				Sponsor s = new Sponsor(name, description);
				Address a = new Address(street, city, state, zip);
				s.setAddress(a);
				Crud c = new Crud();
				long id = c.save(s);
				s= (Sponsor)c.get(s, id);
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON);
					
				return new ResponseEntity<Sponsor>(s, HttpStatus.OK);
			}
			return new ResponseEntity<Sponsor>(HttpStatus.BAD_REQUEST);
				
			
		}
	
		//get Sponsor
		@RequestMapping(value="/sponsor/{id}", method = RequestMethod.GET)
		public @ResponseBody ResponseEntity<Sponsor> getPlayer(@PathVariable("id") long id){
			Crud c = new Crud();
			Sponsor s = new Sponsor();
			s = (Sponsor)c.get(s, id);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			if(s != null)
			return new ResponseEntity<Sponsor>(s, HttpStatus.OK);
			
			return new ResponseEntity<Sponsor>(s, HttpStatus.NOT_FOUND);
		}
		
		//get update form for Sponsor
		
		@RequestMapping(value="/update/sponsor/{id}", method = RequestMethod.GET)
		public ModelAndView callUpdateFromHome(@PathVariable("id") long id){
			Crud c = new Crud();
			Sponsor s = new Sponsor();
			s = (Sponsor)c.get(s, id);
			ModelAndView mv = new ModelAndView("update");
			mv.addObject("who", "sponsor");
			if(s==null)
				mv.addObject("valid", "no");
			else
				mv.addObject("valid","yes");
			mv.addObject("details", s);
			System.out.println("I am here");
			return mv;
		}
		
		//update the Sponsor Details
		
		@RequestMapping(value="/sponsor/{id}", method = RequestMethod.POST)
		public @ResponseBody ResponseEntity<Sponsor> updateSponsor(  @PathVariable("id") long id,
											@RequestParam("name") String name,
											@RequestParam("street") String street,
											@RequestParam("city") String city,
											@RequestParam("state") String state,
											@RequestParam("zip") String zip,
											@RequestParam("description") String description,
											HttpServletRequest request
											){
			
			if(name.isEmpty())
				return new ResponseEntity<Sponsor>(HttpStatus.BAD_REQUEST);
			Crud c = new Crud();
			Address a = new Address();
			Sponsor s = new Sponsor();
			s = (Sponsor)c.get(s, id);
			if(s == null)
				return new ResponseEntity<Sponsor>(s, HttpStatus.NOT_FOUND);
			
			a = (Address)c.get(a, s.getAddress().getAddressId());
			a.addressUpdate(street, city, state, zip);
			s.sponsorUpdate(name, description);
			s.setAddress(a);
			c.update(a);
			c.update(s);
			
			s = (Sponsor)c.get(s, id);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity<Sponsor>(s, HttpStatus.OK);
		}
		
		//Delete Sponsor
		@RequestMapping(value="/sponsor/{id}", method=RequestMethod.DELETE)
		public @ResponseBody ResponseEntity<String> deleteSponsor(@PathVariable("id") long id, HttpServletRequest request){
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			Crud c = new Crud();
			Sponsor s = new Sponsor();
			s = (Sponsor)c.get(s, id);
			if(s == null)
				return new ResponseEntity<String>("404 Not Found",HttpStatus.NOT_FOUND);
			//If a valid sponsor is present, try to delete it
			try{
				c.delete(s);
			}catch(Exception E){
				System.out.println("Unale to delete the spomsor");
				return new ResponseEntity<String>("400 Bad Request",HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<String>("Ok", HttpStatus.OK);
			
		}
		
		
		
}
