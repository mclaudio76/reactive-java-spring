package mclaudio76.springreactivedemo.springwebflux;

import static reactor.core.scheduler.Schedulers.elastic;

import java.time.Instant;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class BookingFlightService {

	
	public Reservation bookFlight(int passengerID, int flightID) {
		Passenger p = findPassenger(passengerID);
		Flight    f = findFlight(flightID);
		return book(p,f); 
	}
	
	public Reservation rBookFlight(int passengerID, int flightID) {
		Flux<Passenger> rPassenger = Flux.defer(() -> Flux.just(findPassenger(passengerID))).subscribeOn(elastic());
		Flux<Flight>    rFlight    = Flux.defer(() -> Flux.just(findFlight((flightID)))).subscribeOn(elastic());
		return 			rPassenger.zipWith(rFlight, (p, f) -> book(p,f)).blockFirst();
	}
	
	
	private Reservation book(Passenger p, Flight f) {
		sleep(2);
		log(" Booked  flight "+f+" to passenger "+p);
		return new Reservation("Reservation = "+f+p);
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
