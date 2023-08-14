package gameup.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Gamer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gamerId;
	@EqualsAndHashCode.Exclude
	private String gamerHandle;
	@EqualsAndHashCode.Exclude
	private String gamerEmail;
	@EqualsAndHashCode.Exclude
	private String gamerDiscord;
	@EqualsAndHashCode.Exclude
	private String gamerUrl;
	@EqualsAndHashCode.Exclude
	private String gamerNote;

	//=====>>  human|o2m|gamer, human owns gamer  //  gamer-side (ownED-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "human_id", nullable = false)
	private Human humanIdentity;

	//=====>>  gamer|m2m|game, gamer owns game  //  gamer-side (ownING-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(	cascade = CascadeType.PERSIST	)
	@JoinTable(		name = "gamer_game",
					joinColumns = @JoinColumn(name = "gamer_id"),
					inverseJoinColumns = @JoinColumn(name = "game_id")	)
	private Set<Game> gamesInterestedIn = new HashSet<>();

	//=====>>  gamer|m2m|event, gamer owns event  //  gamer-side (ownING-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(	cascade = CascadeType.PERSIST	)
	@JoinTable(		name = "gamer_event",
					joinColumns = @JoinColumn(name = "gamer_id"),
					inverseJoinColumns = @JoinColumn(name = "event_id")	)
	private Set<Event> eventsRegisteredFor = new HashSet<>();
	
	//=====>>  gamer|m2m|location, gamer owns location  //  gamer-side (ownING-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(	cascade = CascadeType.PERSIST	)
	@JoinTable(		name = "gamer_location",
					joinColumns = @JoinColumn(name = "gamer_id"),
					inverseJoinColumns = @JoinColumn(name = "location_id")	)
	private Set<Location> locationsHostingFor = new HashSet<>();
}
