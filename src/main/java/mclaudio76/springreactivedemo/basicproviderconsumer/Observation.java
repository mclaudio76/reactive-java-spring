package mclaudio76.springreactivedemo.basicproviderconsumer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Observation {

	private static int OBSERVATION_COUNTER = 0;
	
	@Getter
	@Setter
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
