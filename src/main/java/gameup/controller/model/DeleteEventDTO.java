package gameup.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

//Event-to-GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class DeleteEventDTO {
	private Long eventId;
	private String eventCheckResult;
	private String gamersRegisteredFor_String;
	private String gamesIncludedIn_String;
	private String locationsScheduledAt_String;
	private String goNoGoResult;
	private String gamersRegisteredFor_RowsDeleted_String;
	private String gamesIncludedIn_RowsDeleted_String;
	private String locationsScheduledAt_RowsDeleted_String;
	private String event_RowsDeleted_String;
	
//  Constructor that generates a DeleteEventDTO from an Event ID
	public DeleteEventDTO(Long eventId)	{
		this.eventId	= eventId;			}

}	//  End of DeleteEventDTO Class
