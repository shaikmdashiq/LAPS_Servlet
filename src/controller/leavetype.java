package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LeaveType;
import dao.LeaveTypeSql;

/**
 * Servlet implementation class leavetype
 */
@WebServlet("/leavetype")
public class leavetype extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public leavetype() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LeaveTypeSql lt = new LeaveTypeSql();
		ArrayList<LeaveType> typelist = lt.getAllLeaveType();
		String type = request.getParameter("type");
		if(null!=typelist&&typelist.size()>0)
		{
			request.setAttribute("type", typelist);
			RequestDispatcher rd = request.getRequestDispatcher("leavetype.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
