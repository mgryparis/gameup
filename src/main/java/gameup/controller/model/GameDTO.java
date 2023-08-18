package gameup.controller.model;

import gameup.entity.Game;
import lombok.Data;
import lombok.NoArgsConstructor;

//  GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class GameDTO {
	private Long gameId;
	private String gameName;
	private String gameUrl;
	private String gameNote;

	//  Constructor that generates a GameDTO instance from a Location Entity instance
	public GameDTO(Game game)	{
		this.gameId		=	game.getGameId();
		this.gameName	=	game.getGameName();
		this.gameUrl	=	game.getGameUrl();
		this.gameNote	=	game.getGameNote();						}

	//  Method on GameDTO that returns the corresponding Game Entity instance
	public Game toGame()	{
		Game game = new Game();
		game.setGameId(gameId);
		game.setGameName(gameName);
		game.setGameUrl(gameUrl);
		game.setGameNote(gameNote);
		return game;												}

}	//  End of GameDTO Class
