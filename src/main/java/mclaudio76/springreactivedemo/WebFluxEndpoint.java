package mclaudio76.springreactivedemo;

import java.time.Duration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;
import static java.lang.System.*;

@RestController
public class WebFluxEndpoint {
	private String[] fruits = {"Apple", "Orange", "Grape", "Banana", "Strawberry"};
	
	@GetMapping(path = "/flux")
	public void usingFlux() {
		 Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry").subscribe(System.out::println);
		 Flux.fromArray(fruits);
		
		 Flux<String> characterFlux = Flux
			      .just("Garfield", "Kojak", "Barbossa")
			      .delayElements(Duration.ofMillis(500));
		 Flux<String> foodFlux = Flux
		      .fromArray(fruits)
		      .delaySubscription(Duration.ofMillis(250))
		      .delayElements(Duration.ofMillis(500));
         Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);
     
         
         Flux<Tuple2<String, String>> zipped = characterFlux.zipWith(foodFlux);
         zipped.subscribe(out::println);
       
	}
	
	
	
	@GetMapping(path = "/fluxget", produces = MediaType.APPLICATION_JSON_VALUE)
	public Flux<String> usingFluxForGet() {
		 
		 return Flux.just("Apple", "Orange", "Grape", "Banana", "Strawberry");
	}
	
}
