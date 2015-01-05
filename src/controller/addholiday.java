package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HolidaySql;
import model.Holiday;

/**
 * Servlet implementation class addholiday
 */
@WebServlet("/addholiday")
public class addholiday extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addholiday() {
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
			Holiday ho = new Holiday();
			try{
			ho.setHid(Integer.parseInt(request.getParameter("hid")));
			ho.setHdate(request.getParameter("hdate"));
			ho.setDescription(request.getParameter("description"));
			if("".equals(ho.getHid())||"".equals(ho.getHdate())||"".equals(ho.getDescription())){
				throw new Exception();
			}
			}catch(Exception e){
				request.setAttribute("error", "Error! Fields can not be null!");
				RequestDispatcher rd = request.getRequestDispatcher("/bridge");
				rd.forward(request, response);
			}
			ho.setCreatedby(request.getSession().getAttribute("logid").toString());
			ho.setUpdatedby(request.getSession().getAttribute("logid").toString());
			HolidaySql hs = new HolidaySql();
			
			try
			{
				hs.addHoliday(ho);
				String addsuc = "Add Holiday Successfully!";
				request.setAttribute("addsuccess", addsuc);
				RequestDispatcher rd = request.getRequestDispatcher("/manageholiday");
				rd.forward(request, response);
			}catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute("error", "Error! Please check your input!");
				RequestDispatcher rd = request.getRequestDispatcher("/bridge");
				rd.forward(request, response);
			}
	}

}
