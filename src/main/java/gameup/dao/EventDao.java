package gameup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import gameup.entity.Event;

public interface EventDao extends JpaRepository<Event, Long> {

}
