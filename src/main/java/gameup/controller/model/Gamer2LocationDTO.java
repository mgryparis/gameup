package gameup.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

//Event-to-GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class Gamer2LocationDTO {
	private Long gamerId;
	private Long locationId;
	private String gamerCheckResult;
	private String locationCheckResult;
	private String goNoGoResult;
	
	public Gamer2LocationDTO(Long gamerId, Long locationId)	{
		this.gamerId	= gamerId;
		this.locationId	= locationId;						}

}	//  End of Gamer2LocationDTO Class
