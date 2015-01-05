package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LeaveForm;
import dao.LeaveFormSql;

/**
 * Servlet implementation class viewsubleave
 */
@WebServlet("/viewsubleave")
public class viewsubleave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewsubleave() {
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
		String logid = request.getSession().getAttribute("logid").toString();
		LeaveFormSql lf = new LeaveFormSql();
		try{
		ArrayList<LeaveForm> list = lf.getStaffLeave(logid);
		if(null!=list&&list.size()>0)
		{
			request.setAttribute("list",list);
			RequestDispatcher rd= request.getRequestDispatcher("subordinatehistory.jsp");
			rd.forward(request, response);
		}else
		{
			String no = "No Record!";
			request.setAttribute("norecord",no);
			RequestDispatcher rd= request.getRequestDispatcher("subordinatehistory.jsp");
			rd.forward(request, response);
		}
		}catch(Exception e)
		{
			String error = "Woooppp...something wrong!";
			request.setAttribute("error",error);
			RequestDispatcher rd= request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		}
	}

}
