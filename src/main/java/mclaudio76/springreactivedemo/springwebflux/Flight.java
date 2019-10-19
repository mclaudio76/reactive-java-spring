package mclaudio76.springreactivedemo.springwebflux;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(of = {"ID"})
public class Flight {
	
	@Getter
	private int ID;
	
	@Getter @Setter
	private String description;
	
	public Flight(int ID, String description) {
		this.ID = ID;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
}
