package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LeaveFormSql;
import dao.LeaveTypeSql;
import model.LeaveForm;

/**
 * Servlet implementation class updatestaffleave
 */
@WebServlet("/updatestaffleave")
public class updatestaffleave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatestaffleave() {
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
		LeaveTypeSql lts = new LeaveTypeSql();
		String[] reason = request.getParameterValues("reason");
		String[] workdis = request.getParameterValues("workdis");
		String[] contact = request.getParameterValues("contact");
		StringBuilder rea,work,con;
		rea = new StringBuilder();
		work = new StringBuilder();
		con = new StringBuilder();
		for(String s:reason)
		{
			rea.append(s+" ");
		}
		for(String s:workdis)
		{
			work.append(s+" ");
		}
		for(String s:contact)
		{
			con.append(s+" ");
		}
		LeaveForm lf = new LeaveForm();
		
		try
		{
			lf.setStaffid(request.getSession().getAttribute("logid").toString());
			lf.setLeavetype(request.getParameter("type"));
			lf.setReason(rea.toString());
			lf.setContact(con.toString());
			lf.setWorkdissemination(work.toString());
			String s = request.getParameter("type");
			lf.setLeaveid(lts.getLeaveIdByType(s));
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			try{
				lf.setStartdate(df.format(df.parse(request.getParameter("startdate"))).toString());
				lf.setEnddate(df.format(df.parse(request.getParameter("enddate"))).toString());
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			lf.setFromshift(request.getParameter("time"));
			lf.setToshift(request.getParameter("time"));
			lf.setLeaveformid(Integer.parseInt(request.getParameter("leaveformid")));
			LeaveFormSql lfs = new LeaveFormSql();
			lfs.updateLeaveForm(lf);
			
			String success = "Update successfully!";
			request.setAttribute("success", success);
			RequestDispatcher rd = request.getRequestDispatcher("/manageleave");
			rd.forward(request, response);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			request.setAttribute("error",ex.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("leaveform.jsp");
			rd.forward(request, response);
		}
		
	}
}
