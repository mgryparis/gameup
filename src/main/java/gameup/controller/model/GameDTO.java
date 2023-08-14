package gameup.controller.model;

import java.util.HashSet;
import java.util.Set;
import gameup.entity.Event;
import gameup.entity.Game;
import gameup.entity.Gamer;

//  GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
public class GameDTO {
	private Long gameId;
	private String gameName;
	private String gameUrl;
	private String gameNote;
	private Set<GamerDTO> gamersInterestedIn = new HashSet<>();
	private Set<EventDTO> eventsPlayingAt = new HashSet<>();

	//  Constructor that generates a GameDTO instance from a Location Entity instance
	public GameDTO(Game game)	{
		this.gameId		=	game.getGameId();
		this.gameName	=	game.getGameName();
		this.gameUrl	=	game.getGameUrl();
		this.gameNote	=	game.getGameNote();
		for(Gamer gamer : game.getGamersInterestedIn())	{
			this.gamersInterestedIn.add(new GamerDTO(gamer));		}
		for(Event event : game.getEventsPlayingAt())	{
			this.eventsPlayingAt.add(new EventDTO(event));			}	}

	//  Method on GameDTO that returns the corresponding Game Entity instance
	public Game toGame()	{
		Game game = new Game();
		game.setGameId(gameId);
		game.setGameName(gameName);
		game.setGameUrl(gameUrl);
		game.setGameNote(gameNote);
		for(GamerDTO gamerDTO : gamersInterestedIn)	{
			game.getGamersInterestedIn().add(gamerDTO.toGamer());	}
		for(EventDTO eventDTO : eventsPlayingAt)	{
			game.getEventsPlayingAt().add(eventDTO.toEvent());		}	
		return game;												}

}	//  End of GameDTO Class
