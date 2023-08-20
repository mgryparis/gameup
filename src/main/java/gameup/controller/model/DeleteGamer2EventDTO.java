package gameup.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteGamer2EventDTO {
	private Long eventId;
	private Long gamerId;
//	private String eventCheckResult;
//	private String gamerCheckResult;
	private String goNoGoResult;
	private String rowsDeleted_string;
	private String opResult;
	
	public DeleteGamer2EventDTO(Long eventId, Long gamerId)	{
		this.eventId	= eventId;
		this.gamerId	= gamerId;					}
	
}	//  End of DeleteGamer2EventDTO Class
