package Package;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 * Servlet implementation class Login
 */
//@WebServlet(description = "Login Servlet", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("We are here in the login servlet");
		int dbUserId = 0;
		String quantity = (String)request.getSession().getAttribute("quantity");
		HttpSession session = request.getSession();
		request.setAttribute("quantity", quantity);
		String inUserName = request.getParameter("username");
		String inPasswd =  request.getParameter("password");
		String msg = "Hello ";

		System.out.println("username and password " + inUserName + " " + inPasswd);
		if(inUserName.equals(null) || inPasswd.equals(null)){
			
			PrintWriter out = response.getWriter();
			out.println("<font size='6' color='red'>" + msg + "</font>");
		}
		
		SHA1Class pass = new SHA1Class();
		String encryptPass =  pass.encryptPassword(inPasswd);
		
		DBconnect db = new DBconnect();
		String firstName = db.verifyLogin(inUserName, encryptPass);
		String[] nameAndId = firstName.split(",");
		if(!nameAndId[1].isEmpty()){
			dbUserId = Integer.parseInt(nameAndId[1]);
		}
		String home = (String)session.getAttribute("backHome");
		System.out.println("Home value at login" + home);
		
		if(nameAndId[0] != "" && nameAndId[1] !=""){
		//	response.sendRedirect(response.encodeRedirectURL("/Ebuy_Inventory/transaction.jsp"));
			if(home != null){
				if (home.equals("home")){
				
					session.setAttribute("userName", nameAndId[0]);
					session.setAttribute("userID", Integer.parseInt(nameAndId[1]));
					response.sendRedirect("http://localhost:4000/");
				}
				else {
					session.setAttribute("userName", nameAndId[0]);
					session.setAttribute("userID", Integer.parseInt(nameAndId[1]));
					List <String> orderList = new ArrayList<String>();
					orderList = db.cancelOrder(dbUserId);
					for(String s : orderList){
						System.out.println("We are here " + s);
					}
					session.setAttribute("orderList", orderList);
					request.getRequestDispatcher("/cancel.jsp").forward(request, response);
				}
			}
			else{
				session.setAttribute("userName", nameAndId[0]);
				session.setAttribute("userID", Integer.parseInt(nameAndId[1]));
				request.setAttribute("valid", "true");
				request.getRequestDispatcher("/transaction.jsp").forward(request, response);
			}
			
		}
		else{
			msg = "Incorrect Username or Password";
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<font size='6' color='red'>" + msg + "</font>");
			response.sendRedirect(response.encodeRedirectURL("/Ebuy_Inventory/login.jsp"));
		}
	}

}
