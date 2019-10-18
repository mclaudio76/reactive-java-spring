package mclaudio76.springreactivedemo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mclaudio76.springreactivedemo.providerconsumer.Observation;
import mclaudio76.springreactivedemo.providerconsumer.ObservationConsumer;
import mclaudio76.springreactivedemo.providerconsumer.ObservationProducer;

@RestController
public class NotifierEndpoint {
	
	@Autowired
	private ObservationProducer producer;
	
	@Autowired
	private void setConsumer1() {
		producer.addConsumer(new ObservationConsumer("Consumer 1",1, 5));
	}
	
	
	@PostMapping(path = "/publish") 
	public void doPublish() {
		for(int x = 0; x < 10; x++) {
			producer.registerObservation(Observation.createObservation(new Random().nextInt()));
		}
		producer.notifyObservers();
	}
	
	
}