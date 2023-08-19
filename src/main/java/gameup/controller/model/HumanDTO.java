package gameup.controller.model;

import gameup.entity.Human;
import lombok.Data;
import lombok.NoArgsConstructor;

//HumanDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class HumanDTO {
	private Long humanId;
	private String humanFirstname;
	private String humanLastname;
	private String humanEmail;
	private String humanPhone;
	private String humanNote;
	
	//  Constructor that generates a HumanDTO instance from a Human Entity instance
	public HumanDTO(Human human) {
		this.humanId 			= human.getHumanId();
		this.humanFirstname 	= human.getHumanFirstname();
		this.humanLastname 		= human.getHumanLastname();
		this.humanEmail 		= human.getHumanEmail();
		this.humanPhone 		= human.getHumanPhone();
		this.humanNote 			= human.getHumanNote();			}
	
	//  Method on HumanDTO that returns the corresponding Location Entity instance
	public Human toHuman()	{
		Human human = new Human();
		human.setHumanId(humanId);
		human.setHumanFirstname(humanFirstname);
		human.setHumanLastname(humanLastname);
		human.setHumanEmail(humanEmail);
		human.setHumanPhone(humanPhone);
		human.setHumanNote(humanNote);
		return human;}

}	//  End of HumanDTO Class
