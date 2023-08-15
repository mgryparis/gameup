package gameup.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import gameup.entity.Human;

public interface HumanDao extends JpaRepository<Human, Long> {

}
