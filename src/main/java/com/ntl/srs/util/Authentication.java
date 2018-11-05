package com.ntl.srs.util;

import com.ntl.srs.bean.CredentialsBean;

public interface Authentication {
	boolean authenticate(CredentialsBean credentialsBean);
	String authorize(String Userid);
	boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus) ;
}
