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
 * Servlet implementation class getleaveform
 */
@WebServlet("/getleaveform")
public class getleaveform extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getleaveform() {
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
		LeaveForm lf = new LeaveForm();
		LeaveFormSql lfs =new LeaveFormSql();
		LeaveTypeSql lts = new LeaveTypeSql();
		lf.setLeaveid(lts.getLeaveIdByType((String)request.getParameter("leavetype")));
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		try{
		lf.setStartdate(df.format(df.parse(request.getParameter("startdate"))).toString());
		lf.setEnddate(df.format(df.parse(request.getParameter("enddate"))).toString());
		if(null==lf.getEnddate()){
			throw new Exception("Check your end date!");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		lf.setFromshift(request.getParameter("fromshift"));
		lf.setToshift(request.getParameter("toshift"));
		lf.setStaffid((String)request.getSession().getAttribute("logid"));
		lf.setLeavetype((String)request.getParameter("leavetype"));
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
		lf.setContact(con.toString());
		lf.setReason(rea.toString());
		lf.setWorkdissemination(work.toString());
		try {
			lfs.insertLeaveForm(lf);
			String add = "Apply Successfully!";
			request.setAttribute("formsuc",add);
			RequestDispatcher rd = request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		} catch (Exception e) {
			String error = "Apply Failed ! Check your input !";
			request.setAttribute("formerror",e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		}
	}

}
