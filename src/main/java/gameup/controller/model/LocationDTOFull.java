package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;
import gameup.entity.Human;
import gameup.entity.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

//LocationDTOFull Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class LocationDTOFull {
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
	
	public LocationDTOFull(
			Location location,
			Human humanPoc,
			Set<GamerDTO> gamersHosting,
			Set<EventDTO> eventsScheduledAt)	{
		this.locationId					= location.getLocationId();
		this.locationName				= location.getLocationName();
		this.locationStreetaddress		= location.getLocationStreetaddress();
		this.locationCity				= location.getLocationCity();
		this.locationState				= location.getLocationState();
		this.locationZip				= location.getLocationZip();
		this.locationPhone				= location.getLocationPhone();
		this.locationNote				= location.getLocationNote();
		this.humanPoc					= humanPoc;
		this.gamersHosting				= gamersHosting;
		this.eventsScheduledAt			= eventsScheduledAt;					}
 
}	//  End of LocationDTOFull Class
