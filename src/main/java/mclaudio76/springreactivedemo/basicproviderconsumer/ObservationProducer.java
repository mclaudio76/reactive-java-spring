package mclaudio76.springreactivedemo.basicproviderconsumer;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;
import org.springframework.stereotype.Component;

@Component
public class ObservationProducer implements Publisher<Observation>{
	
	private List<Subscriber<Observation>> consumers  = new ArrayList<>();
	private List<Observation> data	= new ArrayList<>();
	
	public void notifyObservers() {
		SubmissionPublisher<Observation>  actualPublisher = new SubmissionPublisher<>();
		consumers.stream().forEach(actualPublisher::subscribe);
		data.stream().forEach(actualPublisher::submit);
		actualPublisher.close();
	}

	@Override
	public void subscribe(Subscriber<? super Observation> consumer) {
		consumers.add( (Subscriber<Observation>) consumer);
	}

	public void registerObservation(Observation obs) {
		data.add(obs);
	}
	
}
