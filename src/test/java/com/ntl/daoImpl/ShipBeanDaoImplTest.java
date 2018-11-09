package com.ntl.daoImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import org.junit.Before;
import org.junit.Test;


import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.daoImpl.CredentialsBeanDaoImpl;
import com.ntl.srs.daoImpl.ShipBeanDaoImpl;
import com.ntl.srs.utilImpl.DBUtilImpl;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Fields;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ShipBeanDaoImplTest {
	
	

    DataSource mockDataSource=mock(DataSource.class);
    Connection mockConn=mock(Connection.class);
    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
    Statement mockCreateStmt=mock(Statement.class);
    ResultSet mockResultSet=mock(ResultSet.class);

	ShipBean shipbean=new ShipBean("As8796","Austin",2541,876);
	//ShipBeanDaoImpl ship;
		 @Before
	    public void setUp() throws SQLException {
	//	 when(mockDataSource.getDBConnection("jdbc")).thenReturn(mockConn);
		 
		// ship=new ShipBeanDaoImpl(dbutil);
		 
		 
	    //    doNothing().when(mockConn).commit();
			 when(mockDataSource.getConnection()).thenReturn(mockConn);
	        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
	        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
	        doNothing().when(mockPreparedStmnt).setInt(anyInt(), anyInt());
	        when(mockPreparedStmnt.executeUpdate()).thenReturn(1);
	        when(mockPreparedStmnt.executeQuery()).thenReturn(mockResultSet);
	       // when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
	        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
	        
	        when(mockResultSet.getString(1)).thenReturn("As8796");
	        when(mockResultSet.getString(2)).thenReturn("Austin");
	        when(mockResultSet.getInt(3)).thenReturn(2541);
	        when(mockResultSet.getInt(4)).thenReturn(876);
	        when(mockResultSet.first()).thenReturn(true);
	    }
	
	@Test
	public void testCreateShipBean() throws SQLException {
		
		ShipBeanDaoImpl ship=new ShipBeanDaoImpl(mockDataSource);
		
		assertEquals("success",ship.createShipBean(shipbean));
	}

//	@Test
	public void testDeleteShipBean() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testUpdateShipBean() throws SQLException {
		ShipBeanDaoImpl ship=new ShipBeanDaoImpl(mockDataSource);
		
		assertEquals(true,ship.updateShipBean(shipbean));
	}

	@Test
	public void testFindByID() throws SQLException {
		ShipBeanDaoImpl ship=new ShipBeanDaoImpl(mockDataSource);
		ship.createShipBean(shipbean);
		assertEquals(shipbean.getShipID(),ship.findByID(shipbean.getShipID()).getShipID());
	}

	@Test
	public void testFindAll() throws SQLException {
		ShipBeanDaoImpl ship=new ShipBeanDaoImpl(mockDataSource);
		//ship.createShipBean(shipbean);
		ArrayList<ShipBean> al=new ArrayList<ShipBean>();
		al.add(shipbean);
		
		assertEquals(al.size(),ship.findAll().size());
	}
	
	


}
