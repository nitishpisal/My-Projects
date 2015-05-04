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
	private Userdetail proposerId;
	
	@ManyToOne
	@JoinColumn(name="proposalforpostid",referencedColumnName = "postid")
	private RequiredBooks proposalForPostId;
	
	private String proposal;
	
	private char accepted;
	private String active;
	
	/**
	 * constructors
	 * @return
	 */
	public Proposals(){}
	
	public Proposals(String proposal, char accepted){
		this.proposal = proposal;
		this.accepted = accepted;
		this.active = "yes";
		
	}
	
	public long getProposalID() {
		return proposalID;
	}
	public void setProposalID(long proposalID) {
		this.proposalID = proposalID;
	}
	public Userdetail getProposerId() {
		return proposerId;
	}
	public void setProposerId(Userdetail proposerId) {
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	
	
}
