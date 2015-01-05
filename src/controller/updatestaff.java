package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RoleSql;
import dao.StaffSql;
import model.Staff;

/**
 * Servlet implementation class updatestaff
 */
@WebServlet("/updatestaff")
public class updatestaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatestaff() {
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
		Staff staff = new Staff();
		RoleSql ro = new RoleSql();
		StaffSql ss = new StaffSql();
		try{
		staff.setStaffid(request.getParameter("staffid"));
		staff.setRoleid(ro.getIdByTitle((String)request.getParameter("type")));
		staff.setPwd(request.getParameter("password"));
		staff.setUpdatedby(request.getSession().getAttribute("logid").toString());
		if("staff".equals((String)request.getParameter("type"))&&null!=request.getParameter("managerid"))
		{
			staff.setManagerid(request.getParameter("managerid"));
		}else if("staff".equals((String)request.getParameter("type")))
		{
			if(null==request.getParameter("managerid")||"".equals(request.getParameter("managerid")))
			throw new Exception();
		}else
		{
			staff.setManagerid(null);
		}
		ss.updateStaff(staff);
		String updatesuc = "Update Successfully!";
		request.setAttribute("updatesuc", updatesuc);
		RequestDispatcher rd= request.getRequestDispatcher("/managestaff");
		rd.forward(request, response);
		}catch(Exception e)
		{
			request.setAttribute("updatefai", "Update Fail! Must assign a manager to staff!");
			RequestDispatcher rd = request.getRequestDispatcher("/managestaff");
			rd.forward(request, response);
		}
	}

}
