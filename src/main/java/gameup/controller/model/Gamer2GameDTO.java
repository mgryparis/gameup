package gameup.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

//Event-to-GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class Gamer2GameDTO {
	private Long gamerId;
	private Long gameId;
	private String gamerCheckResult;
	private String gameCheckResult;
	private String goNoGoResult;
	
	public Gamer2GameDTO(Long gamerId, Long gameId)	{
		this.gamerId	= gamerId;
		this.gameId		= gameId;						}

}	//  End of Gamer2GameDTO Class
