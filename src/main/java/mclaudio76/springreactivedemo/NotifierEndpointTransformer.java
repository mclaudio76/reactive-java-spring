package mclaudio76.springreactivedemo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mclaudio76.springreactivedemo.basicproviderconsumer.Observation;
import mclaudio76.springreactivedemo.basicproviderconsumer.ObservationConsumer;
import mclaudio76.springreactivedemo.basicproviderconsumer.ObservationProducer;
import mclaudio76.springreactivedemo.transformer.CustomTransformer;

@RestController
public class NotifierEndpointTransformer {

	private ObservationProducer producer;
	private CustomTransformer transformer;
	
	
	@Autowired
	public NotifierEndpointTransformer() {
		producer = new ObservationProducer();
		transformer = new CustomTransformer();
		producer.subscribe(transformer);
		transformer.subscribe(new ObservationConsumer("Consumer 1",1, 5));
		transformer.subscribe(new ObservationConsumer("Consumer 2",1, 10));
		transformer.subscribe(new ObservationConsumer("Consumer 3",1, 15));
	}
	
	
	@PostMapping(path = "/publishtrans") 
	public void doPublish() {
		for(int x = 0; x < 10; x++) {
			producer.registerObservation(Observation.createObservation(new Random().nextInt()));
		}
		producer.notifyObservers();
	}
}
