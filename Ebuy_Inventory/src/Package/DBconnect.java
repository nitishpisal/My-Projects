package Package;
//import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import com.sun.corba.se.pept.transport.Connection;


public class DBconnect {

	private Connection con;
	private Statement st;
	private Statement st1;
	private ResultSet rs;
	private String query;
	private String username = "";
	private String password = "";
	
	public DBconnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "root", "");
			st =con.createStatement();
		}catch(Exception ex){
			System.out.println("Error :" + ex);
		}
	}
	
	public void getData(){
		try{
			System.out.println("We are here");
			query = "select * from logindetails";
			rs = st.executeQuery(query);
			System.out.println("Results from DB");
			while(rs.next()){
					int userID = rs.getInt("USERID");
					String userName = rs.getString("USERNAME");
					String password = rs.getString("PASSWORD");
					
					System.out.println("RECORD ID :" + userID);
					System.out.println("TENANT ID :" + userName);
					System.out.println("TENANT NAME :" + password);
			}
		}catch(Exception ex){
			System.out.println("Error :" + ex);
		}
	}
	
	protected String verifyLogin(String inUserName, String inPassword){
		String outPassword = "";
		username = inUserName;
		password = inPassword;
		
		try{
			query = "select PASSWORD from LOGINDETAILS where USERNAME = " + "'" + username + "'";
			System.out.println(query);
			rs = st.executeQuery(query);
			if(!rs.next()){
				System.out.println("User Does not exist");
				return "";
			}
			else{
				outPassword = rs.getString("PASSWORD");
				if(outPassword.isEmpty() || outPassword.equals(null)){
					return "";
				}
				if(outPassword.equals(password)){
					try{
						String firstName = "";
						int userId = 0;
						query = "select u.FIRSTNAME, l.USERID from USERDETAILS u, LOGINDETAILS l where l.USERNAME = " + "'" + username +"'" + " and u.USERID = l.USERID";
						rs = st.executeQuery(query);
						while(rs.next()){
						firstName = rs.getString("FIRSTNAME");
						userId = rs.getInt("USERID");
						}
						return firstName + "," + userId;
					}catch(Exception ee){
						System.out.println("Error in fetching first name from userdetails " + ee);
					}
					
				}
				else{
					return "";
				}
			}
			
		}catch(Exception ex){
			System.out.println("Database Error: " + ex);
		}
		return "";
	}
	
	protected String registerUser(String inEmail, String inUsername, String firstName, String lastName, String inPhNo, String inDob, String inPassword){
		username = inUsername;
		password = inPassword;
		String query1, query2, firstNm, lastNm, email, phone,dob;
		query1 = query2 = firstNm = lastNm =email = phone =dob ="";

		int userId = 0;
		firstNm = firstName;
		lastNm = lastName;
		email = inEmail;
		phone = inPhNo;
		dob = inDob;
		
		try{
			query1 = "insert into LOGINDETAILS (USERNAME,PASSWORD) values (" + "'"+username+"'" + "," + "'"+password+"'" +")" ;
			System.out.println(query1);
			int insertStatus = st.executeUpdate(query1);
			System.out.println("Login details Insert Status  "+insertStatus);
			if(insertStatus == 1){
			query2 = "select USERID from LOGINDETAILS where USERNAME = " + "'"+username+"'" ;
			System.out.println(query2);
			rs = st.executeQuery(query2);
			while(rs.next()){
			userId = rs.getInt("USERID");
			}
			System.out.println("New User " + userId);
			query = "insert into USERDETAILS (USERID, EMAIL, FIRSTNAME, LASTNAME, PHONENO, DOB) values (" + userId + "," + "'"+email+"'," +"'" +firstNm+ "'," +"'" +lastNm+ "'," +"'"+phone+"'," + "'"+dob+"'" +")";
			System.out.println(query);
			insertStatus = st.executeUpdate(query);
			System.out.println("User details insert status " + insertStatus);
			}
			return firstNm +"," + userId;
		}catch(Exception ex){
			System.out.println("Error in Inserting : " + ex);
			if (userId !=0){
				try{
				query = "delete from LOGINDETAILS where USERID = " + userId;
				st.executeUpdate(query);
				}catch(Exception e){
					System.out.println("Error in catch block, Cannot remove user.");
				}
			}
		}

		return "";
	}
	
	protected String getProductDetails(String productId){
		
		String pId = productId;
		String pName = "";
		int qty = 0;
		int price = 0;
		String concat = "";
		
		try{
			String query = "select PRODUCTNAME, QUANTITY, PRICE from PRODUCTDETAILS where PRODUCTID = " + "'" + pId + "'";
			rs =st.executeQuery(query);
			while(rs.next()){
				 pName = rs.getString("PRODUCTNAME");
				 qty = rs.getInt("QUANTITY");
				 price = rs.getInt("PRICE");
				 concat = pName + "," + qty +"," +price;
				 System.out.println("product data : " + concat);
		}
		}catch(Exception ex){
			System.out.println("Error Database Exception while fetching product Record : " + ex);
		}
		
		return concat;
	}

	protected String placeOrder(int intUserId, String productId, int intQuantity,
			String name, String billingAdd, String cardNo) {
		//select max order number from orer table
		int orderNo = 100;
		
		try{
			query = "select max(ORDERNO) as ORDERNO from ORDERDETAILS";
			rs = st.executeQuery(query);
			while(rs.next()){
				orderNo = rs.getInt("ORDERNO") + 1;
			}
			
			//Insert into the Order Details
			try{
				query = "insert into ORDERDETAILS (ORDERNO,USERID,PRODUCTID,QUANTITY) values(" + orderNo + "," + intUserId +"," + "'" +productId+ "'," + intQuantity +")";
				System.out.println(query);
				int orderStatus = st.executeUpdate(query);
				//Insert the transaction for the above order if success
				if(orderStatus == 1){
					String txnStatus = transaction(orderNo, name, productId, intQuantity);
					return txnStatus;
					
				}
			}catch(Exception ex){
				System.out.println("Insertion to Order table failed " +ex);
			}
			
		}catch(Exception ee){
			System.out.println("Unable to fetch Order Number" + ee);
		}
	return "";		
	
	}
	
	private String transaction(int orderNo, String name, String productID, int quantity){
		String result = "";
		try{
			query = "insert into transactiondetails (ORDERID, NAME, STATUS) values (" + orderNo +",'" +name + "','INPROGRESS')" ;
			System.out.println(query);
			int status = st.executeUpdate(query);	
			if (status == 1){
				query = "update PRODUCTDETAILS set quantity = quantity - " + quantity + " where PRODUCTID = " + "'" + productID + "'";
				System.out.println(query);
				status = st.executeUpdate(query);
				if (status == 1){
					//Query to check if Quantity drops below zero
					
					// Fetching Order Number from our transactions
					query = "select TRANSACTIONID, ORDERID from TRANSACTIONDETAILS where ORDERID = " + orderNo;
					rs = st.executeQuery(query);
					while(rs.next()){
						result = rs.getString("TRANSACTIONID");
						result = result + "," + rs.getString("ORDERID");
					}
					return result;
				}
				
			}
	
		}catch(Exception ex){
			System.out.println("Error in transaction " + ex);
		}	
		return "";
	}
	
	protected String chkQuantity(String productID, String quantity){
		try{
			query = "select QUANTITY from PRODUCTDETAILS where PRODUCTID = " + "'" + productID + "'" ; 
			rs = st.executeQuery(query);
			while(rs.next()){
				int qty = rs.getInt("QUANTITY");
				qty = qty - Integer.parseInt(quantity);
				if (qty < 0){
					return "no";
				}
				else{
					return "yes";
				}
			}
		}catch(Exception ex){
			System.out.println("Unable to check Quantity for the product " + ex);
		}
		return "yes";
	}
	
	private String getLastupdateTime(){
		String currentTime = "";
		int count = 0;
		try {
			query = "select DateDiff(curDate(),UPDATETIME) as DATEDIFF, COUNT from LASTUPDATE";
			rs =st.executeQuery(query);
			while(rs.next()){
				currentTime = rs.getString("DATEDIFF");
				count = rs.getInt("COUNT");
			}/*if (count == 0){
				try{
					query = "update LASTUPDATE set COUNT = 1";
					st.addBatch(query);
					query = "update LASTUPDATE set UPDATETIME = CURTIME()";
					st.addBatch(query);
					st.executeBatch();
				}catch(Exception e){
					System.out.println("Error in updating the count inside getLastUpdateTime " + e);
				}
			}*/
			return currentTime + "," + count;
		}catch(Exception ex){
			System.out.println("Unable to fetch the last update time "+ ex);
		}
		
		return "";
	}
	
	protected void updateStatus(){
		try{
			st1 =con.createStatement();
		}catch(Exception st){
			System.out.println("Unable to initialize statement " + st1);
		}
		String query1 = "";
		String orderId = "";
		String time = getLastupdateTime();
		String[] time1 = time.split(",");
		int lastUpdate = Integer.parseInt(time1[0]);
		int count = Integer.parseInt(time1[1]);
		int status = 0;
		System.out.println("Last update variable " + lastUpdate);
		if (lastUpdate >= 1)
		try{			
			query = "select DateDIFF(curDate(),TIME) as DIFF, ORDERID from Transactiondetails group by ORDERID having DIFF > 3";
			rs = st.executeQuery(query);
			
			int dateDiff = 0;
			while(rs.next()){
				orderId = orderId + "," + rs.getString("ORDERID");
				System.out.println("ORder Id's " + orderId);
				//dateDiff = rs.getInt("DIFF");
			/*	if (dateDiff > 3){
					try{
						query1 = "update TRANSACTIONDETAILS set STATUS = 'COMPLETED' WHERE ORDERID = " + orderId;
						status = st1.executeUpdate(query1);
					}catch(Exception e){
						System.out.println(e + " Unable to update Status for ORDER " + orderId);
					}
				}*/
				
			}
			try{
				query = "update TRANSACTIONDETAILS set STATUS = 'COMPLETED' WHERE ORDERID in (0" + orderId + ")";
				status = st.executeUpdate(query);
			}catch(Exception eex){
				
				System.out.println("unable to update the status for the orders " );
			}
			System.out.println("value of status = " + status);
			
				try{
					query = "update LASTUPDATE set COUNT = 0";
					st.addBatch(query);
					query = "update LASTUPDATE set UPDATETIME = CURTIME()";
					st.addBatch(query);
					st.executeBatch();
				}catch(Exception ee){
					System.out.println("Unable to Update count at UpdateStatus function " + ee);
				}
			
		}catch (Exception ex){
			System.out.println("Unable to run updates for orders "+ ex);
		}
		
	}
	
	protected List<String> cancelOrder(int userId){
		
		List<String> orderList = new ArrayList<String>();
		String queryOp = null;
		query = "SELECT O.ORDERNO FROM ORDERDETAILS O, TRANSACTIONDETAILS T WHERE O.USERID = " + userId + " AND O.ORDERNO = T.ORDERID AND T.STATUS <> 'COMPLETED'";
		try{
			rs = st.executeQuery(query);
			while(rs.next()){
				queryOp = "" + rs.getInt("ORDERNO");
				orderList.add(queryOp);
			}
		}catch(Exception ex){
			System.out.println("Unable to fetch the orders for cancellation for user id + " + userId + " " + ex);
			
		}
		return orderList;
	}
	
		protected int proceedCancellation(int ordNo){
		
		int status = 0;
		query = "UPDATE TRANSACTIONDETAILS SET STATUS = 'CANCELLED' WHERE ORDERID = " + ordNo;
		try{
			status =  st.executeUpdate(query);
			return status;
		}catch(Exception ex){
			System.out.println("Unable to cancel the order  " + ordNo + " " + ex);
			
		}
		return status;
	}
	
	
}
