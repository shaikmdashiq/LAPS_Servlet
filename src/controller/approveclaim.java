package controller;

import java.io.IOException;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClaimSql;
import dao.StaffSql;

/**
 * Servlet implementation class approveclaim
 */
@WebServlet("/approveclaim")
public class approveclaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public approveclaim() {
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
		ClaimSql ss = new ClaimSql();
		int id = Integer.parseInt(request.getParameter("cid"));
		StaffSql sta = new StaffSql();
		String staffid = request.getParameter("sid");
		try
		{
			ss.deleteClaim(id);
			sta.updateCompensation(staffid);
			String approve = "Approve Compensation Successfully !";
			request.setAttribute("approve", approve);
			RequestDispatcher rd= request.getRequestDispatcher("/showclaim");
			rd.forward(request, response);
		}catch(Exception e)
		{
			String erro = "wooopps..Something wrong !";
			request.setAttribute("error",erro);
			RequestDispatcher rd= request.getRequestDispatcher("/showclaim");
			rd.forward(request, response);
		}
	}

}
