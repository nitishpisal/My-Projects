package edu.sjsu.cmpe275.lab3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Login implements Record{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long userid;
	
	@Column(name="username")
	private String userName;
	
	private String password;
	
	Login(){}
	Login(String username, String password){
		this.userName = username;
		this.password = password;
	}
	
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
