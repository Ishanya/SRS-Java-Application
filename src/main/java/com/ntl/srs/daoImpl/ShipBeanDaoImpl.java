 package com.ntl.srs.daoImpl;

import java.util.ArrayList;

import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.dao.ShipBeanDao;
import com.ntl.srs.utilImpl.DBUtilImpl;
import java.sql.*;

public class ShipBeanDaoImpl implements ShipBeanDao{

	Connection con=DBUtilImpl.getDBConnection("jdbc");
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	
	public String createShipBean(ShipBean shipBean) throws SQLException {
		
		ps=con.prepareStatement("insert into SRS_TBL_Ship values(?,?,?,?)");
		ps.setString(1, shipBean.getShipID());
		ps.setString(2, shipBean.getShipName());
		ps.setInt(3, shipBean.getSeatingCapacity());
		ps.setInt(4, shipBean.getReservationCapacity());
		int add=ps.executeUpdate();
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

	public boolean updateShipBean(ShipBean shipBean) throws SQLException   {
		// TODO Auto-generated method stub
			
			ps=con.prepareStatement("update SRS_TBL_Ship set ShipName='"+shipBean.getShipName()+"', SeatingCapacity='"+shipBean.getSeatingCapacity()+"',ReservationCapacity='"+shipBean.getReservationCapacity()+"' where shipId='"+shipBean.getShipID()+"'");
			int modify=ps.executeUpdate();
			if(modify>0)
				return true;
			else {
				return false;
			}
		}

		
	

	
	public ShipBean findByID(String id) throws SQLException {
		// TODO Auto-generated method stub
		
		ps=con.prepareStatement("select * from srs_tbl_ship where shipId='"+id+"'");
		rs=ps.executeQuery();
		while(rs.next())
		{
		ShipBean sbean=new ShipBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
		
			return sbean;
		}
		return null;
	}

	
	public ArrayList<ShipBean> findAll() throws SQLException {
		ArrayList<ShipBean> ship=new ArrayList<ShipBean>();
		ps=con.prepareStatement("select * from srs_tbl_ship ");
		rs=ps.executeQuery();
		while(rs.next())
		{
			ShipBean sp=new ShipBean(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
			ship.add(sp);
		}
		if(rs.first())
		{
			return ship;
		}
		return null;
	}

}
