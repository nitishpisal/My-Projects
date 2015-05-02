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

	/*protected List<Bids> getBids(long userId){
		
		List<Bids> bids = new ArrayList<Bids>();
		query = "select * from Bids Bi inner join Books Bo on "
				+ "Bi.bookid = Bo.bookid "
				+ "where Bo.owner = " + userId;
		try{
			rs = st.executeQuery(query);
			
			while(rs.next()){
				Bids g = new Bids();
				Books b = new Books();
				g.setBiddername(rs.getString("biddername"));
				g.setPhno(rs.getString("phno"));
				g.setPrice(rs.getInt("price"));
				g.setBookdetails(rs.getString("bookdetails"));
				g.setAccepted(rs.getString("accepted").charAt(0));
				b.setBookId(rs.getLong("bookid"));
				Crud c = new Crud();
				b = (Books) c.get(b, b.getBookId());
				g.setBookId(b);
				g.setBidId(rs.getLong("bidid"));
				
			}
		}catch(Exception ee){
			System.out.println("Unable to get the bids for the user " + userId);
			
		}
		
		return bids;
	}*/
	

	protected void close(){
		try{
			con.close();
		}catch(Exception e){
			System.out.println("Cannot close connection "+ e);
		}
	}
	
}