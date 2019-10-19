package mclaudio76.springreactivedemo.springwebflux;

public class Reservation {
	
	public String reservationID;
	
	public Reservation(String reservationID) {
		this.reservationID = reservationID;
	}
	
	public Reservation update() {
		return this;
	}
	
	@Override
	public String toString() {
		return this.reservationID;
	}
}
