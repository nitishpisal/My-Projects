package edu.sjsu.cmpe275.lab3;

import java.sql.Date;

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
public class Orders implements Record {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="orderid")
	private long orderId;
	
	@ManyToOne
    @JoinColumn(name="userid",referencedColumnName = "userid")
	private Userdetail userId;
	
	@ManyToOne
    @JoinColumn(name="bookid",referencedColumnName = "bookid")
	private Books bookId;
	
	private int quantity;
	private float amount;
	
	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	/**
	 * Constructors
	 * @return
	 */
	
	public Orders(){}
	public Orders(int quantity, float amount){
		this.quantity = quantity;
		this.amount = amount;
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Userdetail getUserId() {
		return userId;
	}

	public void setUserId(Userdetail userId) {
		this.userId = userId;
	}

	public Books getBookId() {
		return bookId;
	}

	public void setBookId(Books bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
