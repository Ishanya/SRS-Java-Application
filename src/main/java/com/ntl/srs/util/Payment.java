package com.ntl.srs.util;

import java.sql.SQLException;

import com.ntl.srs.bean.PaymentBean;

public interface Payment {

	boolean findByCardNumber(String userid, String cardnumber) throws SQLException;
	String process(PaymentBean payment) throws SQLException;
	


	
}
