package com.ntl.srs.bean;

import java.time.LocalDate;

public class ScheduleBean {

	private String scheduleID;
	private String routeID;
	private String shipID;
	private LocalDate startDate;
	
	
	
	
	public ScheduleBean(String routeID, String shipID, LocalDate startDate) {
		super();
		this.routeID = routeID;
		this.shipID = shipID;
		this.startDate = startDate;
	}



	/**
	 * @param scheduleID
	 * @param routeID
	 * @param shipID
	 */
	public ScheduleBean(String scheduleID, String routeID, String shipID) {
		super();
		this.scheduleID = scheduleID;
		this.routeID = routeID;
		this.shipID = shipID;
	}



	public ScheduleBean() {
		super();
	}
	
	
	
	public ScheduleBean(String scheduleID, String routeID, String shipID, LocalDate startDate) {
		super();
		this.scheduleID = scheduleID;
		this.routeID = routeID;
		this.shipID = shipID;
		this.startDate = startDate;
	}



	public String getScheduleID() {
		return scheduleID;
	}
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}
	public String getRouteID() {
		return routeID;
	}
	public void setRouteID(String routeID) {
		this.routeID = routeID;
	}
	public String getShipID() {
		return shipID;
	}
	public void setShipID(String shipID) {
		this.shipID = shipID;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScheduleBean [scheduleID=" + scheduleID + ", routeID=" + routeID + ", shipID=" + shipID + ", startDate="
				+ startDate + "]";
	}
	
	
	
	
}
