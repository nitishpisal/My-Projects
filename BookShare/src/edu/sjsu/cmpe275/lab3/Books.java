package edu.sjsu.cmpe275.lab3;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Books implements Record{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bookid")
	private long bookId;
	
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int year;
	private int price;
	private int quantity;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="owner",referencedColumnName = "userid")
	private Login owner;
	
	private char bid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Transient
	private Date time;
	
	private char available;

	//constructors-------------------
	
	
	
	
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Login getOwner() {
		return owner;
	}

	public void setOwner(Login owner) {
		this.owner = owner;
	}

	public char getBid() {
		return bid;
	}

	public void setBid(char bid) {
		this.bid = bid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public char getAvailable() {
		return available;
	}

	public void setAvailable(char available) {
		this.available = available;
	}
	
}
