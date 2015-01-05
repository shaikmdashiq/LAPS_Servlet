package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Staff;
import dao.StaffSql;

/**
 * Servlet implementation class managestaff
 */
@WebServlet("/managestaff")
public class managestaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public managestaff() {
        super();
        
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
		StaffSql ss = new StaffSql();
		ArrayList<Staff> slist = ss.getAllStaff();
		if(null!=slist&&slist.size()>0)
		{
			request.setAttribute("slist",slist);
			RequestDispatcher rd= request.getRequestDispatcher("managestaff.jsp");
			rd.forward(request, response);
		}else
		{
			String no = "No records!";
			request.setAttribute("norecord", no);
			RequestDispatcher rd= request.getRequestDispatcher("managestaff.jsp");
			rd.forward(request, response);
		}
	}

}
