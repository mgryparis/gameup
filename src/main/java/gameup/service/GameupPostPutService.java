package gameup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameup.controller.model.EventDTO;
import gameup.controller.model.GameDTO;
import gameup.controller.model.GamerDTO;
import gameup.controller.model.HumanDTO;
import gameup.controller.model.LocationDTO;
import gameup.dao.EventDao;
import gameup.dao.GameDao;
import gameup.dao.GamerDao;
import gameup.dao.HumanDao;
import gameup.dao.LocationDao;
import gameup.entity.Event;
import gameup.entity.Game;
import gameup.entity.Gamer;
import gameup.entity.Human;
import gameup.entity.Location;
//  import jakarta.persistence.EntityManager;

@Service
public class GameupPostPutService {
	@Autowired private EventDao eventDao;
	@Autowired private GameDao gameDao;
	@Autowired private GamerDao gamerDao;
	@Autowired private HumanDao humanDao;
	@Autowired private LocationDao locationDao;
//	@Autowired private EntityManager em;
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Events  ]--------------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	

	@Transactional(readOnly = false)
	public EventDTO saveEvent(EventDTO eventDTO) {
		Event event = eventDTO.toEvent();
		Event savedEvent = eventDao.save(event);
		return new EventDTO(savedEvent);				}
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Games  ]---------------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	@Transactional(readOnly = false)
	public GameDTO saveGame(GameDTO gameDTO) {
		Game game = gameDTO.toGame();
		Game savedGame = gameDao.save(game);
		return new GameDTO(savedGame);				}
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Gamers  ]--------------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	@Transactional(readOnly = false)
	public GamerDTO saveGamer(GamerDTO gamerDTO) {
		Gamer gamer = gamerDTO.toGamer();
		Gamer savedGamer = gamerDao.save(gamer);
		return new GamerDTO(savedGamer);				}
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Locations  ]-----------------------------------------------------------------
//  ------------------------------------------------------------------------------------------
	
	@Transactional(readOnly = false)
	public LocationDTO saveLocation(LocationDTO locationDTO) {
		Location location= locationDTO.toLocation();
		Location savedLocation = locationDao.save(location);
		return new LocationDTO(savedLocation);				}
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Humans  ]--------------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	@Transactional(readOnly = false)
	public HumanDTO saveHuman(HumanDTO humanDTO) {
		Human human = humanDTO.toHuman();
		Human savedHuman= humanDao.save(human);
		return new HumanDTO(savedHuman);				}
	
	
}	//  End of GameupPostPutService Class
