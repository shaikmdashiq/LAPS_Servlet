package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LeaveForm;
import dao.LeaveFormSql;


/**
 * Servlet implementation class approveleave
 */
@WebServlet("/approveleave")
public class approveleave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public approveleave() {
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
		LeaveFormSql lf = new LeaveFormSql();
		try{
		ArrayList<LeaveForm> leavelist = lf.getAppliedLeave(request.getSession().getAttribute("logid").toString());
		if(null!=leavelist&&leavelist.size()>0)
		{
			request.setAttribute("leaveform",leavelist);
			RequestDispatcher rd= request.getRequestDispatcher("appliedleave.jsp");
			rd.forward(request, response);
		}else
		{
			String no = "No records!";
			request.setAttribute("norecord", no);
			RequestDispatcher rd= request.getRequestDispatcher("manageleave.jsp");
			rd.forward(request, response);
		}
		}catch(Exception e)
		{
			String error = "Woooppps...something wrong!";
			request.setAttribute("error",error);
			RequestDispatcher rd= request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		}
		
	}

}
