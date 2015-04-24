package edu.sjsu.cmpe275.lab3;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bids {
	
	@Id @GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="bidid")
	private long bidId;
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="bookid")
	private Books bookId;
	
	@ManyToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="userid")
	private Login bidder;
	
	private int price;
	
	//-----------Constructors-----------------------//
	
	
	//------------getter and setters ---------------//
	public long getBidId() {
		return bidId;
	}

	public void setBidId(long bidId) {
		this.bidId = bidId;
	}

	public Books getBookId() {
		return bookId;
	}

	public void setBookId(Books bookId) {
		this.bookId = bookId;
	}

	public Login getBidder() {
		return bidder;
	}

	public void setBidder(Login bidder) {
		this.bidder = bidder;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
