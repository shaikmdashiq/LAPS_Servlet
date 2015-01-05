package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClaimSql;

/**
 * Servlet implementation class addclaim
 */
@WebServlet("/addclaim")
public class addclaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addclaim() {
        super();
       
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
		String staffid = request.getSession().getAttribute("logid").toString();
		ClaimSql  cs = new ClaimSql();
		try
		{
			cs.insertClaim(staffid);
			String claim = "Claim Successfully !";
			request.setAttribute("claimsuc",claim);
			RequestDispatcher rd = request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
			String claim = "Claim Failed!";
			request.setAttribute("claimfai",claim);
			RequestDispatcher rd = request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
			
		}
		
	}

}
