package mclaudio76.springreactivedemo;

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
		producer.addConsumer(new ObservationConsumer(10));
		producer.addConsumer(new ObservationConsumer(1));
	}
	
	
	@PostMapping(path = "/publish") 
	public void doPublish() {
		producer.registerObservation(Observation.createObservation(20));
	}
	
	
}
