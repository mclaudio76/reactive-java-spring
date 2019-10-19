package mclaudio76.springreactivedemo.springwebflux;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Passenger {
	
	@Getter @Setter
	@EqualsAndHashCode.Include
	private int ID;
	
	@Getter @Setter
	private String name;
	
	public Passenger(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	
}
