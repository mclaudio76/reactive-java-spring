package mclaudio76.springreactivedemo.providerconsumer;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class ObservationProducer {
	 
	private SubmissionPublisher<Observation>  actualPublisher = new SubmissionPublisher<Observation>();
	private List<ObservationConsumer> consumers  = new ArrayList<>();
	
	public void addConsumer(ObservationConsumer consumer) {
		consumers.add(consumer);
		actualPublisher.subscribe(consumer);
	}
	
	public void registerObservation(Observation obs) {
		for(ObservationConsumer consumer : consumers) {
			consumer.onNext(obs);
		}
	}

}
