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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eventId;
	@EqualsAndHashCode.Exclude
	private String eventName;
	@EqualsAndHashCode.Exclude
	private String eventLevel;
	@EqualsAndHashCode.Exclude
	private String eventDate;
	@EqualsAndHashCode.Exclude
	private String eventStarttime;
	@EqualsAndHashCode.Exclude
	private String eventEndtime;
	
	//=====>>  gamer|m2m|event, gamer owns event  //  event-side (ownED-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "eventsRegisteredFor", cascade = CascadeType.PERSIST)
	private Set<Gamer> gamersRegisteredFor = new HashSet<>();

	//=====>>  event|m2m|game, event owns game  //  event-side (ownING-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(	cascade = CascadeType.PERSIST	)
	@JoinTable(	name = "event_game",
			joinColumns = @JoinColumn(name = "event_id"),
			inverseJoinColumns = @JoinColumn(name = "game_id"))
	private Set<Game> gamesIncludedIn = new HashSet<>();

	//=====>>  event|m2m|location, event owns location  //  event-side (ownING-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(	name = "event_location",
				joinColumns = @JoinColumn(name = "event_id"),
				inverseJoinColumns = @JoinColumn(name = "location_id"))
	private Set<Location> locationsScheduledAt = new HashSet<>();
	
}
