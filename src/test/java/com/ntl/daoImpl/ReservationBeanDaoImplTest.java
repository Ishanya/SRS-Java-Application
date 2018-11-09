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

import com.ntl.srs.bean.ReservationBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.daoImpl.ProfileBeanDaoImpl;
import com.ntl.srs.daoImpl.ReservationBeanDaoImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReservationBeanDaoImplTest {

	
	 DataSource mockDataSource=mock(DataSource.class);
	    Connection mockConn=mock(Connection.class);
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    Statement mockCreateStmt=mock(Statement.class);
	    ResultSet mockResultSet=mock(ResultSet.class);
	    
	    
	    String dateoff="20/02/2010";
	    String strr[]=dateoff.split("/");
	    LocalDate bd=LocalDate.of(Integer.parseInt(strr[2]),Integer.parseInt(strr[1]), Integer.parseInt(strr[0]));
	
	    String dateof="21/02/2010";
	    String str[]=dateof.split("/");
	    LocalDate jd=LocalDate.of(Integer.parseInt(str[2]),Integer.parseInt(str[1]), Integer.parseInt(str[0]));
	
	    	ReservationBean reserve=new ReservationBean("IsSr1234","IsSr2345","In8970",bd,jd,2,20000,"pending");
	
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
        when(mockResultSet.getString(1)).thenReturn("IsSr1234");
        when(mockResultSet.getString(2)).thenReturn("IsSr2345");
        when(mockResultSet.getString(3)).thenReturn("Is8970");
        when(mockResultSet.getDate(4)).thenReturn(Date.valueOf(bd));
        when(mockResultSet.getDate(5)).thenReturn(Date.valueOf(jd));
        when(mockResultSet.getInt(6)).thenReturn(2);
        when(mockResultSet.getInt(7)).thenReturn(20000);
        when(mockResultSet.getString(8)).thenReturn("pending");
      //  when(mockResultSet.getInt(4)).thenReturn(0);
    }


	@Test
	public void testCreateReservationBean() {
		ReservationBeanDaoImpl reservation=new ReservationBeanDaoImpl(mockDataSource);

		assertEquals("pending",reservation.createReservationBean(reserve));
	}

	@Test
	public void testDeleteReservationBean() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdateReservationBean() {
		ReservationBeanDaoImpl reservation=new ReservationBeanDaoImpl(mockDataSource);

		assertEquals(true,reservation.updateReservationBean(reserve));	
	}

	@Test
	public void testFindByID() {
		ReservationBeanDaoImpl reservation=new ReservationBeanDaoImpl(mockDataSource);

		assertEquals(reserve.getNoOfSeats(),reservation.findByID(reserve.getReservationID()).getNoOfSeats());
	}

	@Test
	public void testFindAll() {
		//fail("Not yet implemented");
	}

	@Test
	public void testScheduletoRoute() {
		ReservationBeanDaoImpl reservation=new ReservationBeanDaoImpl(mockDataSource);
		ScheduleBean sb=new ScheduleBean("IsSr2345","IsSr4567","In8970");
		ArrayList<ScheduleBean> arl=new ArrayList<ScheduleBean>();
		arl.add(sb);
		
		assertEquals(arl.size(),reservation.ScheduletoRoute("India", "Sri Lanka",bd).size());
	}

}
