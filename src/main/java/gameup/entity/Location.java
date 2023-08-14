package gameup.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locationId;
	@EqualsAndHashCode.Exclude
	private String locationName;
	@EqualsAndHashCode.Exclude
	private String locationStreetaddress;
	@EqualsAndHashCode.Exclude
	private String locationCity;
	@EqualsAndHashCode.Exclude
	private String locationState;
	@EqualsAndHashCode.Exclude
	private String locationZip;
	@EqualsAndHashCode.Exclude
	private String locationPhone;
	@EqualsAndHashCode.Exclude
	private String locationNote;
	
	//=====>>  human|o2m|location, human owns location  //  location-side (ownED-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "human_id", nullable = false)
	private Human humanPoc;		
	
	//=====>>  gamer|m2m|location, gamer owns event  //  location-side (ownED-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "locationsHostingFor", cascade = CascadeType.PERSIST)
	private Set<Gamer> gamersHosting = new HashSet<>();	

	//=====>>  event|m2m|location, event owns location  //  location-side (ownED-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "locationsScheduledAt", cascade = CascadeType.PERSIST)
	private Set<Event> eventsScheduledAt = new HashSet<>();
}
