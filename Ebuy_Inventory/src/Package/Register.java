package Package;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public static long isNumeric(String phone){
    	long no = 0;
    	
    	try{
    		no = Long.parseLong(phone);
    	}catch(NumberFormatException  ex){
    		return 0;
    	}
    	return no;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email ,username ,phNo, dob, password, msg, firstName, lastName;
		email = username = phNo = dob = password = msg = firstName = lastName ="";
		email = request.getParameter("email");
		username = request.getParameter("username");
		phNo = request.getParameter("phNumber");
		dob = request.getParameter("dateOfBirth");
		password = request.getParameter("password");
		firstName = request.getParameter("firstName");
		lastName = request.getParameter("lastName");
		HttpSession session = request.getSession();
		
		long phoneNumber = isNumeric(phNo);
		
		if(String.valueOf(phoneNumber).length()!=10){
			msg = "Invalid Phone Number";
			phNo = null;
		}
		
		if (email.equals(null) || username.equals(null) || dob.equals(null) || password.equals(null) || firstName.equals(null)|| lastName.equals(null)){
			
			response.sendRedirect(response.encodeRedirectURL("/Ebuy_Inventory/signup.jsp"));
		}
		
		SHA1Class passwd = new SHA1Class();
		String encryptedPass = passwd.encryptPassword(password);
		
		DBconnect insert = new DBconnect();
		String details = insert.registerUser(email, username, firstName, lastName, phNo, dob, encryptedPass);
		String[] nameAndId = details.split(",");
		if(nameAndId[0] != "" && nameAndId[1] != ""){
			session.setAttribute("userName", firstName);
			session.setAttribute("userID", Integer.parseInt(nameAndId[1]));
			request.setAttribute("valid", "true");
			response.sendRedirect(response.encodeRedirectURL("/Ebuy_Inventory/transaction.jsp"));
		}
	}

}
