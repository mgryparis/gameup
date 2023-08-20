package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;

import gameup.entity.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

//EventDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class EventDTOFull {
private Long eventId;
private String eventName;
private String eventLevel;
private String eventDate;
private String eventStarttime;
private String eventEndtime;
private Set<GamerDTO> gamersRegisteredFor = new HashSet<>();
private Set<GameDTO> gamesIncludedIn = new HashSet<>();
private Set<LocationDTO> locationsScheduledAt = new HashSet<>();

//  Constructor that generates a EventDTOFull instance from Event + Sets for related Entities
	public EventDTOFull(Event event, 
			Set<GameDTO> gamesIncludedIn, 
			Set<GamerDTO> gamersRegisteredFor, 
			Set<LocationDTO> locationsScheduledAt)	{
		this.eventId 				= event.getEventId();
		this.eventName				= event.getEventName();
		this.eventLevel 			= event.getEventLevel();
		this.eventDate 				= event.getEventDate();
		this.eventStarttime 		= event.getEventStarttime();
		this.eventEndtime 			= event.getEventEndtime();			
		this.gamesIncludedIn		= gamesIncludedIn;
		this.gamersRegisteredFor	= gamersRegisteredFor;
		this.locationsScheduledAt	= locationsScheduledAt;			}

}	//  End of EventDTOFull Class