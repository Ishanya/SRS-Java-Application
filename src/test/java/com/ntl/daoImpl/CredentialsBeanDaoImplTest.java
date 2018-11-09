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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.daoImpl.CredentialsBeanDaoImpl;
import com.ntl.srs.daoImpl.ShipBeanDaoImpl;
import com.ntl.srs.util.DBUtil;

public class CredentialsBeanDaoImplTest {
	 DataSource mockDataSource=mock(DataSource.class);
	    Connection mockConn=mock(Connection.class);
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    Statement mockCreateStmt=mock(Statement.class);
	    ResultSet mockResultSet=mock(ResultSet.class);

	CredentialsBean credi=new CredentialsBean("Is1111","Ishanya@333","A",0);
	
	CredentialsBeanDaoImpl cred;
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
	        
	        when(mockResultSet.getString(1)).thenReturn("Is1111");
	        when(mockResultSet.getString(2)).thenReturn("Ishanya@333");
	        when(mockResultSet.getString(3)).thenReturn("A");
	        when(mockResultSet.getInt(4)).thenReturn(0);
	    }
	
	@Test
	public void testCredentialsBeanDaoImpl() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCredentials() {
		//CredentialsBean credi=new CredentialsBean("Is1111","Ishanya@333","A",0);
		
		
		String str[]=new String[2];
		str[0]="A";
		str[1]=""+0;
		
		CredentialsBeanDaoImpl credit=new CredentialsBeanDaoImpl(mockDataSource);
	
		String[] result=credit.credentials(credi);
		
		assertEquals("A",result[0]);
		
		
	}

	@Test
	public void testUpdateCredentialsBean() {
//CredentialsBean credi=new CredentialsBean("Is1111","Ishanya@333","A",0);
		
CredentialsBeanDaoImpl cred=new CredentialsBeanDaoImpl(mockDataSource);

assertEquals(true,cred.updateCredentialsBean(credi));
	}

	@Test
	public void testFindByID() {
		
		
CredentialsBeanDaoImpl cred=new CredentialsBeanDaoImpl(mockDataSource);

assertEquals(credi.getUserID(),cred.findByID(credi.getUserID()).getUserID());
	}
	
	
	@Test
	public void testFindAll() {
		//fail("Not yet implemented");
	}

	@Test
	public void testChangingPassword() {
		
CredentialsBeanDaoImpl cred=new CredentialsBeanDaoImpl(mockDataSource);

assertEquals(true,cred.changingPassword(credi));
	}

	@Test
	public void testCreateCredentialsBean() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDeleteCredentialsBean() {
		//fail("Not yet implemented");
	}

}
