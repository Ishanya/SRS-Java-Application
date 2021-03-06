package com.ntl.srs.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.daoImpl.PassengerBeanDaoImpl;
import com.ntl.srs.daoImpl.RouteBeanDaoImpl;
import com.ntl.srs.daoImpl.ScheduleBeanDaoImpl;
import com.ntl.srs.daoImpl.ShipBeanDaoImpl;
import com.ntl.srs.service.Administrator;

import Client.Shiping;

public class AdministratorImpl implements Administrator{

	ShipBeanDaoImpl Shipsbean=new ShipBeanDaoImpl();
	ScheduleBeanDaoImpl Schedulebean=new ScheduleBeanDaoImpl();
	RouteBeanDaoImpl rbean=new RouteBeanDaoImpl();
	PassengerBeanDaoImpl passenger=new PassengerBeanDaoImpl();
	
	
	static Logger loggr=Logger.getLogger(AdministratorImpl.class);
	
	
	
	/**
	 * 
	 */
	public AdministratorImpl() {
		super();
	}
	
	public AdministratorImpl(PassengerBeanDaoImpl passimpl) {
		super();
		passenger=passimpl;
		
	}
	
	public AdministratorImpl(ShipBeanDaoImpl shipimpl) {
		super();
		Shipsbean=shipimpl;
		
	}

	public AdministratorImpl(RouteBeanDaoImpl roueimpl) {
		super();
		rbean=roueimpl;
		
	}

	public AdministratorImpl(ScheduleBeanDaoImpl schimpl) {
		super();
		Schedulebean=schimpl;
		
	}

	public String addShip(ShipBean shipbean) throws SQLException {
		
//		Random rand = new Random();
//		shipbean.setShipID(shipbean.getShipName().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
//		System.out.println("please NOTE UR UNIQUE ID: "+shipbean.getShipID());
//	
		loggr.info("Ship adding");
		String status=Shipsbean.createShipBean(shipbean);
		
		if(status!=null)
		return "success";
		
		return null;
	}

	
	public boolean modifyShip(ShipBean Shipbean) throws SQLException {
		// TODO Auto-generated method stub
		loggr.info("Ship Modifying");
		if(Shipsbean.updateShipBean(Shipbean))
		{
			return true;
		}
		
		return false;
	}

	
	public String addSchedule(ScheduleBean schedulebean) throws SQLException {
		// TODO Auto-generated method stub
		
//		AdministratorImpl ad=new AdministratorImpl();
//		Random rand = new Random();
//		RouteBean rot=ad.viewByRouteId(schedulebean.getRouteID());
//		schedulebean.setScheduleID(rot.getSource().substring(0, 2)+rot.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
//		System.out.println("please NOTE UR UNIQUE ID: "+schedulebean.getScheduleID());
//	
	
		loggr.info("Schedule adding");
		String status=Schedulebean.createScheduleBean(schedulebean);
		
		return status;
	}

	
	public boolean modifySchedule(ScheduleBean schedulebean) throws SQLException {
		// TODO Auto-generated method stub
		loggr.info("Schedule Modification");
		if(Schedulebean.updateScheduleBean(schedulebean))
		{
			return true;
		}
		return false;
	}

	
	public int removeSchedule(ArrayList<String> scheduleid) {
		// TODO Auto-generated method stub
		loggr.info("Schedule removing");
		int result=Schedulebean.deleteScheduleBean(scheduleid);
		
		return result;
	}

	
	public String addRoute(RouteBean routebean) throws SQLException {

//		Random rand = new Random();
//		routebean.setRouteID(routebean.getSource().substring(0, 2)+routebean.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
//		System.out.println("please NOTE UR UNIQUE ID: "+routebean.getRouteID());
//		
		
		loggr.info("Route adding");
		String status=rbean.createRouteBean(routebean);
		
		return status;
	}

	
	public boolean modifyRoute(RouteBean routebean) throws SQLException {
		loggr.info("Route Modification");
		if(rbean.updateRouteBean(routebean))
		{
			return true;
		}
		return false;
	}

	
	public int removeRoute(String routeid) throws SQLException {
		loggr.info("Route removing");
		
		ArrayList<String> al=new ArrayList<String>();
		String z[]=routeid.split(" ");
		for(String i:z)
		{
			al.add(i);
		}
int result=rbean.deleteRouteBean(al);
		//System.out.println(result);
		return result;
	}

	
	public ShipBean viewByShipId(String ShipId) throws SQLException {
		loggr.info("view the ship details by id");
		ShipBean sp=Shipsbean.findByID(ShipId);
		if(sp!=null)
		return sp;
		else {
			return null;
		}
	}

	
	public RouteBean viewByRouteId(String routeid) throws SQLException {
		loggr.info("Routes details by id");
		RouteBean rb=rbean.findByID(routeid);
		if(rb!=null)
		return rb;
		else {
			return null;
		}
	}

	
	public ArrayList<ShipBean> viewByAllShips() throws SQLException {
		loggr.info("All ships displayed");
		ArrayList<ShipBean> al=new ArrayList<ShipBean>();
		al=Shipsbean.findAll();
		if(al!=null)
		{
			return al;
		}
		return null;
	}

	
	public ArrayList<RouteBean> viewByAllRoute() throws SQLException {
		loggr.info("All routes displayed");
		ArrayList<RouteBean> al=new ArrayList<RouteBean>();
		al=rbean.findAll();
		if(al!=null)
		{
			return al;
		}
		return null;
	}

	
	public ArrayList<ScheduleBean> viewByAllSchedule() throws SQLException {
		loggr.info("All schedules displayed");
		ArrayList<ScheduleBean> al=new ArrayList<ScheduleBean>();
		al=Schedulebean.findAll();
		if(al!=null)
		{
			return al;
		}
		return null;
	}

	
	public ScheduleBean viewByScheduleId(String scheduleid) throws SQLException {
		loggr.info("view schedule by id");
		ScheduleBean sb=Schedulebean.findByID(scheduleid);
		if(sb!=null)
		return sb;
		else {
			return null;
		}
	}

	
	public ArrayList<PassengerBean> viewPasengersByShip(String shipid) throws SQLException {
		loggr.info("all passengers by ship");
		ArrayList<PassengerBean> apass=new ArrayList<PassengerBean>();
		apass=passenger.findByShip(shipid);
		if(apass!=null)
		{
			return apass;
		}
		return null;
	}

}
