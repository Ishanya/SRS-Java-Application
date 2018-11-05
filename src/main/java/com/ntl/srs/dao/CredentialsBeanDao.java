package com.ntl.srs.dao;


import java.util.ArrayList;

import com.ntl.srs.bean.CredentialsBean;


public interface CredentialsBeanDao {

		
		String createCredentialsBean(CredentialsBean credentialsBean) ;
		int deleteCredentialsBean(ArrayList<String> al );
		boolean updateCredentialsBean(CredentialsBean credentialsBean);
		CredentialsBean findByID(String id);
		ArrayList<CredentialsBean> findAll();
		
	}

	

