package com.ntl.srs.utilImpl;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.daoImpl.CredentialsBeanDaoImpl;
import com.ntl.srs.util.Authentication;

import Client.Shiping;



public class LoggedIn implements Authentication {

	CredentialsBeanDaoImpl guc=new CredentialsBeanDaoImpl();
	static Logger loggr=Logger.getLogger(LoggedIn.class);
	
	public LoggedIn( CredentialsBeanDaoImpl credit) {
		super();
		guc=credit;
		
	}
	
	
	public LoggedIn() {
		super();
	}




	//String UserType[]=new String[2];
	static CredentialsBean credit;
	
	
	public boolean authenticate(CredentialsBean credentialsBean) {
		
		loggr.info("authentication done");
		
		credit=guc.findByID(credentialsBean.getUserID());
		int flag=0;
		if(Pattern.matches("[A-Za-z]{2}[0-9]{4}", credentialsBean.getUserID()) && Pattern.matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", credentialsBean.getPassword()))
		{
		
				
				if(credit!=null ) {
					if(credit.getPassword().equals(credentialsBean.getPassword()) )
						{
			//	guc.changeStatus(credentialsBean);
					flag=1;
					return true;
				
						}
					else {
						System.out.println("fail");
						return false;
					}
				}
			else {
				System.out.println("invalid");
				return false;
			}
			
			
			
		}
		else {
			System.out.println("Username or password is not in correct format");
			return false;
		}
		
	}

		
		
	

	
	public String authorize(String Userid) {
		// TODO Auto-generated method stub
		loggr.info("authorized...");
		//CredentialsBeanDaoImpl credImpl=new CredentialsBeanDaoImpl();
		
		if(credit.getUserID().equals(Userid))
		{
			return credit.getUserType();
			
		}
		
		return null;
	}

	
	public boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus) {
		// TODO Auto-generated method stub
		loggr.info("Login Status Changed");
		CredentialsBean credit=new CredentialsBean();
		
		credit=guc.findByID(credentialsBean.getUserID());
	
		if(credit!=null)
		{
			if(guc.updateCredentialsBean(credentialsBean))
			{
				System.out.println("Status changed");
				return true;
			}
			else {
				System.out.println("status not changed");
			}
			
			
			
		}
		return false;
	}

}
