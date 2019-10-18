package mclaudio76.springreactivedemo.providerconsumer;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class ObservationConsumer implements Subscriber<Observation>{
	Subscription subscription = null;
	private int maxItems 	  = 0;
	private String observerID = "";
	private int msecSleepTime = 0;
	private int received	  = 0;
	
	public ObservationConsumer(String observerID, int maxItems, int msecSleepTime) {
		this.maxItems    = maxItems;
		this.observerID = observerID;
		this.msecSleepTime = msecSleepTime;
	}
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(maxItems);
	}

	@Override
	public void onNext(Observation item) {
		log(item.toString());
		try {
			Thread.sleep(msecSleepTime);
		}
		catch(Exception e) {
			
		}
		received++;
		subscription.request(maxItems);
	}

	@Override
	public void onError(Throwable throwable) {
		log("An error occurred");
		
	}

	@Override
	public void onComplete() {
		log("All data sent by publisher , received "+received);
	}
	
	
	private void log(String mex) {
		System.out.println("["+observerID+"] >> "+mex);
	}

}
