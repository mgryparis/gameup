package gameup.controller.model;

import gameup.entity.Gamer;
import gameup.entity.Human;
import lombok.Data;
import lombok.NoArgsConstructor;

//  GamerDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class GamerDTO {
	private Long gamerId;
	private String gamerHandle;
	private String gamerEmail;
	private String gamerDiscord;
	private String gamerUrl;
	private String gamerNote;
//	private Human humanIdentity;

	
	//  Constructor that generates a GamerDTO instance from a Gamer Entity instance
	public GamerDTO(Gamer gamer) {
		this.gamerId 		= gamer.getGamerId();
		this.gamerHandle 	= gamer.getGamerHandle();
		this.gamerEmail 	= gamer.getGamerEmail();
		this.gamerDiscord 	= gamer.getGamerDiscord();
		this.gamerUrl 		= gamer.getGamerUrl();
		this.gamerNote 		= gamer.getGamerNote();				}
	
	//  Method on GamerDTO that returns the corresponding Gamer Entity instance
	public Gamer toGamer()	{
		Gamer gamer = new Gamer();
		gamer.setGamerId(gamerId);
		gamer.setGamerHandle(gamerHandle);
		gamer.setGamerEmail(gamerEmail);
		gamer.setGamerDiscord(gamerDiscord);
		gamer.setGamerUrl(gamerUrl);
		gamer.setGamerNote(gamerNote);
		return gamer;														}
	
}	//  End of GamerDTO Class
