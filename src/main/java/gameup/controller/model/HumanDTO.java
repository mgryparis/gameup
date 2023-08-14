package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;
import gameup.entity.Gamer;
import gameup.entity.Human;
import gameup.entity.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

//HumanDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class HumanDTO {
	private Long humanId;
	private String humanFirstName;
	private String humanLastName;
	private String humanEmail;
	private String humanPhone;
	private String humanNote;
	private Set<GamerDTO> gamerIdentities = new HashSet<>();
	private Set<LocationDTO> locationsHosting = new HashSet<>();
	
	//  Constructor that generates a HumanDTO instance from a Human Entity instance
	public HumanDTO(Human human) {
		this.humanId 			= human.getHumanId();
		this.humanFirstName 	= human.getHumanFirstName();
		this.humanLastName 		= human.getHumanLastName();
		this.humanEmail 		= human.getHumanEmail();
		this.humanPhone 		= human.getHumanPhone();
		this.humanNote 			= human.getHumanNote();
		for(Gamer gamer : human.getGamerIdentities())	{
			this.gamerIdentities.add(new GamerDTO(gamer));			}
		for(Location location : human.getLocationsHosting())	{
			this.locationsHosting.add(new LocationDTO(location));	}	}
	
	//  Method on HumanDTO that returns the corresponding Location Entity instance
	public Human toHuman()	{
		Human human = new Human();
		human.setHumanId(humanId);
		human.setHumanFirstName(humanFirstName);
		human.setHumanLastName(humanLastName);
		human.setHumanEmail(humanEmail);
		human.setHumanPhone(humanPhone);
		human.setHumanNote(humanNote);
		for(GamerDTO gamerDTO : gamerIdentities) {
			human.getGamerIdentities().add(gamerDTO.toGamer());		}
		for(LocationDTO locationDTO : locationsHosting) {
			human.getLocationsHosting().add(locationDTO.toLocation());		}	
		return human;}

}	//  End of HumanDTO Class
