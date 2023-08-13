package gameup.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

	@EqualsAndHashCode.Exclude
	@OneToOne(mappedBy = "gamerId")		//  Owned-side o2o
	private Gamer humansGamerIdentity;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = false)
	private Set<GLocation> humansGLocations = new HashSet<>();
}
