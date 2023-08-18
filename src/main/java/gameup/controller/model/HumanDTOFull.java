package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;

import gameup.entity.Human;
import lombok.Data;
import lombok.NoArgsConstructor;

//HumanDTOFull Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class HumanDTOFull {
	private Long humanId;
	private String humanFirstname;
	private String humanLastname;
	private String humanEmail;
	private String humanPhone;
	private String humanNote;
	private Set<GamerDTO> gamerIdentities = new HashSet<>();
	private Set<LocationDTO> locationsHosting = new HashSet<>();

	public HumanDTOFull(
			Human human,
			Set<GamerDTO> gamerIdentities,
			Set<LocationDTO> locationsHosting)	{
		this.humanId					= human.getHumanId();
		this.humanFirstname				= human.getHumanFirstname();
		this.humanLastname				= human.getHumanLastname();
		this.humanEmail					= human.getHumanEmail();
		this.humanPhone					= human.getHumanPhone();
		this.humanNote					= human.getHumanNote();
		this.gamerIdentities			= gamerIdentities;
		this.locationsHosting			= locationsHosting;				}

}	//  End of HumanDTOFull Class
