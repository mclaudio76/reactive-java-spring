package mclaudio76.springreactivedemo.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import mclaudio76.springreactivedemo.basicproviderconsumer.Observation;

public class CustomTransformer implements Processor<Observation, Observation>{
	
	private SubmissionPublisher<Observation>  publisher = new SubmissionPublisher<>();
	private Subscription subscription  = null;
	private List<Subscriber<? super Observation>> subscribers = new ArrayList<>();
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(100);
	}

	@Override
	public void onNext(Observation item) {
		System.out.println("[Producer] Applying transformation");
		item.setNumericValue(Math.abs(item.getNumericValue()));
		publisher.submit(item);
		subscription.request(100);
	}

	@Override
	public void onError(Throwable throwable) {
		subscribers.stream().forEach(x -> x.onError(throwable));
	}

	@Override
	public void onComplete() {
		subscribers.forEach(Subscriber::onComplete);
	}

	@Override
	public void subscribe(Subscriber<? super Observation> subscriber) {
		publisher.subscribe(subscriber);
		subscribers.add(subscriber);
	}

	
}
