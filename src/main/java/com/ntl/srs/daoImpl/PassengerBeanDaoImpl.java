package com.ntl.srs.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.ReservationBean;
import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.dao.PassengerBeanDao;
import com.ntl.srs.utilImpl.DBUtilImpl;

public class PassengerBeanDaoImpl implements PassengerBeanDao{

	Connection con;
	PreparedStatement ps=null;
	ResultSet rs=null;
	ReservationBeanDaoImpl rserve =new ReservationBeanDaoImpl();
	

	public PassengerBeanDaoImpl() {
		super();
		con=DBUtilImpl.getDBConnection("jdbc");
	}
	
	public PassengerBeanDaoImpl(DataSource datasource) {
		super();
		try {
			con=datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String createPassengerBean(PassengerBean passengerBean)  {
		// Connection con=DBUtilImpl.getDBConnection("jdbc");
		//System.out.println("pass "+passengerBean.getReservationID()+" and "+passengerBean.getName());
		ReservationBean rbn=new ReservationBean();
		try {
			rbn=rserve.findByID(passengerBean.getReservationID());
		
	//	System.out.println("rbn "+rbn.getScheduleID());
		passengerBean.setScheduleID(rbn.getScheduleID());
		
		ps=con.prepareStatement("insert into srs_tbl_passenger values(?,?,?,?,?)");
		ps.setString(1, passengerBean.getReservationID());
		ps.setString(2,passengerBean.getScheduleID());
		ps.setString(3, passengerBean.getName());
		ps.setInt(4, passengerBean.getAge());
		ps.setString(5, passengerBean.getGender());}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		int ans=0;
		try {
			ans = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DBUtilImpl.closing(con, null, ps,null);
		if(ans>0)
		{
			
			return "Success";
		}
		
		return null;
	}

	
	public int deletePassengerBean(ArrayList<String> al) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean updatePassengerBean(PassengerBean passengerBean)  {
		// TODO Auto-generated method stub
		return false;
	}

	
	public PassengerBean findByID(String id) {
		// Connection con=DBUtilImpl.getDBConnection("jdbc");
		try {
		ps=con.prepareStatement("select * from srs_tbl_passenger where reservationId='"+id+"'");
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			PassengerBean sp=new PassengerBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
			//System.out.println("Ishanya "+sp.getReservationID());
		//	DBUtilImpl.closing(con, null, ps,rs);
			return sp;
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		//DBUtilImpl.closing(con, null, ps,rs);
		return null;
	}

	
	public ArrayList<PassengerBean> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean allPass(String id) throws SQLException {
		 //Connection con=DBUtilImpl.getDBConnection("jdbc");
		
		ps=con.prepareStatement("delete from srs_tbl_passenger where reservationId='"+id+"'");
		int test=ps.executeUpdate();
		//DBUtilImpl.closing(con, null, ps,null);
		if(test>0)
		{
			return true;
		}
		return false;
	}
	
	public ArrayList<PassengerBean> findAllById(String id) 
	{
		 //Connection con=DBUtilImpl.getDBConnection("jdbc");
		ArrayList<PassengerBean> al=new ArrayList();
		try {
		ps=con.prepareStatement("select * from srs_tbl_passenger where reservationId='"+id+"'");
		rs=ps.executeQuery();
		while(rs.next())
		{
			PassengerBean sp=new PassengerBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
			al.add(sp);
		}}
		catch(SQLException e){
			System.out.println(e);
		}
		//DBUtilImpl.closing(con, null, ps,rs);
		
		if(al!=null)
		{
			return al;
		}
		
		return null;
	}

	public ArrayList<PassengerBean> findByShip(String shipId) 
	{
		 //Connection con=DBUtilImpl.getDBConnection("jdbc");
		ArrayList<PassengerBean> al=new ArrayList();
		try {
		ps=con.prepareStatement("select srs_tbl_passenger.reservationId,srs_tbl_passenger.scheduleId,srs_tbl_passenger.name,srs_tbl_passenger.age,srs_tbl_passenger.gender from srs_tbl_passenger inner join srs_tbl_schedule on srs_tbl_passenger.scheduleId=srs_tbl_schedule.scheduleId where srs_tbl_schedule.shipId='"+shipId+"'");
		rs=ps.executeQuery();
		while(rs.next())
		{
			PassengerBean sp=new PassengerBean(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
			al.add(sp);
		}
		}
		catch(SQLException sq)
		{
			System.out.println(sq);
		}
		//DBUtilImpl.closing(con, null, ps,rs);
		if(al!=null)
		{
			return al;
		}
		
		return null;
	}
	
}
