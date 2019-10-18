package mclaudio76.springreactivedemo.providerconsumer;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class ObservationProducer {
	
	private List<ObservationConsumer> consumers  = new ArrayList<>();
	private List<Observation> data	= new ArrayList<>();
	
	public void addConsumer(ObservationConsumer consumer) {
		consumers.add(consumer);
	}
	
	public void registerObservation(Observation obs) {
		data.add(obs);
	}
	
	public void notifyObservers() {
		SubmissionPublisher<Observation>  actualPublisher = new SubmissionPublisher<>();
		consumers.stream().forEach(actualPublisher::subscribe);
		data.stream().forEach(actualPublisher::submit);
		actualPublisher.close();
	}

}
