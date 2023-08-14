package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;
import gameup.entity.Event;
import gameup.entity.Gamer;
import gameup.entity.Human;
import gameup.entity.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

//LocationDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class LocationDTO {
	private Long locationId;
	private String locationName;
	private String locationStreetaddress;
	private String locationCity;
	private String locationState;
	private String locationZip;
	private String locationPhone;
	private String locationNote;
	private Human humanPoc;		
	private Set<GamerDTO> gamersHosting = new HashSet<>();	
	private Set<EventDTO> eventsScheduledAt = new HashSet<>();
	
	//  Constructor that generates a LocationDTO instance from a Location Entity instance
	public LocationDTO(Location location)	{
		this.locationId 			= location.getLocationId();
		this.locationName 			= location.getLocationName();
		this.locationStreetaddress 	= location.getLocationStreetaddress();
		this.locationCity 			= location.getLocationCity();
		this.locationState 			= location.getLocationState();
		this.locationZip 			= location.getLocationZip();
		this.locationPhone 			= location.getLocationPhone();
		this.locationNote 			= location.getLocationNote();
		this.humanPoc 				= location.getHumanPoc();
		for(Gamer gamer : location.getGamersHosting())	{
			this.gamersHosting.add(new GamerDTO(gamer));				}
		for(Event event : location.getEventsScheduledAt())	{
					this.eventsScheduledAt.add(new EventDTO(event));	}	}

	//  Method on LocationDTO that returns the corresponding Location Entity instance
	public Location toLocation() 	{
		Location location = new Location();
		location.setLocationId(locationId);
		location.setLocationName(locationName);
		location.setLocationStreetaddress(locationStreetaddress);
		location.setLocationCity(locationCity);
		location.setLocationState(locationState);
		location.setLocationZip(locationZip);
		location.setLocationPhone(locationPhone);
		location.setLocationNote(locationNote);
		location.setHumanPoc(humanPoc);
		for(GamerDTO gamerDTO : gamersHosting)	{
			location.getGamersHosting().add(gamerDTO.toGamer());		}
		for(EventDTO eventDTO : eventsScheduledAt)	{
			location.getEventsScheduledAt().add(eventDTO.toEvent());	}
		return location;												}

}  // End of LocationDTO Class
