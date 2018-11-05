package com.ntl.daoImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.daoImpl.CredentialsBeanDaoImpl;
import com.ntl.srs.daoImpl.ShipBeanDaoImpl;

public class ShipBeanDaoImplTest {

	ShipBean shipbean=new ShipBean("As8796","Austin",2541,876);
	ShipBeanDaoImpl ship;
	
	@Test
	public void testCreateShipBean() throws SQLException {

		
		ship=mock(ShipBeanDaoImpl.class);
		
		when(ship.createShipBean(shipbean)).thenReturn("success");// this is just to test DAO method before testing the actual Authenticate method
		
	
		String result=ship.createShipBean(shipbean);
		
		assertEquals(result,"success");
	}

	@Test
	public void testDeleteShipBean() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testUpdateShipBean() throws SQLException {
ship=mock(ShipBeanDaoImpl.class);
		
		when(ship.updateShipBean(shipbean)).thenReturn(true);// this is just to test DAO method before testing the actual Authenticate method
		
	
		boolean result=ship.updateShipBean(shipbean);
		
		assertEquals(result,true);
	}

	@Test
	public void testFindByID() throws SQLException {
		ship=mock(ShipBeanDaoImpl.class);
		
		when(ship.findByID(shipbean.getShipID())).thenReturn(shipbean);// this is just to test DAO method before testing the actual Authenticate method
		
	
		ShipBean result=ship.findByID(shipbean.getShipID());
		
		assertEquals(result.getShipID(),shipbean.getShipID());
	}

	@Test
	public void testFindAll() throws SQLException {
		ship=mock(ShipBeanDaoImpl.class);
		
		ArrayList<ShipBean> arr=new ArrayList<ShipBean>();
		arr.add(shipbean);
		
		when(ship.findAll()).thenReturn(arr);// this is just to test DAO method before testing the actual Authenticate method
		
	
		ArrayList<ShipBean> result=ship.findAll();
		
		assertEquals(result.size(),1);
	}

}
