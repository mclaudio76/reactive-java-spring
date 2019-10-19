package mclaudio76.springreactivedemo.springwebflux;

import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class BookingFlightService {

	
	public void bookFlight(int passengerID, int flightID) {
		Passenger p = findPassenger(passengerID);
		Flight    f = findFlight(flightID);
		book(p,f); 
	}
	
	
	private void book(Passenger p, Flight f) {
		sleep(2);
		log(" Booked  flight "+f+" to passenger "+p);
	}


	private Passenger findPassenger(int ID) {
		sleep(2);
		log("Found passenger ");
		return new Passenger(ID, "John Doe");
	}
	
	private Flight findFlight(int ID) {
		sleep(3);
		log("Found flight ");
		return new Flight(ID, "347 Milan - London");
	}
	
	
	
	// simulates time required to execute a routine
	private void sleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
		}
		catch(Exception e) {
			
		}
	}

	private void log(String txt) {
		System.out.println(Instant.now()+" >> "+txt);
	}
	
}
