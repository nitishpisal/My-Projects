package Package;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CancelOrder
 */
public class CancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ordNo = request.getParameter("orderNo");
		ArrayList<String> array =(ArrayList)request.getSession().getAttribute("orderList");
		if (array.contains(ordNo)){
			
			DBconnect db = new DBconnect();
			int status  = db.proceedCancellation(Integer.parseInt(ordNo));
			
			if (status == 1){
				request.setAttribute("orderNo", ordNo);
				request.getRequestDispatcher("/cancelSummary.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("/Ebuy_Inventory/cancel.jsp");
			}
		}
		else {
			response.sendRedirect("/Ebuy_Inventory/cancel.jsp");
		}
		
	}

}
