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
public class GLocation {
	private Long glocationId;
	private String glocationName;
	private String glocationLevel;
	private String glocationDate;
	private String glocationStarttime;
	private String glocationEndtime;
	
	private Human glocationsHumanPoc;		//  OWNED-side o20
	private Set<Gamer> glocationsGamerHosts;//  OWNED-side m2m
	private Set<GEvent> glocationsGEvents;	//  OWNED-side o2m
}
