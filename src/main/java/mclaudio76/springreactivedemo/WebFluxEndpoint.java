package mclaudio76.springreactivedemo;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mclaudio76.springreactivedemo.springwebflux.BookingFlightService;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import static java.lang.System.*;

@RestController
public class WebFluxEndpoint {
	
	@Autowired
	BookingFlightService fService;
	
	private String[] fruits = {"Apple", "Orange", "Grape", "Banana", "Strawberry"};
	
	@GetMapping(path = "/flux")
	public void usingFlux() {
		 Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry").subscribe(System.out::println);
		 Flux.fromArray(fruits);
		 Flux<String> characterFlux = Flux
			      .just("Donald Duck", "Mickey Mouse", "Goofy")
			      .delayElements(Duration.ofMillis(500));
		 Flux<String> foodFlux = Flux
		      .fromArray(fruits)
		      .delaySubscription(Duration.ofMillis(250))
		      .delayElements(Duration.ofMillis(500));
         Flux<Tuple2<String, String>> zipped = characterFlux.zipWith(foodFlux);
         zipped.subscribe(out::println);
	}
	
	@GetMapping(path="/booking")
	public void booking() {
		fService.bookFlight(10, 20);
	}
	
	
	
	@GetMapping(path = "/fluxget", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<String> usingFluxForGet() {
		 return Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");
	}
	
}
