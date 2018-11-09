package com.ntl.srs.serviceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.daoImpl.CredentialsBeanDaoImpl;
import com.ntl.srs.daoImpl.PassengerBeanDaoImpl;
import com.ntl.srs.daoImpl.RouteBeanDaoImpl;
import com.ntl.srs.daoImpl.ScheduleBeanDaoImpl;
import com.ntl.srs.daoImpl.ShipBeanDaoImpl;
import com.ntl.srs.utilImpl.LoggedIn;

public class AdministratorTest {

	ShipBeanDaoImpl ship;
	RouteBeanDaoImpl roue;
	ScheduleBeanDaoImpl schedu;
	PassengerBeanDaoImpl passen;

	@Test
	public void testAddShip() throws SQLException {
		ShipBean sb=new ShipBean("Sh7890","Borbar",400,140);
		
		ship=mock(ShipBeanDaoImpl.class);
		
			when(ship.createShipBean(sb)).thenReturn("success");
		
		
		AdministratorImpl admin=new AdministratorImpl(ship);
		
		String result = admin.addShip(sb);
		
		assertEquals(result,"success");
	}

	@Test
	public void testModifyShip() throws SQLException {
	ShipBean sb=new ShipBean("Sh7890","Borbar",400,190);
		
		ship=mock(ShipBeanDaoImpl.class);
		
			when(ship.updateShipBean(sb)).thenReturn(true);
		
		
		AdministratorImpl admin=new AdministratorImpl(ship);
		
		boolean result = admin.modifyShip(sb);
		
		assertEquals(result,true);
	}

	@Test
	public void testAddSchedule() throws SQLException {
		
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("InAu2345","InAu3456","Sh7890",dat);
		
		schedu=mock(ScheduleBeanDaoImpl.class);
		
			when(schedu.createScheduleBean(sb)).thenReturn("success");
		
		
		AdministratorImpl admin=new AdministratorImpl(schedu);
		
		String result = admin.addSchedule(sb);
		
		assertEquals(result,"success");
	}

	@Test
	public void testModifySchedule() throws SQLException {
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("InAu2345","InAu4786","th3748",dat);
		
		schedu=mock(ScheduleBeanDaoImpl.class);
		
			when(schedu.updateScheduleBean(sb)).thenReturn(true);
		
		
		AdministratorImpl admin=new AdministratorImpl(schedu);
		
		boolean result = admin.modifySchedule(sb);
		
		assertEquals(result,true);
	}

	@Test
	public void testRemoveSchedule() {
		
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("InAu2345","InAu4786","th3748",dat);
		
		ScheduleBean sbb=new ScheduleBean("ItAu2245","ItAu4486","td3748",dat);
		
		
		schedu=mock(ScheduleBeanDaoImpl.class);
		ArrayList<String> al=new ArrayList<String>();
		al.add("InAu2345");
		al.add("ItAu2245");
		when(schedu.deleteScheduleBean(al)).thenReturn(2);
		
		
		AdministratorImpl admin=new AdministratorImpl(schedu);
		
		int result = admin.removeSchedule(al);
		
		assertEquals(result,2);
	}

	@Test
	public void testAddRoute() throws SQLException {
RouteBean rb=new RouteBean("InAu3456","India","Australia","4000",44000);
		
		roue=mock(RouteBeanDaoImpl.class);
		
		when(roue.createRouteBean(rb)).thenReturn("success");
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		String result = admin.addRoute(rb);
		
		assertEquals(result,"success");
	}

	@Test
	public void testModifyRoute() throws SQLException {
RouteBean rb=new RouteBean("InAu3456","India","Australia","4000",44000);
		
		roue=mock(RouteBeanDaoImpl.class);
		
		when(roue.updateRouteBean(rb)).thenReturn(true);
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		boolean result = admin.modifyRoute(rb);
		
		assertEquals(result,true);
	}

	@Test
	public void testRemoveRoute() throws SQLException {
RouteBean rb=new RouteBean("InAu3456","India","Australia","4000",44000);
RouteBean rbb=new RouteBean("Ausr3255","Australia","Sri Lanka","4000",44000);

		String s="InAu3456"+ " "+"AuSr3255";
		roue=mock(RouteBeanDaoImpl.class);
		ArrayList<String> al=new ArrayList<String>();
		al.add("InAu3456");
		al.add("AuSr3255");
		when(roue.deleteRouteBean(al)).thenReturn(2);
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		int result = admin.removeRoute(s);
		
		assertEquals(result,2);
	}

