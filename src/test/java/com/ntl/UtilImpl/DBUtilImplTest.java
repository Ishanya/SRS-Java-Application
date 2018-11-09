package com.ntl.UtilImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.runners.MockitoJUnitRunner;
import com.ntl.srs.utilImpl.DBUtilImpl;

@RunWith(MockitoJUnitRunner.class)
public class DBUtilImplTest {

	@Mock
    DataSource mockDataSource;
    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement mockPreparedStmnt;
    @Mock
    ResultSet mockResultSet;
	
    
    
    
    @Before
    public void setUp() throws SQLException {

	 
	
        when(mockDataSource.getConnection()).thenReturn(mockConn);
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
        doNothing().when(mockPreparedStmnt).setInt(anyInt(), anyInt());
        when(mockPreparedStmnt.executeUpdate()).thenReturn(anyInt());
       // when(mockPreparedStmnt.executeQuery()).thenReturn(mockResultSet);
       // when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
       // when(mockResultSet.getInt(Fields.GENERATED_KEYS)).thenReturn(userId);
    }
    
	@Test
	public void testGetDBConnection() {
		  DBUtilImpl instance = new DBUtilImpl(mockDataSource);
	        instance.getDBConnection("jdbc");
	        
	       boolean result =true;
	        
	        assertEquals(true,result);
	      
	}

	@Test
	public void testClosing() {
		//fail("Not yet implemented");
	}

}
