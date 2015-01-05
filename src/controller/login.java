package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Staff;
import dao.StaffSql;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 String id = request.getParameter("id");
		 String pwd = request.getParameter("pwd");
		 StaffSql ss = new StaffSql();
		 String path = ss.getPath(id, pwd);
		 Staff staff = ss.getStaffById(id);
		 HttpSession hs = request.getSession();
		 hs.setAttribute("logid",id);
		 
		 if("login.jsp"!=path)
		 {
			 String error="";
			 request.setAttribute("error",error);
			 String firstname= staff.getFirstname();
			 hs.setAttribute("fname", firstname);
			 hs.setAttribute("path",path);
			 RequestDispatcher rd = request.getRequestDispatcher("/loginmenu.jsp");
			 rd.forward(request, response);
		 }else
		 {
			  
			 String error = "Invalid ID/Password";
			 request.setAttribute("error",error);
			 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			 rd.forward(request, response);
		 }
		 
	}

}
