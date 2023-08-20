package gameup.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

//Event-to-GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class Event2LocationDTO {
	private Long eventId;
	private Long locationId;
	private String eventCheckResult;
	private String locationCheckResult;
	private String goNoGoResult;
	
	public Event2LocationDTO(Long eventId, Long locationId)	{
		this.eventId	= eventId;
		this.locationId	= locationId;				}

}	//  End of Event2LocationDTO Class
