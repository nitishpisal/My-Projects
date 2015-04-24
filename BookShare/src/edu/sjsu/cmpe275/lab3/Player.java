package edu.sjsu.cmpe275.lab3;

import javax.persistence.Transient;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;

//Annotation defining that this class is an entity to be mapped to DB
@Entity
//for storing it to some other table use:
//@Table(name="Player_Table")
public class Player implements Record{
	@Id @GeneratedValue(strategy = GenerationType.AUTO) // for id autogeneration (strategy = GenerationType=Auto...)
	private long id;
    private String firstname;
    private String lastname;
    private String email;
    private String description;
    //private int sponsorid;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="addressid")
    private Address address;
    
    /*@ManyToOne
    @JoinColumn(name="sponsorid")  */
    @Transient
    private Sponsor sponsor;
    
    private String opponents;
    
    public Player(){
    	
    }
    public void playerUpdate(String firstname, String lastname, String email, String desc){
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.email = email;
    	this.description = desc;
    }
    
    public Player(String firstname, String lastname, String email, String desc){
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.email = email;
    	this.description = desc;   	
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Sponsor getSponsor() {
		return sponsor;
	}
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}
	public String getOpponents() {
		return opponents;
	}
	public void setOpponents(String opponents) {
		this.opponents = opponents;
	}
}
