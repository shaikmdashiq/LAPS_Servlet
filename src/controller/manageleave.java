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
 * Servlet implementation class manageleave
 */
@WebServlet("/manageleave")
public class manageleave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageleave() {
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
		LeaveFormSql lfs = new LeaveFormSql();	
        try
        {
        	ArrayList<LeaveForm> formlist = lfs.manageSelfLeave(request.getSession().getAttribute("logid").toString());
        	if(null!=formlist&&formlist.size()>0)
    		{
    			request.setAttribute("staffform",formlist);
    			RequestDispatcher rd= request.getRequestDispatcher("manageleave.jsp");
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
        	String error = "Error, please check what you did";
        	request.setAttribute("error",error);
        	RequestDispatcher rd= request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
        }
		

	}
}
