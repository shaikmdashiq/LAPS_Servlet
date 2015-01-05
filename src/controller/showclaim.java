package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClaimSql;
import model.Claim;

/**
 * Servlet implementation class showclaim
 */
@WebServlet("/showclaim")
public class showclaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showclaim() {
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
		ClaimSql sss= new ClaimSql();
		String logid = request.getSession().getAttribute("logid").toString();
		ArrayList<Claim> list = sss.getAllClaim(logid);
		
		try
		{
			if(null!=list&&list.size()>0){
			request.setAttribute("claim",list);
			RequestDispatcher rd = request.getRequestDispatcher("showclaim.jsp");
			rd.forward(request, response);
			}else
			{
				String norecord = "No record !";
				request.setAttribute("norec",norecord);
				RequestDispatcher rd = request.getRequestDispatcher("showclaim.jsp");
				rd.forward(request, response);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
