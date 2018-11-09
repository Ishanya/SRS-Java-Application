package com.ntl.srs.utilImpl;

import java.util.Random;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.bean.ProfileBean;
import com.ntl.srs.daoImpl.CredentialsBeanDaoImpl;
import com.ntl.srs.daoImpl.ProfileBeanDaoImpl;
import com.ntl.srs.util.Authentication;
import com.ntl.srs.util.User;

import Client.Shiping;

public class SignedUp implements User{
LoggedIn log = new LoggedIn();
static Logger loggr=Logger.getLogger(SignedUp.class);
CredentialsBeanDaoImpl cred=new CredentialsBeanDaoImpl();
	/**
 * 
 */
public SignedUp(CredentialsBeanDaoImpl c) {
	super();
	cred=c;
	
}


public SignedUp(LoggedIn lod,CredentialsBeanDaoImpl c) {
	super();
	cred=c;
	log=lod;
	
}


ProfileBeanDaoImpl profile=new ProfileBeanDaoImpl();
public SignedUp(ProfileBeanDaoImpl prof) {
	super();
	profile=prof;
}

	/**
 * 
 */
public SignedUp() {
	super();
}


 

public SignedUp(LoggedIn logindao) {
	super();
	log=logindao;
}

	public String login(CredentialsBean credit) {
		loggr.info("Logging in");
		CredentialsBeanDaoImpl cr=new CredentialsBeanDaoImpl();
		CredentialsBean cdre=new CredentialsBean();
		cdre=cr.findByID(credit.getUserID());
		
	if(cdre!=null) {
		if(log.authenticate(credit))
		{
			if(cdre.getLoginStatus()==0) {
			String userType=log.authorize(credit.getUserID());
			 log.changeLoginStatus(credit,credit.getLoginStatus());
			 return userType;
			}
			else {
				
				return "fail";
			}
		}
		else {
			return "invalid";
		}
	}
	else {
		return "invalid";
	}
	}

	/*
	public boolean logout(String userId) {
		ProfileBeanDaoImpl pb=new ProfileBeanDaoImpl();
		ProfileBean prof=pb.findByID(userId);
		
	CredentialsBean cred=new CredentialsBean();
		
		if(pb.updateProfileBean(prof))
		{
			System.out.println("Successfully Logged Out! Happy to serve you");
		}
		else {
			System.out.println("Unable to log you out! Sorry");
		}
		
		return false;
	}*/

	
	public boolean logout(String userId) {
		//System.out.println("welcome");
		loggr.info("Logging out");
		CredentialsBean prof=new CredentialsBean();
		 prof=cred.findByID(userId);
		// System.out.println("gtyu");
		// System.out.println(prof.getUserID());
		LoggedIn log=new LoggedIn();
		
		
		if(log.changeLoginStatus(prof, prof.getLoginStatus()))
		{
			System.out.println("Successfully Logged Out! Happy to serve you");
		}
		else {
			System.out.println("Unable to log you out! Sorry");
		}
		
		return false;
	}


	
	
	public String changePassword(CredentialsBean credentialsBean, String newPassword) {
		// TODO Auto-generated method stub
		loggr.info("Changing Password");
		
	//	CredentialsBeanDaoImpl cred = new CredentialsBeanDaoImpl();
		//credentialsBean.setPassword(newPassword);
		if(cred.changingPassword(credentialsBean)) {
			return credentialsBean.getUserID();
		}
		else {
			return null;
		}
		
	
	}
	
	
	
	
	public String register(ProfileBean profileBean) {
		// TODO Auto-generated method stub
	//	System.out.println("helo ui");
//		Random rand = new Random();
//	//	profileBean.setUserID(profileBean.getFirstName().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
//		System.out.println("please NOTE UR UNIQUE ID: "+profileBean.getUserID());
		loggr.info("registering....");
		
		String uid=profile.createProfileBean(profileBean);
		if(uid!=null)
		{
			return "success";
		}
		else {
			
			return null;
		}
		
	}
	
	public boolean validating(String value) {
		boolean result=Pattern.matches("[A-Za-z]{2,}",value);
		return result;
		
	}

}
