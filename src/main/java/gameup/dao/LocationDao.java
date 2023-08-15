package gameup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import gameup.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long> {

}
