package mclaudio76.springreactivedemo;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mclaudio76.springreactivedemo.providerconsumer.Observation;
import mclaudio76.springreactivedemo.providerconsumer.ObservationConsumer;
import mclaudio76.springreactivedemo.providerconsumer.ObservationProducer;

@RestController
public class NotifierService {
	
	private ObservationProducer producer;
	
	
	@PostConstruct
	public void initialize() {
		producer = new ObservationProducer();
		producer.addConsumer(new ObservationConsumer("Consumer 1",1, 5));
		producer.addConsumer(new ObservationConsumer("Consumer 2",2, 10));
		producer.addConsumer(new ObservationConsumer("Consumer 3",3, 20));
	}
	
	
	@PostMapping(path = "/publish") 
	public void doPublish() {
		for(int x = 0; x < 10; x++) {
			producer.registerObservation(Observation.createObservation(new Random().nextInt()));
		}
		producer.notifyObservers();
	}
	
	
}
