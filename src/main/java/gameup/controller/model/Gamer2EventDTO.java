package gameup.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

//Event-to-GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class Gamer2EventDTO {
	private Long gamerId;
	private Long eventId;
	private String gamerCheckResult;
	private String eventCheckResult;
	private String goNoGoResult;
	
	public Gamer2EventDTO(Long gamerId, Long eventId)	{
		this.gamerId	= gamerId;
		this.eventId	= eventId;						}

}	//  End of Gamer2EventDTO Class
