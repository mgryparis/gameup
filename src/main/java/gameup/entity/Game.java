package gameup.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gameId;
	@EqualsAndHashCode.Exclude
	private String gameName;
	@EqualsAndHashCode.Exclude
	private String gameUrl;
	@EqualsAndHashCode.Exclude
	private String gameNote;

	//=====>>  gamer|m2m|game, gamer owns game  //  game-side (ownED-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "gamesInterestedIn", cascade = CascadeType.PERSIST)
	private Set<Gamer> gamersInterestedIn = new HashSet<>();

	//=====>>  event|m2m|game, event owns game  //  game-side (ownED-side)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "gamesIncludedIn", cascade = CascadeType.PERSIST)
	private Set<Event> eventsPlayingAt = new HashSet<>();
}