	@Test
	public void testViewByShipId() throws SQLException {
	ShipBean sb=new ShipBean("Sh7890","Borbar",400,140);
		
		ship=mock(ShipBeanDaoImpl.class);
		
			when(ship.findByID(sb.getShipID())).thenReturn(sb);
		
		
		AdministratorImpl admin=new AdministratorImpl(ship);
		
		ShipBean result = admin.viewByShipId(sb.getShipID());
		
		assertEquals(result.getShipID(),"Sh7890");
	}

	@Test
	public void testViewByRouteId() throws SQLException {
RouteBean rb=new RouteBean("InAu3456","India","Australia","4000",44000);
		
		roue=mock(RouteBeanDaoImpl.class);
		
		when(roue.findByID(rb.getRouteID())).thenReturn(rb);
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		RouteBean result = admin.viewByRouteId(rb.getRouteID());
		
		assertEquals(result.getRouteID(),"InAu3456");
	}

	@Test
	public void testViewByAllShips() throws SQLException {
	ShipBean sb=new ShipBean("Sh7890","Borbar",400,140);
	ShipBean sbb=new ShipBean("Sh6899","Aorbar",420,170);
	ArrayList<ShipBean> sp=new ArrayList<ShipBean>();
	sp.add(sb);
	sp.add(sbb);
		ship=mock(ShipBeanDaoImpl.class);
		
			when(ship.findAll()).thenReturn(sp);
		
		
		AdministratorImpl admin=new AdministratorImpl(ship);
		
		ArrayList<ShipBean> result = admin.viewByAllShips();
		
		assertEquals(result.size(),2);
	}

	@Test
	public void testViewByAllRoute() throws SQLException {
RouteBean rb=new RouteBean("InAu3456","India","Australia","4000",44000);
RouteBean rbb=new RouteBean("Ausr3255","Australia","Sri Lanka","4000",44000);
		roue=mock(RouteBeanDaoImpl.class);
		
		ArrayList<RouteBean> ab=new ArrayList<RouteBean>();
		ab.add(rbb);
		ab.add(rb);
		when(roue.findAll()).thenReturn(ab);
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		ArrayList<RouteBean> result = admin.viewByAllRoute();
		
		assertEquals(result.size(),2);
	}

	@Test
	public void testViewByAllSchedule() throws SQLException {
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("InAu2346","InAu4786","th3748",dat);
		ArrayList<ScheduleBean> schbean=new ArrayList<ScheduleBean>();
		schbean.add(sb);
		schedu=mock(ScheduleBeanDaoImpl.class);
		
			when(schedu.findAll()).thenReturn(schbean);
		
		
		AdministratorImpl admin=new AdministratorImpl(schedu);
		
		ArrayList<ScheduleBean> result = admin.viewByAllSchedule();
		
		assertEquals(result.size(),1);
	}

	@Test
	public void testViewByScheduleId() throws SQLException {
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("InAu2345","InAu4786","th3748",dat);
		
		schedu=mock(ScheduleBeanDaoImpl.class);
		
			when(schedu.findByID(sb.getScheduleID())).thenReturn(sb);
		
		
		AdministratorImpl admin=new AdministratorImpl(schedu);
		
		ScheduleBean result = admin.viewByScheduleId(sb.getScheduleID());
		
		assertEquals(result.getScheduleID(),"InAu2345");
	}

	@Test
	public void testViewPasengersByShip() throws SQLException {
		PassengerBean rb=new PassengerBean("InAu3456","InAu4763","Ram",32,"Male");
		PassengerBean pb=new PassengerBean("InAu3456","InAu4763","Antara",22,"Female");
		//RouteBean rbb=new RouteBean("Ausr3255","Australia","Sri Lanka","4000",44000);
		
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("InAu2356","InAu4343","th3748",dat);
		
				passen=mock(PassengerBeanDaoImpl.class);
				
				ArrayList<PassengerBean> ab=new ArrayList<PassengerBean>();
				
				ab.add(rb);
				ab.add(pb);
				when(passen.findByShip("th3748")).thenReturn(ab);
				
				
				AdministratorImpl admin=new AdministratorImpl(passen);
				
				ArrayList<PassengerBean> result = admin.viewPasengersByShip("th3748");
				
				assertEquals(result.size(),2);
	}

}
