package gameup.controller.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

//Event-to-GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class DeleteEventDTO {
	private Long eventId;
	private String eventCheckResult;
	private List<String> gamersRegisteredFor_String;
	private List<String> gamesIncludedIn_String;
	private List<String> locationsScheduledAt_String;
	private String goNoGoResult;
	
//  Constructor that generates a DeleteEventDTO from an Event ID
	public DeleteEventDTO(Long eventId)	{
		this.eventId	= eventId;			}

}	//  End of DeleteEventDTO Class
