package Package;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Product
 */
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		final DBconnect db1 = new DBconnect();
		String productId = request.getParameter("productId");
		String productData = db1.getProductDetails(productId);
		db1.updateStatus();
		String[] pData = productData.split(",");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");
		int total  = Integer.parseInt(quantity) * Integer.parseInt(price);
		String sTotal = Integer.toString(total);
		
		String image = request.getParameter("image");
		System.out.println("image at product is " + image);
		HttpSession session = request.getSession();
		request.setAttribute("productID", productId);
		request.setAttribute("quantity", quantity);
		request.setAttribute("image", image);
		request.setAttribute("price", price);
		request.setAttribute("quantityAvl", pData[1]);
		request.setAttribute("description", pData[0]);
		request.setAttribute("total", total);
		session.setAttribute("ProductID", productId);
		session.setAttribute("quantity", quantity);
		session.setAttribute("sessionImage", image);
		session.setAttribute("sTotal", sTotal);
		
		String qtyAvl =  db1.chkQuantity(productId, quantity);
		if(qtyAvl.equals("no")){
			request.setAttribute("quantityNo", qtyAvl);
		}
		else{
			request.setAttribute("quantityNo", "yes");
		}
		
		
	/*	Runnable r = new Runnable() {
	         public void run() {
	        	
	             db1.updateStatus();
	             System.out.println("Under thread Execution");
	         }
	     };

	     new Thread(r).start();
	     try{
	    	 Thread.sleep(4000000);
	     }catch(InterruptedException e){
	    	 System.out.println("Thread caused Exception " + e);
	     }*/
		request.getRequestDispatcher("/orderSummary.jsp").forward(request, response);
		
		out.println("Session product Id is :" + (String)session.getAttribute("ProductID"));
	}

}
