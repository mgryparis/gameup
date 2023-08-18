package gameup.controller.model;

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
	
	//  Constructor that generates a LocationDTO instance from a Location Entity instance
	public LocationDTO(Location location)	{
		this.locationId 			= location.getLocationId();
		this.locationName 			= location.getLocationName();
		this.locationStreetaddress 	= location.getLocationStreetaddress();
		this.locationCity 			= location.getLocationCity();
		this.locationState 			= location.getLocationState();
		this.locationZip 			= location.getLocationZip();
		this.locationPhone 			= location.getLocationPhone();
		this.locationNote 			= location.getLocationNote();			}

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
		return location;													}

}  // End of LocationDTO Class
