package edu.sjsu.cmpe275.lab3;

import java.text.Annotation;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class SessionFactoryObj {
		
	public static SessionFactory getSessionFactory(){
		return new AnnotationConfiguration().configure().buildSessionFactory();
	}

}
