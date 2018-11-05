package com.ntl.srs.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ntl.srs.bean.ShipBean;

public interface ShipBeanDao {
	
	String createShipBean(ShipBean shipBean) throws SQLException;
	int deleteShipBean(ArrayList<String> al );
	boolean updateShipBean(ShipBean shipBean) throws SQLException ;
	ShipBean findByID(String id) throws SQLException;
	ArrayList<ShipBean> findAll() throws SQLException;
	
}
