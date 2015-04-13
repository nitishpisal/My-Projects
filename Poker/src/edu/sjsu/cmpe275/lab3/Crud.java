package edu.sjsu.cmpe275.lab3;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Crud {
	Session session;
	SessionFactory s;
	public Crud(){}
	
	public long save(Record r){
		long id = 0;
		s = SessionFactoryObj.getSessionFactory();
		session = s.openSession();
		session.beginTransaction();
		session.save(r);
		session.getTransaction().commit();
		if(r instanceof edu.sjsu.cmpe275.lab3.Player){
			Player p = (Player)r;
			id = p.getId();
		}
		else if(r instanceof edu.sjsu.cmpe275.lab3.Sponsor){
			Sponsor s = (Sponsor)r;
			id = s.getId();
		}
		else if(r instanceof edu.sjsu.cmpe275.lab3.Address){
			Address a = (Address)r;
			id = a.getAddressId();
		}
		session.close();
		s.close();
		
		return id;
	}
	
	public Record get(Record r, long id){
		s = SessionFactoryObj.getSessionFactory();
		session = s.openSession();
		session.beginTransaction();
		Record newR;
		newR = (Record)session.get(r.getClass(), id);
		session.close();
		s.close();
		return newR;
	}
	
	public void update(Record r){
		s = SessionFactoryObj.getSessionFactory();
		session = s.openSession();
		session.beginTransaction();
		session.update(r);
		session.getTransaction().commit();
		session.close();
		s.close();
	}
	
	public void delete(Record r){
		s = SessionFactoryObj.getSessionFactory();
		session = s.openSession();
		session.beginTransaction();
		session.delete(r);
		session.getTransaction().commit();
		session.close();
		s.close();
	}

}
