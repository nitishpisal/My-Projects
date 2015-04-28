package edu.sjsu.cmpe275.lab3;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table (name="Requiredbooks")
public class RequiredBooks implements Record{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="postid")
	private long postId;
	
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int year;
	private int quantity;
	
	@ManyToOne
    @JoinColumn(name="postuserid",referencedColumnName = "userid")
	private Login postUserId;
	
	/**
	 * Constructors
	 */
	
	public RequiredBooks(){}
	
	public RequiredBooks(String isbn, String title, String author, String publisher, int year, int quantity){
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
		this.quantity = quantity;
		
	}
	
	/**
	 * getter and setters
	 * @return
	 */
	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Login getPostUserId() {
		return postUserId;
	}

	public void setPostUserId(Login postUserId) {
		this.postUserId = postUserId;
	}
	
	
	
	
}
