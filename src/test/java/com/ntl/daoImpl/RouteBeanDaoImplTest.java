package com.ntl.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.junit.Before;
import org.junit.Test;

import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.daoImpl.ProfileBeanDaoImpl;
import com.ntl.srs.daoImpl.RouteBeanDaoImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RouteBeanDaoImplTest {

	 DataSource mockDataSource=mock(DataSource.class);
	    Connection mockConn=mock(Connection.class);
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    Statement mockCreateStmt=mock(Statement.class);
	    ResultSet mockResultSet=mock(ResultSet.class);
	    
	    RouteBean route=new RouteBean("InSr1234","India","Sri Lanka","1200",20000);
	    
	    @Before
	    public void setUp() throws SQLException {
	//	 when(mockDataSource.getDBConnection("jdbc")).thenReturn(mockConn);
		 
		// ship=new ShipBeanDaoImpl(dbutil);
		 
		 
	    //    doNothing().when(mockConn).commit();
			 when(mockDataSource.getConnection()).thenReturn(mockConn);
	        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
	        when(mockConn.createStatement()).thenReturn(mockCreateStmt);
	        when(mockCreateStmt.executeQuery(anyString())).thenReturn(mockResultSet);
	       when(mockCreateStmt.executeUpdate(anyString())).thenReturn(1);
	        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
	        doNothing().when(mockPreparedStmnt).setInt(anyInt(), anyInt());
	        when(mockPreparedStmnt.executeUpdate()).thenReturn(1);
	        when(mockPreparedStmnt.executeQuery()).thenReturn(mockResultSet);
	       // when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
	        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
	        when(mockResultSet.first()).thenReturn(true);
	        when(mockResultSet.getString(1)).thenReturn("InSr1234");
	        when(mockResultSet.getString(2)).thenReturn("India");
	        when(mockResultSet.getString(3)).thenReturn("Sri Lanka");
	        
	        when(mockResultSet.getString(4)).thenReturn("1200");
	        when(mockResultSet.getInt(5)).thenReturn(20000);
	    }


	@Test
	public void testCreateRouteBean() {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);
		
		assertEquals("success",routebean.createRouteBean(route));
	}

	@Test
	public void testDeleteRouteBean() {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);
		ArrayList<String> al=new ArrayList<String>();
		al.add("InSr1234");
		assertEquals(1,routebean.deleteRouteBean(al));
	}

	@Test
	public void testUpdateRouteBean() {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);

		assertEquals(true,routebean.updateRouteBean(route));
	}

	@Test
	public void testFindByID() {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);

		assertEquals(route.getFare(),routebean.findByID(route.getRouteID()).getFare());
	}

	@Test
	public void testFindAll() {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);
		ArrayList<RouteBean> al=new ArrayList<RouteBean>();
		al.add(route);
		assertEquals(al.size(),routebean.findAll().size());
	}

}
