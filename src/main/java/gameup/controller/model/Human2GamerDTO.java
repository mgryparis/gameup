package gameup.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

//Event-to-GameDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class Human2GamerDTO {
	private Long humanId;
	private Long gamerId;
	private String humanCheckResult;
	private String gamerCheckResult;
	private String goNoGoResult;
	
	public Human2GamerDTO(Long humanId, Long gamerId)	{
		this.humanId	= humanId;
		this.gamerId	= gamerId;						}

}	//  End of Human2GamerDTO Class
