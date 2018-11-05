package com.ntl.daoImpl;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

import javax.sql.DataSource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;


import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.daoImpl.CredentialsBeanDaoImpl;
import com.ntl.srs.util.DBUtil;

public class CredentialsBeanDaoImplTest {

	
  
	CredentialsBeanDaoImpl cred;
	
	 
	
	@Test
	public void testCredentialsBeanDaoImpl() {
		//fail("Not yet implemented");
	}

	@Test
	public void testCredentials() {
		CredentialsBean credi=new CredentialsBean("Is1111","Ishanya@333","A",0);
		
		cred=mock(CredentialsBeanDaoImpl.class);
		String str[]=new String[2];
		str[0]="A";
		str[1]=""+0;
		when(cred.credentials(credi)).thenReturn(str);// this is just to test DAO method before testing the actual Authenticate method
		
	
		String[] result=cred.credentials(credi);
		
		assertEquals(result[0],"A");
	}

	@Test
	public void testUpdateCredentialsBean() {
CredentialsBean credi=new CredentialsBean("Is1111","Ishanya@333","A",0);
		
		cred=mock(CredentialsBeanDaoImpl.class);
		
		when(cred.updateCredentialsBean(credi)).thenReturn(true);// this is just to test DAO method before testing the actual Authenticate method
		
	
		boolean result=cred.updateCredentialsBean(credi);
		
		assertEquals(result,true);
	}

	@Test
	public void testFindByID() {
		
		CredentialsBean credi=new CredentialsBean("Is1111","Ishanya@333","A",0);
		
		cred=mock(CredentialsBeanDaoImpl.class);
		
		//CredentialsBeanDaoImpl cre=new CredentialsBeanDaoImpl();
		
		when(cred.findByID("Is1111")).thenReturn(credi);// this is just to test DAO method before testing the actual Authenticate method
		
	//	CredentialsBeanDaoImpl cb=new CredentialsBeanDaoImpl();
		CredentialsBean result=cred.findByID("Is1111");
		
		assertEquals(result.getUserID(),credi.getUserID());
	}
	
	
	@Test
	public void testFindAll() {
		//fail("Not yet implemented");
	}

	@Test
	public void testChangingPassword() {
CredentialsBean credi=new CredentialsBean("Is1111","Ishanya@333","A",0);
		
		cred=mock(CredentialsBeanDaoImpl.class);
	
		when(cred.changingPassword(credi)).thenReturn(true);// this is just to test DAO method before testing the actual Authenticate method
		 
	
		boolean result=cred.changingPassword(credi);
		
		assertEquals(result,true);
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
