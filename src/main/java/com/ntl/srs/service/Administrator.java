package com.ntl.srs.service;



import java.sql.SQLException;
import java.util.ArrayList;

import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.bean.ShipBean;

public interface Administrator {


		String  addShip(ShipBean shipbean) throws SQLException;
		boolean modifyShip(ShipBean Shipbean) throws SQLException;
		String  addSchedule(ScheduleBean schedulebean) throws SQLException ;
		boolean  modifySchedule(ScheduleBean schedulebean) throws SQLException ;
		int  removeSchedule(ArrayList<String>  scheduleid) ;
		String  addRoute(RouteBean routebean) throws SQLException;
		boolean  modifyRoute(RouteBean routebean) throws SQLException;
		int  removeRoute(String routeid) throws SQLException ;
		ShipBean  viewByShipId(String ShipId) throws SQLException ;
		RouteBean  viewByRouteId(String routeid) throws SQLException ;
		ArrayList<ShipBean>  viewByAllShips() throws SQLException;
		ArrayList<RouteBean>  viewByAllRoute() throws SQLException;
		ArrayList<ScheduleBean>  viewByAllSchedule() throws SQLException;
		ScheduleBean  viewByScheduleId(String scheduleid) throws SQLException;
		ArrayList<PassengerBean> viewPasengersByShip(String scheduleid) throws SQLException;

	}

	

