package edu.sjsu.cmpe275.lab3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Proposals implements Record{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="proposalid")
	private long proposalID;
	
	@ManyToOne
	@JoinColumn(name="proposerid",referencedColumnName = "userid")
	private Login proposerId;
	
	@ManyToOne
	@JoinColumn(name="proposalforpostid",referencedColumnName = "postid")
	private RequiredBooks proposalForPostId;
	
	private String proposal;
	
	private String bookdetails;
	private String username;
	private String phno;
	
	private char accepted;
	
	/**
	 * constructors
	 * @return
	 */
	public Proposals(){}
	
	public Proposals(String proposal, char accepted, String username, String phno, String bookdetails){
		this.proposal = proposal;
		this.accepted = accepted;
		this.username = username;
		this.phno = phno;
		this.bookdetails = bookdetails;
	}
	
	public long getProposalID() {
		return proposalID;
	}
	public void setProposalID(long proposalID) {
		this.proposalID = proposalID;
	}
	public Login getProposerId() {
		return proposerId;
	}
	public void setProposerId(Login proposerId) {
		this.proposerId = proposerId;
	}
	public RequiredBooks getProposalForPostId() {
		return proposalForPostId;
	}
	public void setProposalForPostId(RequiredBooks proposalForPostId) {
		this.proposalForPostId = proposalForPostId;
	}
	public String getProposal() {
		return proposal;
	}
	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	public char getAccepted() {
		return accepted;
	}
	public void setAccepted(char accepted) {
		this.accepted = accepted;
	}

	public String getBookdetails() {
		return bookdetails;
	}

	public void setBookdetails(String bookdetails) {
		this.bookdetails = bookdetails;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}
	
	
}
