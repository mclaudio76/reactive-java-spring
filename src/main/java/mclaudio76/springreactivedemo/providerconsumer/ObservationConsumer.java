package mclaudio76.springreactivedemo.providerconsumer;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class ObservationConsumer implements Subscriber<Observation>{
	Subscription subscription = null;
	private int maxItems 	  = 0;
	
	public ObservationConsumer(int maxItems) {
		this.maxItems = maxItems;
	}
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(maxItems);
	}

	@Override
	public void onNext(Observation item) {
		System.out.println("Got"+item);
		subscription.request(maxItems);
	}

	@Override
	public void onError(Throwable throwable) {
		System.err.println(" An error is been raised ");
		
	}

	@Override
	public void onComplete() {
		System.out.println(" The producer has finished ");
	}

}
