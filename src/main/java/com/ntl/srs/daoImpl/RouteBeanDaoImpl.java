package com.ntl.srs.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.dao.RouteBeanDao;
import com.ntl.srs.utilImpl.DBUtilImpl;

public class RouteBeanDaoImpl implements RouteBeanDao{

	Connection con;
	PreparedStatement ps=null,pss=null;
	ResultSet rs=null;
	
	
	public RouteBeanDaoImpl() {
		super();
		con=DBUtilImpl.getDBConnection("jdbc");
	}

	
	public RouteBeanDaoImpl(DataSource datasource) {
		super();
	try {
		con=datasource.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}


	public String createRouteBean(RouteBean routeBean)  {
			int add=0;
		try {
			// Connection con=DBUtilImpl.getDBConnection("jdbc");
			ps=con.prepareStatement("insert into SRS_TBL_Route values(?,?,?,?,?)");
			ps.setString(1, routeBean.getRouteID());
			ps.setString(2, routeBean.getSource());
			ps.setString(3, routeBean.getDestination());
			ps.setString(4,routeBean.getTravelDuration());
			ps.setInt(5, routeBean.getFare());
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

	
	public int deleteRouteBean(ArrayList<String> al)  {
		int flag=1;
		for(String i:al) {
		try {
			 //Connection con=DBUtilImpl.getDBConnection("jdbc");
			pss=con.prepareStatement("delete from srs_tbl_schedule where routeId=?");
			pss.setString(1, i);
			int d=pss.executeUpdate();
			
				ps=con.prepareStatement("delete from srs_tbl_route where routeId=?");
				ps.setString(1, i);
				int del=ps.executeUpdate();
				if(del==0)
				{
					//System.out.println("in del");
					flag=0;
					return 0;
				}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			//DBUtilImpl.closing(con, null, ps,null);
		//	DBUtilImpl.closing(con, null, pss,null);
		}
		
		}
	//	System.out.println(flag);
		return flag;
		
	}

	
	public boolean updateRouteBean(RouteBean routeBean) {
		int modify=0;
		try {
			// Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("update srs_tbl_route set source='"+routeBean.getSource()+"', Destination='"+routeBean.getDestination()+"',travelDuration='"+routeBean.getTravelDuration()+"',fare='"+routeBean.getFare()+"' where RouteId='"+routeBean.getRouteID()+"'");
		 modify=ps.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println(e);
		}
		//DBUtilImpl.closing(con, null, ps,null);
		if(modify>0)
		{
			return true;
		}
		return false;
	}

	
	public RouteBean findByID(String id)  {
		try {
			// Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("select * from srs_tbl_route where routeId='"+id+"'");
		rs=ps.executeQuery();
		while(rs.next())
		{
		RouteBean rbean=new RouteBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
	//	DBUtilImpl.closing(con, null, ps,rs);
			return rbean;
		}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		//DBUtilImpl.closing(con, null, ps,rs);
		return null;
	}

	
	public ArrayList<RouteBean> findAll()  {
		ArrayList<RouteBean> route=new ArrayList<RouteBean>();
		try {
			// Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("select * from srs_tbl_route ");
		rs=ps.executeQuery();
		while(rs.next())
		{
			RouteBean sp=new RouteBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
			route.add(sp);
		}
		if(rs.first())
		{
			//DBUtilImpl.closing(con, null, ps,rs);
			return route;
		}}
		catch(SQLException e) {
			System.out.println(e);
		}
		//DBUtilImpl.closing(con, null, ps,rs);
		return null;
	}

}
