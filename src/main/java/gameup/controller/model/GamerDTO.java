package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;
import gameup.entity.Event;
import gameup.entity.Game;
import gameup.entity.Gamer;
import gameup.entity.Human;
import gameup.entity.Location;
import lombok.Data;
import lombok.NoArgsConstructor;

//  GamerDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class GamerDTO {
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
	
	//  Constructor that generates a GamerDTO instance from a Gamer Entity instance
	public GamerDTO(Gamer gamer) {
		this.gamerId 		= gamer.getGamerId();
		this.gamerHandle 	= gamer.getGamerHandle();
		this.gamerEmail 	= gamer.getGamerEmail();
		this.gamerDiscord 	= gamer.getGamerDiscord();
		this.gamerUrl 		= gamer.getGamerUrl();
		this.gamerNote 		= gamer.getGamerNote();				}
//		this.humanIdentity 	= gamer.getHumanIdentity();					}
//		for(Game game : gamer.getGamesInterestedIn())	{
//			this.gamesInterestedIn.add(new GameDTO(game));				}	
//		for(Event event : gamer.getEventsRegisteredFor())	{
//			this.eventsRegisteredFor.add(new EventDTO(event));			}
//		for(Location location : gamer.getLocationsHostingFor())	{
//			this.locationsHostingFor.add(new LocationDTO(location));	}	}
	
	//  Method on GamerDTO that returns the corresponding Gamer Entity instance
	public Gamer toGamer()	{
		Gamer gamer = new Gamer();
		gamer.setGamerId(gamerId);
		gamer.setGamerHandle(gamerHandle);
		gamer.setGamerEmail(gamerEmail);
		gamer.setGamerDiscord(gamerDiscord);
		gamer.setGamerUrl(gamerUrl);
		gamer.setGamerNote(gamerNote);
//		gamer.setHumanIdentity(humanIdentity);
//		for(GameDTO gameDTO : gamesInterestedIn)	{
//			gamer.getGamesInterestedIn().add(gameDTO.toGame());				}
//		for(EventDTO eventDTO : eventsRegisteredFor)	{
//			gamer.getEventsRegisteredFor().add(eventDTO.toEvent());			}
//		for(LocationDTO locationDTO : locationsHostingFor)	{
//			gamer.getLocationsHostingFor().add(locationDTO.toLocation());	}	
		return gamer;														}
	
}	//  End of GamerDTO Class
