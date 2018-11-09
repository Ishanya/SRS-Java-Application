package com.ntl.srs.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.dao.ScheduleBeanDao;
import com.ntl.srs.utilImpl.DBUtilImpl;

public class ScheduleBeanDaoImpl implements ScheduleBeanDao{

	
	Connection con;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	
	
	public ScheduleBeanDaoImpl() {
		super();
		con=DBUtilImpl.getDBConnection("jdbc");
	}
	
	
	public ScheduleBeanDaoImpl(DataSource datasource) {
		super();
		try {
			con=datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String createScheduleBean(ScheduleBean scheduleBean) {
		// TODO Auto-generated method stub
		int add=0;
		try {
			// Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("insert into SRS_TBL_Schedule values(?,?,?,?)");
		ps.setString(1, scheduleBean.getScheduleID());
		ps.setString(2, scheduleBean.getShipID());
		ps.setString(3, scheduleBean.getRouteID());
		ps.setDate(4, Date.valueOf(scheduleBean.getStartDate()));
		 add=ps.executeUpdate();
		}catch(SQLException sq)
		{
			sq.printStackTrace();
			}
		
		//DBUtilImpl.closing(con, null, ps,null);
		if(add>0)
		return "success";
		else {
			return null;
		}
		
	
	}

	
	public int deleteScheduleBean(ArrayList<String> al) {
		int flag=1;
		for(String i:al) {
		try {
			// Connection con=DBUtilImpl.getDBConnection("jdbc");
				ps=con.prepareStatement("delete from srs_tbl_schedule where scheduleId='"+i+"'");
				int del=ps.executeUpdate();
				if(del==0)
				{
					flag=0;
					return 0;
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			//DBUtilImpl.closing(con, null, ps,null);
		}
		
		}
		return flag;
		
	}

	
	public boolean updateScheduleBean(ScheduleBean scheduleBean)  {
		// TODO Auto-generated method stub
		int modify=0;
		try {
			 //Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("update srs_tbl_schedule set ShipId='"+scheduleBean.getShipID()+"', RouteId='"+scheduleBean.getRouteID()+"',startDate='"+Date.valueOf(scheduleBean.getStartDate())+"' where ScheduleId='"+scheduleBean.getScheduleID()+"'");
		 modify=ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e);
		}
		//DBUtilImpl.closing(con, null, ps,null);
		if(modify>0)
		{
			return true;
		}
		return false;
	}

	
	public ScheduleBean findByID(String id)  {

		
		try {
			// Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("select * from srs_tbl_schedule where scheduleId='"+id+"'");
		rs=ps.executeQuery();
		while(rs.next()) {
			Date today = rs.getDate(4);
			Instant instant = Instant.ofEpochMilli(today.getTime());
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); 
			LocalDate localDate = localDateTime.toLocalDate();
		ScheduleBean sbean=new ScheduleBean(rs.getString(1),rs.getString(3),rs.getString(2),localDate);
		//DBUtilImpl.closing(con, null, ps,rs);
			return sbean;
		}}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	
	public ArrayList<ScheduleBean> findAll()  {
		ArrayList<ScheduleBean> sbean=new ArrayList<ScheduleBean>();
		try {
			// Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("select * from srs_tbl_schedule ");
		rs=ps.executeQuery();
		while(rs.next())
		{
			
			Date today = rs.getDate(4);
			Instant instant = Instant.ofEpochMilli(today.getTime());
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()); 
			LocalDate localDate = localDateTime.toLocalDate();

			
			ScheduleBean sche=new ScheduleBean(rs.getString(1),rs.getString(3),rs.getString(2),localDate);
			sbean.add(sche);
		}
		if(rs.first())
		{
			//DBUtilImpl.closing(con, null, ps,rs);
			return sbean;
		}
		}catch(SQLException e) {
			System.out.println(e);
		}
		//DBUtilImpl.closing(con, null, ps,rs);
		return null;
	}

}
