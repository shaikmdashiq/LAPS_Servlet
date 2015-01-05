package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LeaveFormSql;
import model.LeaveForm;

/**
 * Servlet implementation class personalhistory
 */
@WebServlet("/personalhistory")
public class personalhistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public personalhistory() {
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
		LeaveFormSql lfs = new LeaveFormSql();
		ArrayList<LeaveForm> flist = lfs.getPersonalHistory(request.getSession().getAttribute("logid").toString());
		try{
		if(null!=flist&&flist.size()>0)
		{
			request.setAttribute("personalform",flist);
			RequestDispatcher rd= request.getRequestDispatcher("personalhistory.jsp");
			rd.forward(request, response);
		}else
		{
			String no = "No records!";
			request.setAttribute("record", no);
			RequestDispatcher rd= request.getRequestDispatcher("personalhistory.jsp");
			rd.forward(request, response);
		}
		}catch(Exception e){
			String error = "Something wrong...";
			request.setAttribute("error", error);
			RequestDispatcher rd= request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		}
	}

}
