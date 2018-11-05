package com.ntl.srs.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ntl.srs.bean.PaymentBean;

public interface PaymentDao {

	

	String createPaymentBean(PaymentBean paymentBean) throws SQLException;
//	int deletePaymentBean(ArrayList<String> al ) throws SQLException;
	boolean updatePaymentBean(PaymentBean PaymentBean) throws SQLException ;
	PaymentBean findByID(String userId,String id) throws SQLException;
	//ArrayList<PaymentBean> findAll() throws SQLException;
	
}
