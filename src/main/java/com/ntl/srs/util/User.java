package com.ntl.srs.util;

import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.bean.ProfileBean;

public interface User {
	String login(CredentialsBean credentialsBean);
	boolean logout(String userId) ;
	String changePassword(CredentialsBean credentialsBean, String newPassword) ;
	String register(ProfileBean profileBean);
}
