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

import com.ntl.srs.bean.PaymentBean;
import com.ntl.srs.bean.ReservationBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.dao.ReservationBeanDao;
import com.ntl.srs.utilImpl.DBUtilImpl;

public class ReservationBeanDaoImpl implements ReservationBeanDao{

	Connection con=DBUtilImpl.getDBConnection("jdbc");
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	
	
	public String createReservationBean(ReservationBean reservationBean) throws SQLException {
		
		ps=con.prepareStatement("insert into srs_tbl_reservation values(?,?,?,?,?,?,?,?)");
		ps.setNString(1, reservationBean.getReservationID());
		ps.setString(2, reservationBean.getScheduleID());
		ps.setString(3, reservationBean.getUserID());
		ps.setDate(4, Date.valueOf(reservationBean.getBookingDate()));
		ps.setDate(5, Date.valueOf(reservationBean.getJourneyDate()));
		ps.setInt(6, reservationBean.getNoOfSeats());
		ps.setDouble(7, reservationBean.getTotalFare());
		ps.setString(8, reservationBean.getBookingStatus());
		
		int show=ps.executeUpdate();
		
		
		
		
		if(show>0)
		{
			return reservationBean.getBookingStatus();
		}
		return null;
	}

	
	public int deleteReservationBean(ArrayList<String> al) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean updateReservationBean(ReservationBean reservationBean) {
		System.out.println("in changing");
		try {
			String stat="confirm";
		ps=con.prepareStatement("update SRS_TBL_reservation set BookingStatus='"+stat+"' where reservationId='"+reservationBean.getReservationID()+"'");
		
		int state=ps.executeUpdate();
			if(state>0)
			{
				return true;
			}
			else {
			return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	
	public ReservationBean findByID(String id) throws SQLException {
		ps=con.prepareStatement("select * from srs_tbl_reservation where ReservationId='"+id+"'");
		rs=ps.executeQuery();
		while(rs.next())
		{
			Date today = rs.getDate(4);
			Instant instant = Instant.ofEpochMilli(today.getTime()); 
			LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			LocalDate localDate4 = localDateTime.toLocalDate();

			Date todayy = rs.getDate(5);
			Instant instantt = Instant.ofEpochMilli(todayy.getTime()); 
			LocalDateTime localDateTimee = LocalDateTime.ofInstant(instantt, ZoneId.systemDefault());
			LocalDate localDate5 = localDateTimee.toLocalDate();
		
		ReservationBean rebean=new ReservationBean(rs.getString(1),rs.getString(2),rs.getString(3),localDate4,localDate5,rs.getInt(6),rs.getDouble(7),rs.getString(8));
		
			return rebean;
		}
		return null;
	}

	
	public ArrayList<ReservationBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<ScheduleBean> ScheduletoRoute(String src,String dest,LocalDate start) throws SQLException
	{
		int flag=0;
		ArrayList<ScheduleBean> sch=new ArrayList<ScheduleBean>();
		ScheduleBean sb=null;
		ps=con.prepareStatement("select sch.ScheduleId,sch.ShipId,sch.RouteId from srs_tbl_schedule sch inner join srs_tbl_route rot on sch.routeId=rot.routeId where rot.source='"+src+"' and rot.Destination='"+dest+"' and sch.startDate='"+Date.valueOf(start)+"'");
		rs=ps.executeQuery();
		
		while(rs.next())
		{
		 sb=new ScheduleBean(rs.getString(1),rs.getString(3),rs.getString(2));
		sch.add(sb);
		}
		if(rs.first())
		{
			flag=1;
		}
		if(flag==1)
		{
			return sch;
		}
		return null;
	}

}
