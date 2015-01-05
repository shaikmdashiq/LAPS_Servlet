package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Claim;
import model.Staff;

public class ClaimSql {
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
	
	public void insertClaim(String staffid)
	{
		String sql = "insert into Claim (staffid) values('"+staffid+"');";
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
			String error = "Error on insert new Claim: " + e;
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, error);
			
		} finally {
			try {
				conn.rollback();
				conn.close();
			} catch (Exception e) {
			}
		}
	}
	
	public ArrayList<Claim> getAllClaim(String logid)
	{
		String sql = "select * from Claim where staffid in (select staffid from Staff where managerid=\""+logid+"\")";
		ArrayList<Claim> claim =null; 
				claim = new ArrayList<Claim>();
		StaffSql ss = new StaffSql();
		
		try
		{
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				Claim c = new Claim();
				c.setId(rs.getInt("id"));
				c.setStaffid(rs.getString("staffid"));
				c.setFirstname(ss.getNameById(rs.getString("staffid")));
				claim.add(c);
			}
			return claim;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return claim;
	}
	
	public void deleteClaim(int id)
	{
		String sql = "delete from Claim where id="+id+";";
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
			String error = "Error on delete Claim: " + e;
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
