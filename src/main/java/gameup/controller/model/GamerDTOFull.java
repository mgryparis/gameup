package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;

import gameup.entity.Gamer;
import gameup.entity.Human;
import lombok.Data;
import lombok.NoArgsConstructor;

//GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class GamerDTOFull {
	private Long gamerId;
	private String gamerHandle;
	private String gamerEmail;
	private String gamerDiscord;
	private String gamerUrl;
	private String gamerNote;
	private Human humanIdentity;
	private Set<GameDTO> gamesInterestedIn = new HashSet<>();
	private Set<EventDTO> eventsRegisteredFor = new HashSet<>();
	private Set<LocationDTO> locationsHostingFor = new HashSet<>();
	
	public GamerDTOFull(
			Gamer gamer, 
			Human humanIdentity,
			Set<GameDTO> gamersInterestedIn,
			Set<EventDTO> eventsRegisteredFor,
			Set<LocationDTO> locationsHostingFor)	{
		this.gamerId				= gamer.getGamerId();
		this.gamerHandle			= gamer.getGamerHandle();
		this.gamerEmail				= gamer.getGamerEmail();
		this.gamerDiscord			= gamer.getGamerDiscord();
		this.gamerUrl				= gamer.getGamerUrl();
		this.gamerNote				= gamer.getGamerNote();
		this.humanIdentity			= humanIdentity;
		this.gamesInterestedIn		= gamersInterestedIn;
		this.eventsRegisteredFor	= eventsRegisteredFor;				
		this.locationsHostingFor	= locationsHostingFor;			}

}	//  End of GamerDTOFull Class
