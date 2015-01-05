package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LeaveType;
import dao.LeaveTypeSql;

/**
 * Servlet implementation class deletetype
 */
@WebServlet("/deletetype")
public class deletetype extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletetype() {
        super();
        // TODO Auto-generated constructor stub
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
		int id = Integer.parseInt(request.getParameter("typeid"));
		LeaveTypeSql lts = new LeaveTypeSql();
		LeaveType lt= new LeaveType();
		lt.setLeaveid(id);
		try
		{
			lts.deleteType(lt);
			String del = "Delete Successfully!";
			request.setAttribute("delsuccess",del);
			RequestDispatcher rd = request.getRequestDispatcher("/leavetype");
			rd.forward(request, response);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			request.setAttribute("error", "An error happened,Please check what you did!");
			RequestDispatcher rd = request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		}
	}

}
