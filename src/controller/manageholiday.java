package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Holiday;
import dao.HolidaySql;

/**
 * Servlet implementation class manageholiday
 */
@WebServlet("/manageholiday")
public class manageholiday extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageholiday() {
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
		HolidaySql hs = new HolidaySql();
		ArrayList<Holiday> holi = hs.getAllHoliday();
		if(holi.size()>0&&null!=holi)
		{
			request.setAttribute("holiday", holi);
			RequestDispatcher rd= request.getRequestDispatcher("showholiday.jsp");
			rd.forward(request, response);
		}else
		{
			String nor = "No records!";
			request.setAttribute("norec", nor);
			RequestDispatcher rd= request.getRequestDispatcher("showholiday.jsp");
			rd.forward(request, response);
		}
		
	}

}
