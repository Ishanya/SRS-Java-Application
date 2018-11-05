package com.ntl.srs.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ntl.srs.bean.ReservationBean;

public interface ReservationBeanDao {


	String createReservationBean(ReservationBean reservationBean) throws SQLException ;
	int deleteReservationBean(ArrayList<String> al );
	boolean updateReservationBean(ReservationBean reservationBean);
	ReservationBean findByID(String id) throws SQLException;
	ArrayList<ReservationBean> findAll();
	
}
