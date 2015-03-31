package com.helloworld.javabeans;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class GreeterClass implements Greeter {

	private List<String> names = new ArrayList<String>();
	
	@Override
	public void setNames(List<String> inNames) {
		//System.out.println(names2);
		names.addAll(inNames);
		
	}

	@Override
	public String getGreeting() {
		ListIterator<String> it = names.listIterator();
		String result = "Hello World from ";
		String name = "";
		int count = 0;
		while(it.hasNext()){
			count++;
			name = (String)it.next();
			if(count > 1 && !it.hasNext())
				result = result + " and ";
			else if(count > 1)
				result = result + ", ";
			result = result + name;
		}
		
		return result + "!";
	}

}
