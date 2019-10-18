package mclaudio76.springreactivedemo.providerconsumer;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Observation {

	private static int OBSERVATION_COUNTER = 0;
	
	@Getter
	private int        numericValue		   = 0;
	
	@EqualsAndHashCode.Include
	private int        observationID 	   = 0;
	
	public static Observation createObservation(int numericValue) {
		Observation obs = new Observation();
		obs.observationID = ++OBSERVATION_COUNTER;
		obs.numericValue  = numericValue;
		return obs;
	}
	
	public String toString() {
		return "Obs# "+observationID+" = ["+numericValue+"]";
	}
	
	
	
}
