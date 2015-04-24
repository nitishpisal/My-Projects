package edu.sjsu.cmpe275.lab3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address implements Record{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="addressid")
	private long addressId;
	private String street;
    private String city;
    private String state;
    private String zip;
    
    public Address(){}
    public Address(String street, String city, String state, String zip){
    	this.street = street;
    	this.city = city;
    	this.state = state;
    	this.zip = zip;    	
    }
    
    public void addressUpdate(String street, String city, String state, String zip){
    	this.street = street;
    	this.city = city;
    	this.state = state;
    	this.zip = zip;    
    }
    
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
    
    
}
