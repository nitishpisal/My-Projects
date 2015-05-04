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
public class Bids implements Record{
	
	@Id @GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name="bidid")
	private long bidId;
	
	@ManyToOne
	@JoinColumn(name="bookid",referencedColumnName = "bookid")
	private Books bookId;
	
	@ManyToOne
	@JoinColumn(name="bidder",referencedColumnName = "userid")
	private Userdetail bidder;
	
	@Column(name="bidprice")
	private int bidPrice;
	
	/*private String biddername;
	private String phno;
	private String bookdetails;*/
	private char accepted;
	private String active;
	
	//-----------Constructors-----------------------//
	
	public Bids(){}
	
	public Bids(int bidPrice, char accepted){
		this.bidPrice = bidPrice;
		this.accepted = accepted;
		this.active = "yes";
	}
	
	
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

	public int getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(int price) {
		this.bidPrice = price;
	}

	public char getAccepted() {
		return accepted;
	}

	public void setAccepted(char accepted) {
		this.accepted = accepted;
	}

	public Userdetail getBidder() {
		return bidder;
	}

	public void setBidder(Userdetail bidder) {
		this.bidder = bidder;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	
}
