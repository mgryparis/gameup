package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;

import gameup.entity.Game;
import lombok.Data;
import lombok.NoArgsConstructor;

//GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class GameDTOFull {
	private Long gameId;
	private String gameName;
	private String gameUrl;
	private String gameNote;
	private Set<GamerDTO> gamersInterestedIn = new HashSet<>();
	private Set<EventDTO> eventsPlayingAt = new HashSet<>();
	
//  Constructor that generates a GameDTOFull instance from Game DTO + Sets for related Entities
	public GameDTOFull(
			Game game, 
			Set<GamerDTO> gamersInterestedIn,
			Set<EventDTO> eventsPlayingAt)	{
		this.gameId		=	game.getGameId();
		this.gameName	=	game.getGameName();
		this.gameUrl	=	game.getGameUrl();
		this.gameNote	=	game.getGameNote();
		this.gamersInterestedIn	= gamersInterestedIn;
		this.eventsPlayingAt	= eventsPlayingAt;				}

}	//  End of GameDTOFull Class
