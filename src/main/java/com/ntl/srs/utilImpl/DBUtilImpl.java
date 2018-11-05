package com.ntl.srs.utilImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ntl.srs.util.DBUtil;


public class DBUtilImpl implements DBUtil{
	
	
	public static Connection getDBConnection(String driverType) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/project", "root","root");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
		
	}
	
	
	
	public static void closing( Connection conn,Statement stmt, PreparedStatement ps,ResultSet rs)
	{
		
		if (rs != null) {
	        try {
	            rs.close();
	        } catch (SQLException e) { System.out.println("something wrong with ResultSet");}
	    }
	    if (ps != null) {
	        try {
	            ps.close();
	        } catch (SQLException e) { System.out.println("PreparedStatement need to be closed");}
	    }
	    if (conn != null) {
	        try {
	            conn.close();
	        } catch (SQLException e) { System.out.println("close the connection ");}
	    }
	    if(stmt!=null) {
	    	try {
	    		stmt.close();
	    	}catch(SQLException e) {System.out.println("something wrong with the statement");}
	    }
	}
	
}
