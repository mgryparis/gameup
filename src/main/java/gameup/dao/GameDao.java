package gameup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import gameup.entity.Game;

public interface GameDao extends JpaRepository<Game, Long> {

}
