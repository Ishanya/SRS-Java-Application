package com.ntl.srs.serviceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.ReservationBean;
import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.daoImpl.PassengerBeanDaoImpl;
import com.ntl.srs.daoImpl.ReservationBeanDaoImpl;
import com.ntl.srs.service.Customer;

public class CustomerImpl implements Customer{
ReservationBeanDaoImpl reservebean=new ReservationBeanDaoImpl();
ArrayList<ScheduleBean>	albean=new ArrayList<ScheduleBean>();
AdministratorImpl al=new AdministratorImpl();
PassengerBeanDaoImpl passbean=new PassengerBeanDaoImpl();
static CustomerImpl cussto=null;

	
	/**
 * 
 */
public CustomerImpl() {
	super();
}


public CustomerImpl(ReservationBeanDaoImpl rimpl) {
	super();
	reservebean=rimpl;
}

public CustomerImpl(PassengerBeanDaoImpl pimpl) {
	super();
	passbean=pimpl;
}

public CustomerImpl(ReservationBeanDaoImpl rimpl,PassengerBeanDaoImpl pimpl) {
	super();
	reservebean=rimpl;
	passbean=pimpl;
}

public CustomerImpl(ReservationBeanDaoImpl rimpl,CustomerImpl cimpl) {
	super();
	reservebean=rimpl;
	cussto=new CustomerImpl();
	cussto=cimpl;
}



	public ArrayList<ScheduleBean> viewScheduleByRoute(String source, String destination, LocalDate date) throws SQLException {
		
		albean=reservebean.ScheduletoRoute(source, destination, date);
		if(albean!=null)
		{
			return albean;
		}
		return null;
	}

	
	public String reserveTicket(ReservationBean reservationBean, ArrayList<PassengerBean> passengerBean) throws SQLException {
//		ScheduleBean sc=new ScheduleBean();
//		
//		
//		sc=al.viewByScheduleId(reservationBean.getScheduleID());
//		
//		RouteBean rb=new RouteBean();
//		rb=al.viewByRouteId(sc.getRouteID());
		
//		Random rand = new Random();
//		reservationBean.setReservationID(rb.getSource().substring(0, 2)+rb.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
//		System.out.println("please NOTE UR UNIQUE ID: "+reservationBean.getReservationID());
		
		CustomerImpl cus=new CustomerImpl();	
		String rRID=reservebean.createReservationBean(reservationBean);
		String pPID=null;
		//System.out.println("in reserve:" + passengerBean.size());
		if(rRID.equalsIgnoreCase("confirm")) {
		pPID=cus.addingPassengers(passengerBean);		
	//	pPID=cussto.addingPassengers(passengerBean);	
		}
		if(rRID!=null && pPID!=null)
		{
			return rRID;
		}
		else{
			return "pending";
		}
		
		
		
	}

	
	public boolean cancelTicket(String reservationId) throws SQLException {
		if(passbean.allPass(reservationId))
		{
			return true;
		}
		return false;
	}

	
	public Map<ReservationBean, PassengerBean> viewTicket(String reservationId) throws SQLException {
		ArrayList<PassengerBean> al=new ArrayList<PassengerBean>();
		al=passbean.findAllById(reservationId);
		ReservationBean res=new ReservationBean();
		res =reservebean.findByID(reservationId);
		//System.out.println(al.size());
		Map<ReservationBean,PassengerBean> map = new HashMap<ReservationBean,PassengerBean>();
		for(PassengerBean m:al)
		{
			map.put(res, m);
			
		}
//		PassengerBean passion =map.get(res);
//		System.out.println(passion.getName());
		if(map!=null)
		{
			return map;
		}
		return null;
	}

	
	public Map<ReservationBean, PassengerBean> printTicket(String reservationId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean changeBookingStatus(ReservationBean reservationBean)
	{
		if(reservebean.updateReservationBean(reservationBean)) {
			return true;
		}
		return false;
	}
	
	public String addingPassengers(ArrayList<PassengerBean> passengerBean) throws SQLException{
		String pPID=null;
		//System.out.println(passengerBean.size());
		for(PassengerBean pass:passengerBean)
		{
			//System.out.println("name:" +pass.getName()+" "+pass.getReservationID());
			 pPID=passbean.createPassengerBean(pass);
			if(pPID==null)
			{
				return pPID;
				
			}
		}
		return pPID;
	}

}
 