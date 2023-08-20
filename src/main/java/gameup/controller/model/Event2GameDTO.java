package gameup.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

//Event-to-GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class Event2GameDTO {
	private Long eventId;
	private Long gameId;
	private String eventCheckResult;
	private String gameCheckResult;
	private String goNoGoResult;
	
	public Event2GameDTO(Long eventId, Long gameId)	{
		this.eventId	= eventId;
		this.gameId		= gameId;					}

}	//  End of Event2GameDTO Class
