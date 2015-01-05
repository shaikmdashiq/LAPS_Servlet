package controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LeaveFormSql;

/**
 * Servlet implementation class approveorreject
 */
@WebServlet("/approveorreject")
public class approveorreject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public approveorreject() {
        super();
        // TODO Auto-generated constructor stub
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
		String comm = request.getParameter("btn");	
		String[] comment = request.getParameterValues("comment");
		StringBuilder sb = new StringBuilder();
		for(String s:comment)
		{
			sb.append(s+" ");
		}
		try{
		int id =Integer.parseInt(request.getParameter("id"));
		LeaveFormSql lf = new LeaveFormSql();
		if("Approve".equals(comm))
		{
			String app = comm+"d";
			try{
			lf.updateStatus(app, sb.toString(), id);
			String approve = "Approved successfully";
			request.setAttribute("result",approve);
			RequestDispatcher rs = request.getRequestDispatcher("/approveleave");
			rs.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else
		{
			String rej = comm+"ed";
			String reject = "Rejected successfully";
			request.setAttribute("result",reject);
			lf.updateStatus(rej, sb.toString(), id);
			RequestDispatcher rs = request.getRequestDispatcher("/approveleave");
			rs.forward(request, response);
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("error", "An error happened,Please check what you did!");
			RequestDispatcher rd = request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		}
	}

}
