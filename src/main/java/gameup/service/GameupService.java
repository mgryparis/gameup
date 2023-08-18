package gameup.service;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import gameup.controller.model.EventDTO;
import gameup.controller.model.GameDTO;
import gameup.controller.model.GamerDTO;
import gameup.controller.model.EventDTOFull;
import gameup.dao.EventDao;
import gameup.dao.GameDao;
import gameup.dao.GamerDao;
import gameup.dao.HumanDao;
import gameup.dao.LocationDao;
import gameup.entity.Event;
import gameup.entity.Game;
import gameup.entity.Gamer;


@Service
public class GameupService {
	@Autowired private EventDao eventDao;
	@Autowired private GameDao gameDao;
	@Autowired private GamerDao gamerDao;
	@Autowired private HumanDao humanDao;
	@Autowired private LocationDao locationDao;
	@Autowired private EntityManager em;
	
	//  %%%%%[  Retrieve Entities  ]%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	//  Event -----------------------------------------------------
	
	@Transactional(readOnly = true)
	public List<EventDTO> retrieveAllEvents()	{
		List<Event> eventList = eventDao.findAll();
		List<EventDTO> eventDTOList = new LinkedList<>();
		for (Event ev : eventList)	{
//			ev.getGamersRegisteredFor().clear();
//			ev.getGamesIncludedIn().clear();
//			ev.getLocationsScheduledAt().clear();
			EventDTO eventDTO = new EventDTO(ev);
			
			//  get gamersRegisteredFor
						
			//  get gamesIncludedIn
			
			//  get locationsScheduledAt
			
			
			eventDTOList.add(eventDTO);									}
		return eventDTOList;											}
	
	@Transactional(readOnly = true)
	public EventDTOFull retrieveEventById(Long eventId) {
		Event event = findEventById(eventId);
		EventDTO eventDTO = new EventDTO(event);
		
		//  get gamersRegisteredFor
		Query qGamers = em.createNativeQuery("select gamer_event.gamer_id from gamer_event where gamer_event.event_id = ?");
		qGamers.setParameter(1, eventId);
		@SuppressWarnings("unchecked")
		List<Long> listOfGamerIds = qGamers.getResultList();
		Set<GamerDTO> gamersRegisteredFor = new HashSet<>();
		System.out.println("_____>>>  List of Gamer IDs:  " + listOfGamerIds);
		for(Long id : listOfGamerIds)	{
			GamerDTO grDTO = retrieveGamerById(id);
			System.out.println("_____>>>  Gamer "+id+":  "+grDTO);									
			gamersRegisteredFor.add(grDTO);												}
		
					
		//  get gamesIncludedIn
		Query qGames = em.createNativeQuery("select event_game.game_id from event_game where event_game.event_id = ?");
		qGames.setParameter(1, eventId);
		@SuppressWarnings("unchecked")
		List<Long> listOfGameIds = qGames.getResultList();
		Set<GameDTO> gamesIncludedIn = new HashSet<>();
		System.out.println("_____>>>  List of Game IDs:  " + listOfGameIds);
		for(Long id : listOfGameIds)	{
			GameDTO gmDTO = retrieveGameById(id);
			System.out.println("_____>>>  Game "+id+":  "+gmDTO);									
			gamesIncludedIn.add(gmDTO);												}
		
		//  get locationsScheduledAt
		

		
		

		EventDTOFull eventDTOF = new EventDTOFull(eventDTO,gamesIncludedIn,gamersRegisteredFor);
		return eventDTOF;															}

	private Event findEventById(Long eventId) {
//		System.out.println(">>>>>    You have reached findEventByID()");
		return eventDao.findById(eventId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Location with ID = "	+ eventId + " was not found ]"));	}

	//  Game -----------------------------------------------------

	@Transactional(readOnly = true)
	public List<GameDTO> retrieveAllGames() {
		List<Game> gameList = gameDao.findAll();
		List<GameDTO> gameDTOList = new LinkedList<>();
		for (Game gm : gameList)	{
			GameDTO gameDTO = new GameDTO(gm);
			gameDTOList.add(gameDTO);								}
		return gameDTOList;											}

	@Transactional(readOnly = true)
	public GameDTO retrieveGameById(Long gameId) {
		Game game = findGameById(gameId);
		GameDTO gameDTO = new GameDTO(game);
		return gameDTO;									}
	
	private Game findGameById(Long gameId) {
		return gameDao.findById(gameId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Game with ID = "	+ gameId + " was not found ]"));	}

	//  Gamer -----------------------------------------------------
	
	@Transactional(readOnly = true)
	public List<GamerDTO> retrieveAllGamers() {
		List<Gamer> gamerList = gamerDao.findAll();
		List<GamerDTO> gamerDTOList = new LinkedList<>();
		for (Gamer gr : gamerList)	{
			GamerDTO gamerDTO = new GamerDTO(gr);
			gamerDTOList.add(gamerDTO);								}
		return gamerDTOList;										}

	@Transactional(readOnly = true)
	public GamerDTO retrieveGamerById(Long gamerId) {
		Gamer gamer = findGamerById(gamerId);
		GamerDTO gamerDTO = new GamerDTO(gamer);
		return gamerDTO;										}

	private Gamer findGamerById(Long gamerId) {
		return gamerDao.findById(gamerId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Gamer with ID = " + gamerId + " was not found ]"));	}

	
	
	
	
	
	
	
	
	
	
	
	



	}	//  End of GameupService Class
