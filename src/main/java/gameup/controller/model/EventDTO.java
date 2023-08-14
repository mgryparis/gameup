package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;
import gameup.entity.Event;
import gameup.entity.Game;
import gameup.entity.Gamer;
import gameup.entity.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

//  EventDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class EventDTO {
	private Long eventId;
	private String eventName;
	private String eventLevel;
	private String eventDate;
	private String eventStarttime;
	private String eventEndtime;
	private Set<GamerDTO> gamersRegisteredFor = new HashSet<>();
	private Set<GameDTO> gamesIncludedIn = new HashSet<>();
	private Set<LocationDTO> locationsScheduledAt = new HashSet<>();

	//  Constructor that generates a EventDTO instance from a Event Entity instance
	public EventDTO(Event event)	{
		this.eventId 			= event.getEventId();
		this.eventName			= event.getEventName();
		this.eventLevel 		= event.getEventLevel();
		this.eventDate 			= event.getEventDate();
		this.eventStarttime 	= event.getEventStarttime();
		this.eventEndtime 		= event.getEventEndtime();
		for(Gamer gamer : event.getGamersRegisteredFor())	{
			this.gamersRegisteredFor.add(new GamerDTO(gamer));			}
		for(Game game : event.getGamesIncludedIn())	{
			this.gamesIncludedIn.add(new GameDTO(game));				}
		for(Location location : event.getLocationsScheduledAt())	{
			this.locationsScheduledAt.add(new LocationDTO(location));	}	}
	
	//  Method on EventDTO that returns the corresponding Event Entity instance
	public Event toEvent()	{
		Event event = new Event();
		event.setEventId(eventId);
		event.setEventName(eventName);
		event.setEventLevel(eventLevel);
		event.setEventDate(eventDate);
		event.setEventStarttime(eventStarttime);
		event.setEventEndtime(eventEndtime);
		for(GamerDTO gamerDTO : gamersRegisteredFor)	{
			event.getGamersRegisteredFor().add(gamerDTO.toGamer());			}
		for(GameDTO gameDTO : gamesIncludedIn)	{
			event.getGamesIncludedIn().add(gameDTO.toGame());				}
		for(LocationDTO locationDTO : locationsScheduledAt)	{
			event.getLocationsScheduledAt().add(locationDTO.toLocation());	}	
		return event;														}
	
}	//  End of EventDTO Class
