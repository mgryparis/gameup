package gameup.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Human {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long humanId;
	@EqualsAndHashCode.Exclude
	private String humanFirstName;
	@EqualsAndHashCode.Exclude
	private String humanLastName;
	@EqualsAndHashCode.Exclude
	private String humanEmail;
	@EqualsAndHashCode.Exclude
	private String humanPhone;
	@EqualsAndHashCode.Exclude
	private String humanNote;

	//=====>>  human|o2m|gamer, human owns gamer  //  human-side (ownING-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "humanIdentity",
			cascade = CascadeType.PERSIST, orphanRemoval = false)
	private Set<Gamer> gamerIdentities = new HashSet<>();;

	//=====>>  human|o2m|location, human owns location  //  human-side (ownING-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "humanPoc", cascade = CascadeType.PERSIST, orphanRemoval = false)
	private Set<Location> locationsHosting = new HashSet<>();
}
