package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LeaveForm;
import model.LeaveType;
import dao.LeaveFormSql;
import dao.LeaveTypeSql;

/**
 * Servlet implementation class staffdeleteform
 */
@WebServlet("/staffdeleteform")
public class staffdeleteform extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public staffdeleteform() {
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
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = Integer.parseInt(request.getParameter("leaveformid"));
		String lstatus = request.getParameter("lstatus");
		LeaveFormSql lfs = new LeaveFormSql();
		LeaveForm lf= new LeaveForm();
		lf.setLeaveformid(id);
		lf.setLstatus(lstatus);
		try
		{
			lfs.deleteLeaveForm(lf);
			RequestDispatcher rd = request.getRequestDispatcher("/manageleave");
			rd.forward(request, response);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			request.setAttribute("error", "An error happened,Please check what you did!");
			RequestDispatcher rd = request.getRequestDispatcher("/bridge");
			rd.forward(request, response);
		}
	}

}
