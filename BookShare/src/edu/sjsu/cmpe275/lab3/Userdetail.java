package edu.sjsu.cmpe275.lab3;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class Userdetail implements Record{
	
	@Id @GeneratedValue(generator="newGen") //name of the primary key generator
	@GenericGenerator(name="newGen", strategy="foreign" , parameters = {@Parameter(value="login" , name="property")})
	private long userid;
	
	private String email;
	private String firstname;
	private String lastname;
	private String description;
	private String phoneno;
	
	@Column(name="dealerorindividual")
	private String dealerOrIndividual;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="addressid")
    private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userid")
	private Login login;
	
	/**
	 * Constructors
	 * @return
	 */
	
	Userdetail(){}
	Userdetail(String firstname, String lastname, String email, String desc, String doi,String phno){
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.description = desc;
		this.dealerOrIndividual = doi;
		this.phoneno = phno;
	}
	
	//-----------------------//
	
	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
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

	public String getDealerOrIndividual() {
		return dealerOrIndividual;
	}

	public void setDealerOrIndividual(String dealerOrIndividual) {
		this.dealerOrIndividual = dealerOrIndividual;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	

}
