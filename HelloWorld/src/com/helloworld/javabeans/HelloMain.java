package com.helloworld.javabeans;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		GreeterClass greet1 = (GreeterClass)context.getBean("greeter");
		GreeterClass greet2 = (GreeterClass)context.getBean("greeter2");
		
		
		System.out.println(greet1.getGreeting());
		System.out.println(greet2.getGreeting());
		
		
	}

}
