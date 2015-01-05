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
 * Servlet implementation class addstaff
 */
@WebServlet("/addstaff")
public class addstaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addstaff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updateid = request.getSession().getAttribute("logid").toString();
		Staff s = new Staff();
		RoleSql rr = new RoleSql();
		try
		{
			s.setUpdatedby(updateid);
			s.setCreatedby(updateid);
			s.setStaffid(request.getParameter("staffid"));
			s.setRoleid(rr.getIdByTitle((String)request.getParameter("title")));
			s.setFirstname(request.getParameter("firstname"));
			s.setLastname(request.getParameter("lastname"));
			s.setGender(request.getParameter("gender"));
			s.setPwd(request.getParameter("password"));
			s.setEmail(request.getParameter("email"));
			s.setPhoneno(Integer.parseInt(request.getParameter("phoneno")));
			String[] add = request.getParameterValues("address");
			StringBuilder sb = new StringBuilder();
			for(String as: add)
			{
				sb.append(as+" ");
			}
			s.setAddress(sb.toString());
		if("staff".equals(request.getParameter("title")))
		{
			s.setManagerid(request.getParameter("managerid"));
			if(null==s.getManagerid()||s.getStaffid().length()!=4){
				throw new Exception();
			}
		}else
		{
			s.setManagerid(null);
		}
			StaffSql ss = new StaffSql();
			ss.insertStaff(s);
			String suc = "Add Successfully !";
			request.setAttribute("suc",suc);
		}catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", "Add Fail ! Please check your input!");
			RequestDispatcher rd = request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		}
		
		RequestDispatcher rd= request.getRequestDispatcher("/managestaff");
		rd.forward(request, response);
	}

}
