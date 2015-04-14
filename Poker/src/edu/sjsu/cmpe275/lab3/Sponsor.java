package edu.sjsu.cmpe275.lab3;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sponsor implements Record{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
	private String name;
    private String description;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="addressid")
    private Address address;
    
    public Sponsor(){}
    
    public void sponsorUpdate(String name, String description){
    	this.name = name;
    	this.description = description;
    }
    
    public Sponsor(String name, String description){
    	this.name = name;
    	this.description = description;
    }
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
