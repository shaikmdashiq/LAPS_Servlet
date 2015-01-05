package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HolidaySql;

/**
 * Servlet implementation class admindelholiday
 */
@WebServlet("/admindelholiday")
public class admindelholiday extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admindelholiday() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int hid = Integer.parseInt(request.getParameter("hid"));
		HolidaySql hs = new HolidaySql();
		try
		{
			hs.deleteHoliday(hid);
			String delete = "Delete Successfully!";
			request.setAttribute("del",delete);
			RequestDispatcher rd = request.getRequestDispatcher("/manageholiday");
			rd.forward(request, response);
		}catch(Exception e)
		{
			//e.printStackTrace();
			request.setAttribute("error", "An error happened,Please check what you did!");
			RequestDispatcher rd = request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
			
		}
		
	}

}
