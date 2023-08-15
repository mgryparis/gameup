package gameup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import gameup.entity.Gamer;

public interface GamerDao extends JpaRepository<Gamer, Long> {

}
