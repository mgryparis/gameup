package gameup.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

//Event-to-GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class Human2LocationDTO {
	private Long humanId;
	private Long locationId;
	private String humanCheckResult;
	private String locationCheckResult;
	private String goNoGoResult;
	
	public Human2LocationDTO(Long humanId, Long locationId)	{
		this.humanId	= humanId;
		this.locationId	= locationId;						}

}	//  End of Human2LocationDTO Class
