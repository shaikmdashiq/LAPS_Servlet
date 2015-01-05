package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Role;
import model.Staff;

public class RoleSql {
	
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
	
	public String getTitleById(int roleid)
	{
		String title = "";
		String sql = "select title from role where roleid="+roleid+";";
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next())
			{
				Role r = new Role();
				r.setTitle(rs.getString("title").toString());
				title = r.getTitle();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return title;
	}
	
	public Integer getIdByTitle(String title)
	{
		int roleid =-1;
		String sql = "select roleid from role where title='"+title+"';";
		try
		{
			Connection conn = getConnection();
			Statement sm = conn.createStatement();
			ResultSet rs = sm.executeQuery(sql);
			if(rs.next())
			{
				roleid=rs.getInt("roleid");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return roleid;
	}
	
	
}


