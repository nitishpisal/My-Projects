package edu.sjsu.cmpe275.lab3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Feedback")
public class Feedback implements Record {

	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	@Column (name = "feedbackId")
	private int feedbackId;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userid")
	private Userdetail userId;
	
	private int ratingForUser;
	
	private String comments;
	
	private int rating;
	
	private String role;
	
	public Feedback(){}
	
	public int getRatingForUser() {
		return ratingForUser;
	}

	public void setRatingForUser(int ratingForUser) {
		this.ratingForUser = ratingForUser;
	}
	
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public Userdetail getUserId() {
		return userId;
	}
	public void setUserId(Userdetail userId) {
		this.userId = userId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		System.out.println("role is" + role);
		this.role = role;
	}
	
}