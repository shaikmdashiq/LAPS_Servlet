package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Holiday;
import model.Staff;

public class HolidaySql {
	public Connection getConnection()throws Exception
	{
		Connection conn= null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/laps","root","password");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	public ArrayList<Holiday> getAllHoliday()
	{
		String sql = "select * from Holiday;";
		ArrayList<Holiday> ho =null; 
				ho = new ArrayList<Holiday>();
		
		try
		{
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				Holiday h = new Holiday();
				h.setHid(rs.getInt("hid"));
				h.setHdate(rs.getString("hdate"));
				h.setDescription(rs.getString("description"));	
				ho.add(h);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return ho;
	}
	
	//add new holiday
	public void addHoliday(Holiday ho)
	{
		String sql = "insert into holiday (hid,hdate,description,createdby,updatedby) values"
				+"("+ho.getHid()+",'"+ho.getHdate()+"','"+ho.getDescription()+"','"+ho.getCreatedby()+"','"
				+ho.getUpdatedby()+"');";
		Connection conn = null;
		try
		{
			conn = getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing insert: " + sql);
			int res = st.executeUpdate(sql);
			st.close();
		}catch (Exception e) {
			String error = "Error on insert new holiday: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	//delete holiday
	public void deleteHoliday(int hid)
	{
		String sql = "delete from holiday where hid="+hid+";";
		Connection conn = null;
		try
		{
			conn= getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing update: " + sql);
			int rows = st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			String error = "Error on delete holiday: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
