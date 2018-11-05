package com.ntl.srs.bean;

public class ShipBean {

	private String shipID;
	private String shipName;
	private int seatingCapacity;
	private int reservationCapacity;
	
	
	public ShipBean() {
		super();
	}
	

	public ShipBean(String shipID, String shipName, int seatingCapacity, int reservationCapacity) {
		super();
		this.shipID = shipID;
		this.shipName = shipName;
		this.seatingCapacity = seatingCapacity;
		this.reservationCapacity = reservationCapacity;
	}



	public ShipBean(String shipName, int seatingCapacity, int reservationCapacity) {
		super();
		this.shipName = shipName;
		this.seatingCapacity = seatingCapacity;
		this.reservationCapacity = reservationCapacity;
	}


	public String getShipID() {
		return shipID;
	}
	public void setShipID(String shipID) {
		this.shipID = shipID;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public int getReservationCapacity() {
		return reservationCapacity;
	}
	public void setReservationCapacity(int reservationCapacity) {
		this.reservationCapacity = reservationCapacity;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShipBean [shipID=" + shipID + ", shipName=" + shipName + "]";
	}
	
	
	
}
