package com.ntl.srs.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ntl.srs.bean.ScheduleBean;


public interface ScheduleBeanDao {


	String createScheduleBean(ScheduleBean scheduleBean) throws SQLException;
	int deleteScheduleBean(ArrayList<String> al ) throws SQLException;
	boolean updateScheduleBean(ScheduleBean scheduleBean) throws SQLException ;
	ScheduleBean findByID(String id) throws SQLException;
	ArrayList<ScheduleBean> findAll() throws SQLException;
	
}
