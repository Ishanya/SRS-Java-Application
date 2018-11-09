package com.ntl.daoImpl;

import java.sql.Connection;
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

import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.ReservationBean;
import com.ntl.srs.daoImpl.PassengerBeanDaoImpl;
import com.ntl.srs.daoImpl.ReservationBeanDaoImpl;
import com.ntl.srs.daoImpl.ShipBeanDaoImpl;
import com.ntl.srs.utilImpl.DBUtilImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PassengerBeanDaoImplTest {

	 DataSource mockDataSource=mock(DataSource.class);
	    Connection mockConn=mock(Connection.class);
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    Statement mockCreateStmt=mock(Statement.class);
	    ResultSet mockResultSet=mock(ResultSet.class);
	    ReservationBeanDaoImpl mockReservation=mock(ReservationBeanDaoImpl.class);
	    //PassengerBean mockPassenger=mock(PassengerBeanDaoImpl.class);
	    
	PassengerBean pass=new PassengerBean("InSr1234","InSr2345","Ishanya",23,"Male");
	
	 String dateoff="20/02/2010";
	    String strr[]=dateoff.split("/");
	    LocalDate bd=LocalDate.of(Integer.parseInt(strr[2]),Integer.parseInt(strr[1]), Integer.parseInt(strr[0]));
	
	    String dateof="21/02/2010";
	    String str[]=dateof.split("/");
	    LocalDate jd=LocalDate.of(Integer.parseInt(str[2]),Integer.parseInt(str[1]), Integer.parseInt(str[0]));
	
	    ReservationBean reserve=new ReservationBean("IsSr1234","IsSr2345","In8970",bd,jd,2,20000,"pending");
	
	    
	 @Before
	    public void setUp() throws SQLException {
	
		 when(mockReservation.findByID(pass.getReservationID())).thenReturn(reserve);
		// when(reserve).getScheduleID().thenReturn("IsSr2345");
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
	        
	        when(mockResultSet.getString(1)).thenReturn("InSr1234");
	        when(mockResultSet.getString(2)).thenReturn("InSr2345");
	        when(mockResultSet.getString(3)).thenReturn("Ishanya");
	        when(mockResultSet.getInt(4)).thenReturn(23);
	        when(mockResultSet.getString(5)).thenReturn("Male");
	    }
	@Test
	public void testCreatePassengerBean() {
	PassengerBeanDaoImpl passenger=new PassengerBeanDaoImpl(mockDataSource);
		
		assertEquals("Success",passenger.createPassengerBean(pass));
	}

	@Test
	public void testDeletePassengerBean() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdatePassengerBean() {
		//fail("Not yet implemented");
	}

	@Test
	public void testFindByID() {
PassengerBeanDaoImpl passenger=new PassengerBeanDaoImpl(mockDataSource);
		
		assertEquals(pass.getReservationID(),passenger.findByID(pass.getReservationID()).getReservationID());
	}

	@Test
	public void testFindAll() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAllPass() {
PassengerBeanDaoImpl passenger=new PassengerBeanDaoImpl(mockDataSource);
		
		try {
			assertEquals(true,passenger.allPass(pass.getReservationID()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindAllById() {
PassengerBeanDaoImpl passenger=new PassengerBeanDaoImpl(mockDataSource);
		
ArrayList<PassengerBean> al=new ArrayList<PassengerBean>();
al.add(pass);

		assertEquals(al.size(),passenger.findAllById(pass.getReservationID()).size());
	}

	@Test
	public void testFindByShip() {
PassengerBeanDaoImpl passenger=new PassengerBeanDaoImpl(mockDataSource);
		
ArrayList<PassengerBean> al=new ArrayList<PassengerBean>();
al.add(pass);

		assertEquals(al.size(),passenger.findByShip("it4563").size());
	}

}
