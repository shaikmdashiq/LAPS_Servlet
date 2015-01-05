package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LeaveType;
import dao.LeaveTypeSql;

/**
 * Servlet implementation class updatetype
 */
@WebServlet("/updatetype")
public class updatetype extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatetype() {
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
		String ins = request.getParameter("ins");
		LeaveTypeSql lts = new LeaveTypeSql();
		LeaveType lt = null;
		try{
		if("t".equals(ins))
		{
			lt = new LeaveType();
			lt.setCreatedby(request.getSession().getAttribute("logid").toString());
			lt.setLeavetype(request.getParameter("leavetype"));
			lt.setLeaveid(Integer.parseInt(request.getParameter("addid")));
			lts.insertType(lt);
			String insert = "Add new Type successfully!";
			request.setAttribute("insert",insert);
			RequestDispatcher rd = request.getRequestDispatcher("/leavetype");
			rd.forward(request, response);
		}else
		{
			lt = new LeaveType();
			lt.setLeavetype(request.getParameter("leavetype"));
			lt.setLeaveid(Integer.parseInt(request.getParameter("updateid")));
			lt.setLimitdays(Float.parseFloat(request.getParameter("limitdays")));
			lt.setCreatedby(request.getSession().getAttribute("logid").toString());
			lts.updateType(lt);
			String update = "Update successfully!";
			request.setAttribute("insert",update);
			RequestDispatcher rd = request.getRequestDispatcher("/leavetype");
			rd.forward(request, response);
		}
		}catch(Exception e)
		{
			request.setAttribute("error", "Error ! Please check what you did!");
			RequestDispatcher rd = request.getRequestDispatcher("/leavetype");
			rd.forward(request, response);
		}
}
}
