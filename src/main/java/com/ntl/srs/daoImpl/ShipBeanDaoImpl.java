 package com.ntl.srs.daoImpl;

import java.util.ArrayList;

import javax.sql.DataSource;

import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.dao.ShipBeanDao;
import com.ntl.srs.utilImpl.DBUtilImpl;
import java.sql.*;

public class ShipBeanDaoImpl implements ShipBeanDao{

	DBUtilImpl dbut=new DBUtilImpl();
	PreparedStatement ps=null;
	ResultSet rs=null;
	DataSource ds=null;
	Connection con;//

	

	public ShipBeanDaoImpl() {
		super();
		this.con=DBUtilImpl.getDBConnection("jdbc");
		
	}

	public ShipBeanDaoImpl(DataSource datasource) {
		super();
		
		try {
			this.con=datasource.getConnection();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public String createShipBean(ShipBean shipBean)  {
		int add=0;
		try {
		//	 Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("insert into SRS_TBL_Ship values(?,?,?,?)");
		ps.setString(1, shipBean.getShipID());
		ps.setString(2, shipBean.getShipName());
		ps.setInt(3, shipBean.getSeatingCapacity());
		ps.setInt(4, shipBean.getReservationCapacity());
		 add=ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		//DBUtilImpl.closing(con, null, ps,null);
		if(add>0)
		return "success";
		else {
			return null;
		}
	}

	
	public int deleteShipBean(ArrayList<String> al) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean updateShipBean(ShipBean shipBean)    {
		// TODO Auto-generated method stub
		int modify=0;
			try {
			//	 Connection con=DBUtilImpl.getDBConnection("jdbc");
			ps=con.prepareStatement("update SRS_TBL_Ship set ShipName='"+shipBean.getShipName()+"', SeatingCapacity='"+shipBean.getSeatingCapacity()+"',ReservationCapacity='"+shipBean.getReservationCapacity()+"' where shipId='"+shipBean.getShipID()+"'");
			 modify=ps.executeUpdate();
			}catch(SQLException e) {
				System.out.println(e);
			}
			//DBUtilImpl.closing(con, null, ps,null);
			if(modify>0)
				return true;
			else {
				return false;
			}
		}

		
	

	
	public ShipBean findByID(String id)  {
		// TODO Auto-generated method stub
		try {
		//	 Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("select * from srs_tbl_ship where shipId='"+id+"'");
		rs=ps.executeQuery();
		while(rs.next())
		{
		ShipBean sbean=new ShipBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
		//DBUtilImpl.closing(con, null, ps,rs);
			return sbean;
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		//DBUtilImpl.closing(con, null, ps,rs);
		return null;
	}

	
	public ArrayList<ShipBean> findAll()  {
		ArrayList<ShipBean> ship=new ArrayList<ShipBean>();
		try {
		//	 Connection con=DBUtilImpl.getDBConnection("jdbc");
		ps=con.prepareStatement("select * from srs_tbl_ship ");
		rs=ps.executeQuery();
		while(rs.next())
		{
			ShipBean sp=new ShipBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
			ship.add(sp);
		}
		if(rs.first())
		{
			//DBUtilImpl.closing(con, null, ps,rs);
			return ship;
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		//DBUtilImpl.closing(con, null, ps,rs);
		return null;
	}

}
