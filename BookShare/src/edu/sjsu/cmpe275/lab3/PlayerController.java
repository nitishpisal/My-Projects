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
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView getHome(){
		
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	//Register player page
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
	}
	
	//get Player
	@RequestMapping(value="/player/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Player> getPlayer(@PathVariable("id") long id){
		Crud c = new Crud();
		Player p = new Player();
		p = (Player)c.get(p, id);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		if(p==null)
			return new ResponseEntity<Player>(p, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Player>(p, HttpStatus.OK);
	}
	
	//call update player
	@RequestMapping(value="/update/player/{id}", method = RequestMethod.GET)
	public ModelAndView callUpdateFromHome(@PathVariable("id") long id){
		Crud c = new Crud();
		Player p = new Player();
		p = (Player)c.get(p, id);
		ModelAndView mv = new ModelAndView("update");
		mv.addObject("who", "player");
		if(p==null)
			mv.addObject("valid", "no");
		else
			mv.addObject("valid","yes");
		mv.addObject("details", p);
		return mv;
	}
	
	//Update a player details
	@RequestMapping(value="/player/{id}", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Player> updatePlayer(@PathVariable("id") long id,
									@RequestParam("firstname") String firstname,
									@RequestParam("lastname") String lastname,
									@RequestParam("email") String email,
									@RequestParam("street") String street,
									@RequestParam("city") String city,
									@RequestParam("state") String state,
									@RequestParam("zip") String zip,
									@RequestParam("description") String description,
									@RequestParam("sponsor") String sponsor){
		Crud c = new Crud();
		Address a = new Address();
		Player p = new Player();
		Sponsor s = new Sponsor();
		if(email.isEmpty() || firstname.isEmpty() || lastname.isEmpty())
			return new ResponseEntity<Player>(p,HttpStatus.BAD_REQUEST);
		
		int sponsorid = 0;
		try{
			sponsorid = Integer.parseInt(sponsor);
		}catch(Exception e){
			System.out.println("No sponsor");
		}
		s = (Sponsor) c.get(s, sponsorid);
		p = (Player)c.get(p, id);
		
		if(p == null)
			return new ResponseEntity<Player>(p,HttpStatus.NOT_FOUND);
		//get the old address
		a = (Address)c.get(a, p.getAddress().getAddressId());
		//update the address and player objects
		a.addressUpdate(street, city, state, zip);
		p.playerUpdate(firstname, lastname, email, description);
		//set the updated address and sponsor for player
		p.setAddress(a);
		p.setSponsor(s);
		//update the address and player in db
		c.update(a);
		c.update(p);
		p = (Player)c.get(p, id);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<Player>(p, HttpStatus.OK);
	}
	
	//Delete player
	
	@RequestMapping(value="/player/{id}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<String> deletePlayer(@PathVariable("id") long id, HttpServletRequest request){
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		Crud c = new Crud();
		Player p = new Player();
		p = (Player)c.get(p, id);
		if(p==null)
			return new ResponseEntity<String>("404 Not Found",HttpStatus.NOT_FOUND);
		c.delete(p);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);
		
	}
	
	//Create Opponents
	@RequestMapping(value="/opponents/{id1}/{id2}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<String> createOpponents(@PathVariable("id1") long id1,
																@PathVariable("id2") long id2){
		
		Crud c = new Crud();
		Player p1 = new Player();
		p1 = (Player) c.get(p1, id1);
		Player p2 = new Player();
		p2 = (Player) c.get(p2, id2);
		String val = "";
		String[] val2;
		String forId1 = String.valueOf(id1);
		String forId2 = String.valueOf(id2);
		if(p1 == null || p2 == null)
			return new ResponseEntity<String>("404 Not Found" , HttpStatus.NOT_FOUND);
		
		if(forId1.equalsIgnoreCase(forId2))
			return new ResponseEntity<String>("400 Bad Request - opponent of itself" , HttpStatus.BAD_REQUEST);
		
		if(p1.getOpponents() == null || p1.getOpponents().isEmpty())
			val = forId2;
		else{
			val = p1.getOpponents();
			val2 = val.split(",");
			for (int i=0; i<val2.length; i++){
				if(val2[i].equals(forId2))
					return new ResponseEntity<String>("200 OK" , HttpStatus.OK);
			}
			val = val + "," + forId2;
		}
		p1.setOpponents(val);
		c.update(p1);
			
		if(p2.getOpponents() == null || p2.getOpponents().isEmpty())
			val = forId1;
		else{
			val = p2.getOpponents();
			val2 = val.split(",");
			for (int i=0; i<val2.length; i++){
				if(val2[i].equals(forId1))
					return new ResponseEntity<String>("200 OK" , HttpStatus.OK);
			}
			val = val + "," + forId1;
		}
		p2.setOpponents(val);
		c.update(p2);
		
		
		return new ResponseEntity<String>("200 Ok", HttpStatus.OK);
	}
	
	@RequestMapping(value="/opponents/{id1}/{id2}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<String> removeOpponents(@PathVariable("id1") long id1,
																@PathVariable("id2") long id2){
		
		Crud c = new Crud();
		Player p1 = new Player();
		p1 = (Player) c.get(p1, id1);
		Player p2 = new Player();
		p2 = (Player) c.get(p2, id2);
		String val = "";
		String[] val2;
		String[] newVal2;
		String[] newVal3;
		String forId1 = String.valueOf(id1);
		String forId2 = String.valueOf(id2);
		//Check if the two players exist
		if(p1 == null || p2 == null)
			return new ResponseEntity<String>("404 Not Found" , HttpStatus.NOT_FOUND);
		
		
		//Check if the players have at least one opponent
		if(p2.getOpponents() == null || p2.getOpponents().isEmpty() 
				|| p1.getOpponents() == null || p1.getOpponents().isEmpty() )
			return new ResponseEntity<String>("404 They are not oponents" , HttpStatus.NOT_FOUND);
		
		val2 = p1.getOpponents().split(",");
		newVal2 = new String[val2.length];
		// check if p1 is having P2 as opponent and vice versa
		//update if present
		for(int i=0, j=0; i<val2.length; i++,j++){
			if(val2[i].equalsIgnoreCase(forId2)){
				continue;
			}
			newVal2[j] = val2[i];
		}
		// join back the array and save it to P1
		String[] newValArr2 = Arrays.copyOfRange(newVal2, 0, newVal2.length-1);
		p1.setOpponents(String.join(",", newValArr2));
		
		val2 = p2.getOpponents().split(",");
		newVal3 = new String[val2.length];
		for(int i=0, j=0; i<val2.length; i++, j++){
			if(val2[i].equalsIgnoreCase(forId1)){
				continue;
			}
			newVal3[j] = val2[i];
		}
		String[] newValArr3 = Arrays.copyOfRange(newVal3, 0, newVal3.length-1);
		//Join back the array save it to P2
		p2.setOpponents(String.join(",", newValArr3));
		 
		c.update(p1);
		c.update(p2);
		
		return new ResponseEntity<String>("200 OK Opponents removed" , HttpStatus.OK);
		
	}
	
	
	
}
