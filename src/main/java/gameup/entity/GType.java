package gameup.entity;

import java.util.HashSet;
import java.util.Set;
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
public class GType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gtypeId;
	@EqualsAndHashCode.Exclude
	private String gtypeName;
	@EqualsAndHashCode.Exclude
	private String gtypeUrl;
	@EqualsAndHashCode.Exclude
	private String gtypeNote;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "gamer_id")	//  OWNED-side m2m
	@JoinTable(name = "gamer_gtype",
				joinColumns = @JoinColumn(name = "gamer_id"),
				inverseJoinColumns = @JoinColumn(name = "gtype_id")	)
	private Set<Gamer> gtypesGamers = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "gevent_id")		//  OWNED-side o2m
	private Set<GEvent> gtypesGEvents = new HashSet<>();
}
