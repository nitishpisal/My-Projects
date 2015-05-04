package edu.sjsu.cmpe275.lab3;
//import java.beans.Statement;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
import java.util.concurrent.ExecutionException;

import org.apache.catalina.User;

//import com.sun.corba.se.pept.transport.Connection;


public class DBconnect {

	private Connection con = null;
	private Statement st;
	private Statement st1;
	private ResultSet rs;
	private String query;
	private String username = "";
	private String password = "";
	PreparedStatement preparedStatement = null;
	public DBconnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bookshare", "root", "");
			st =con.createStatement();
		}catch(Exception ex){
			System.out.println("Error :" + ex);
		}
	}

	protected int updateBids(long bookId){
		query = "update Bids set active = 'no' where bookid = " + bookId;
		int status = 0;
		try{
			status = st.executeUpdate(query);
		}catch(Exception e){
			System.out.println("Unable to update the bids in DB connect");
		}
		
		return status;
	}

	protected void close(){
		try{
			con.close();
		}catch(Exception e){
			System.out.println("Cannot close connection "+ e);
		}
	}
	
}