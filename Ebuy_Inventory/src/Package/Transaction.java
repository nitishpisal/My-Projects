package Package;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transaction
 */

public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int intQuantity = 0;
		//variables for Transaction table
		String name, cardNo, cvv, billingAdd, password;
		name = cardNo = cvv = billingAdd = password = "";
		Validation val =  new Validation();
		request.setAttribute("valid", "true");
		//data for order table
		cardNo = request.getParameter("cardNo");
		cvv = request.getParameter("cvv");
		int userId = (Integer) request.getSession().getAttribute("userID");
	//	int intUserId = Integer.parseInt(userId);
		String productId = (String) request.getSession().getAttribute("ProductID");
		String quantity = (String) request.getSession().getAttribute("quantity");
		if (quantity != null){
			intQuantity = Integer.parseInt(quantity);
		}
		
		name = request.getParameter("cardName");
		billingAdd = request.getParameter("address");
		
		if (cardNo.length()!= 10 || val.isInteger(cardNo) == false){
			System.out.println("card length " + cardNo.length() + " " + val.isInteger(cardNo));
			request.setAttribute("cardInvalid", "invalid");
			request.setAttribute("valid", "cardFalse");
			request.getRequestDispatcher("/transaction.jsp").forward(request, response);
			return;
		}
	
		if (cvv.length()!= 3 || val.isInteger(cvv) == false){
			request.setAttribute("cvvInvalid", "invalid");
			request.setAttribute("valid", "cvvFalse");
			request.getRequestDispatcher("/transaction.jsp").forward(request, response);
			return;
		}
		
		
		DBconnect db = new DBconnect();
		String status = db.placeOrder(userId, productId, intQuantity, name, billingAdd, cardNo);
		request.setAttribute("ProductID",(String)request.getSession().getAttribute("ProductID"));
		request.setAttribute("sTotal",(String)request.getSession().getAttribute("sTotal"));
		request.setAttribute("image",(String)request.getSession().getAttribute("sessionImage"));
		System.out.println("image name " + request.getSession().getAttribute("sessionImage"));
		System.out.println("request " + request.getAttribute("image"));
		System.out.println(request.getSession().getAttribute("sTotal"));
		
		String[] result = status.split(",");
		String transactionId = result[0];
		String orderId = result[1];
		request.setAttribute("transactionId", transactionId);
		request.setAttribute("orderId", orderId);
		
		if (status != null){
			request.getRequestDispatcher("/orderConfirmation.jsp").forward(request, response);
		}
		
	}

}
