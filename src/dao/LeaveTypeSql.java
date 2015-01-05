package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.LeaveType;

public class LeaveTypeSql {
	
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
	
	public String getLeaveTypeById(int leaveid)
	{
		String leavetype = "";
		String sql = "select leavetype from leavetype where leaveid="+leaveid;
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next())
			{
				leavetype = rs.getString("leavetype");
			}
			
			sm.close();
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return leavetype;
	}
	
	public ArrayList<LeaveType> getAllLeaveType()
	{
		String sql = "select * from leavetype;";
		ArrayList<LeaveType> typelist = new ArrayList<LeaveType>();
		try
		{
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				LeaveType lt = new LeaveType();
				lt.setLeaveid(rs.getInt("leaveid"));
				lt.setLeavetype(rs.getString("leavetype"));
				lt.setCreatedby(rs.getString("createdby"));
				lt.setLimitdays(rs.getFloat("limitdays"));
				typelist.add(lt);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return typelist;
	}
	
	public void insertType(LeaveType lt)
	{
		String sql = "insert into leavetype (leaveid,leavetype,limitdays,createdby) values"	
				+"("+lt.getLeaveid()+",\""+lt.getLeavetype()+"\","+lt.getLimitdays()+",\""+lt.getCreatedby()+"\");";
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
			String error = "Error on insert new type: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	public void updateType(LeaveType lt)
	{
		String sql = "update leavetype set leavetype=\""+lt.getLeavetype()+"\",limitdays="+lt.getLimitdays()
				+",createdby=\""+lt.getCreatedby()+"\",updatedby='"+lt.getCreatedby()+"'"
				+" where leaveid="+lt.getLeaveid()+";";
		Connection conn = null;
		try
		{
			conn= getConnection();
			Statement st = conn.createStatement();
			Logger.getLogger(getClass().getName()).log(Level.INFO,
					"Executing update: " + sql);
			int res = st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			String error = "Error on update type: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
		
	}
	
	public void deleteType(LeaveType lt)
	{
		String sql = "delete from leavetype where leaveid="+lt.getLeaveid()+";";
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
			String error = "Error on delete type: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
		
	}
	
	public Integer getLeaveIdByType(String leavetype)
	{
		int id=-1;
		String sql = "select leaveid from leavetype where leavetype='"+leavetype+"';";
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next())
			{
				id = rs.getInt("leaveid");
			}
			
			sm.close();
			conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}
}
