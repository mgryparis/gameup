package gameup.entity;

import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = false)	//  OWNING-side o2o
	@JoinColumn(name = "human_id", nullable = true)
	private Human gamersHumanIdentity;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)	//  OWNING-side m2m
	@JoinTable(name = "gamer_gtype",
				joinColumns = @JoinColumn(name = "gamer_id"),
				inverseJoinColumns = @JoinColumn(name = "gtype_id")	)
	private Set<GType> gamersGTypes = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)	//  OWNING-side m2m
	@JoinTable(name = "gamer_gevent",
				joinColumns = @JoinColumn(name = "gamer_id"),
				inverseJoinColumns = @JoinColumn(name = "gevent_id")	)
	private Set<GEvent> gamersGEvents = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)	//  OWNING-side m2m
	@JoinTable(name = "gamer_glocation",
				joinColumns = @JoinColumn(name = "gamer_id"),
				inverseJoinColumns = @JoinColumn(name = "glocation_id")	)
	private Set<GLocation> gamersGLocations = new HashSet<>();
}
