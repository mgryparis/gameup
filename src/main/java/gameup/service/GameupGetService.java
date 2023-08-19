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
import gameup.controller.model.LocationDTO;
import gameup.controller.model.HumanDTO;
import gameup.controller.model.EventDTOFull;
import gameup.controller.model.GameDTOFull;
import gameup.controller.model.GamerDTOFull;
import gameup.controller.model.LocationDTOFull;
import gameup.controller.model.HumanDTOFull;
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

@Service
public class GameupGetService {
	@Autowired private EventDao eventDao;
	@Autowired private GameDao gameDao;
	@Autowired private GamerDao gamerDao;
	@Autowired private HumanDao humanDao;
	@Autowired private LocationDao locationDao;
	@Autowired private EntityManager em;

//  ------------------------------------------------------------------------------------------
//  ----- [  Get Events  ]--------------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	//  Returns all Entities in the DB, but only the base DTO for each
	@Transactional(readOnly = true)
	public List<EventDTO> retrieveAllEvents()	{
		List<Event> eventList = eventDao.findAll();
		System.out.println("_____>>>  EventList = "+eventList);
		List<EventDTO> eventDTOList = new LinkedList<>();
		for (Event ev : eventList)	{
			EventDTO eventDTO = new EventDTO(ev);
			eventDTOList.add(eventDTO);									}
		System.out.println("_____>>>  EventList = "+eventDTOList);
		return eventDTOList;											}
	
	//  Returns only the requested Entity, but includes the base DTO + directly related base DTOs (1 hop only) 
	@Transactional(readOnly = true)
	public EventDTOFull retrieveEventById(Long eventId) {

		//  get the base Event entity and create the base DTO
		Event event = findEventById(eventId);
		
		//  get gamersRegisteredFor
		Query qGamers = em.createNativeQuery("select gamer_event.gamer_id from gamer_event where gamer_event.event_id = ?");
		qGamers.setParameter(1, eventId);
		@SuppressWarnings("unchecked")
		List<Long> listOfGamerIds = qGamers.getResultList();
		Set<GamerDTO> gamersRegisteredFor = new HashSet<>();
		System.out.println("_____>>>  List of Gamer IDs:  " + listOfGamerIds);
		for(Long id : listOfGamerIds)	{
			GamerDTO grDTO = new GamerDTO(findGamerById(id));
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
			GameDTO gmDTO = new GameDTO(findGameById(id));
			System.out.println("_____>>>  Game "+id+":  "+gmDTO);									
			gamesIncludedIn.add(gmDTO);												}
		
		//  get locationsScheduledAt
		Query qLocations = em.createNativeQuery("select event_location.location_id from event_location where event_location.event_id = ?");
		qLocations.setParameter(1, eventId);
		@SuppressWarnings("unchecked")
		List<Long> listOfLocationIds = qLocations.getResultList();
		Set<LocationDTO> locationsScheduledAt = new HashSet<>();
		System.out.println("_____>>>  List of Location IDs:  " + listOfLocationIds);
		for(Long id : listOfLocationIds)	{
			LocationDTO lcDTO = new LocationDTO (findLocationById(id));
			System.out.println("_____>>>  Location "+id+":  "+lcDTO);									
			locationsScheduledAt.add(lcDTO);												}
		
		//  Build and return the DTOFull
		EventDTOFull eventDTOF = new EventDTOFull(event,gamesIncludedIn,gamersRegisteredFor,locationsScheduledAt);
		return eventDTOF;															}

	private Event findEventById(Long eventId) {
		return eventDao.findById(eventId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Location with ID = "	+ eventId + " was not found ]"));	}

//  ------------------------------------------------------------------------------------------
//  ----- [  Get Games  ]---------------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	//  Returns all Entities in the DB, but only the base DTO for each
	@Transactional(readOnly = true)
	public List<GameDTO> retrieveAllGames() {
		List<Game> gameList = gameDao.findAll();
		List<GameDTO> gameDTOList = new LinkedList<>();
		for (Game gm : gameList)	{
			GameDTO gameDTO = new GameDTO(gm);
			gameDTOList.add(gameDTO);								}
		return gameDTOList;											}

	//  Returns only the requested Entity, but includes the base DTO + directly related base DTOs (1 hop only) 
	@Transactional(readOnly = true)
	public GameDTOFull retrieveGameById(Long gameId) {
		
		//  get the base Event entity and create the base DTO
		Game game = findGameById(gameId);
		
		//  get gamersInterestedIn
		Query qGamers = em.createNativeQuery("select gamer_game.gamer_id from gamer_game where gamer_game.game_id = ?");
		qGamers.setParameter(1, gameId);
		@SuppressWarnings("unchecked")
		List<Long> listOfGamerIds = qGamers.getResultList();
		Set<GamerDTO> gamersInterestedIn = new HashSet<>();
		System.out.println("_____>>>  List of Gamer IDs:  " + listOfGamerIds);
		for(Long id : listOfGamerIds)	{
			GamerDTO grDTO = new GamerDTO(findGamerById(id));
			System.out.println("_____>>>  Gamer "+id+":  "+grDTO);									
			gamersInterestedIn.add(grDTO);												}
		
		//  get eventsPlayingAt
		Query qEvents = em.createNativeQuery("select event_game.event_id from event_game where event_game.game_id = ?");
		qEvents.setParameter(1, gameId);
		@SuppressWarnings("unchecked")
		List<Long> listOfEventIds = qEvents.getResultList();
		Set<EventDTO> eventsPlayingAt = new HashSet<>();
		System.out.println("_____>>>  List of Event IDs:  " + eventsPlayingAt);
		for(Long id : listOfEventIds)	{
			EventDTO evDTO = new EventDTO(findEventById(id));
			System.out.println("_____>>>  Event "+id+":  "+evDTO);									
			eventsPlayingAt.add(evDTO);												}
		
		//  Build and return the DTOFull
		GameDTOFull gameDTOF = new GameDTOFull(game,gamersInterestedIn,eventsPlayingAt);
		return gameDTOF;															}
	
	private Game findGameById(Long gameId) {
		return gameDao.findById(gameId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Game with ID = "	+ gameId + " was not found ]"));	}

//  ------------------------------------------------------------------------------------------
//  ----- [  Get Gamers  ]--------------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	//  Returns all Entities in the DB, but only the base DTO for each
	@Transactional(readOnly = true)
	public List<GamerDTO> retrieveAllGamers() {
		List<Gamer> gamerList = gamerDao.findAll();
		List<GamerDTO> gamerDTOList = new LinkedList<>();
		for (Gamer gr : gamerList)	{
			GamerDTO gamerDTO = new GamerDTO(gr);
			gamerDTOList.add(gamerDTO);								}
		return gamerDTOList;										}

	//  Returns only the requested Entity, but includes the base DTO + directly related base DTOs (1 hop only) 
	@Transactional(readOnly = true)
	public GamerDTOFull retrieveGamerById(Long gamerId) {
	
		//  get the base Event entity and create the base DTO
		Gamer gamer = findGamerById(gamerId);
		
		//  Get Human Identity
		Human humanIdentity = gamer.getHumanIdentity();
		humanIdentity.getGamerIdentities().clear();
		humanIdentity.getLocationsHosting().clear();
		
		//  get gamesInterestedIn
		Query qGames = em.createNativeQuery("select gamer_game.game_id from gamer_game where gamer_game.gamer_id = ?");
		qGames.setParameter(1, gamerId);
		@SuppressWarnings("unchecked")
		List<Long> listOfGameIds = qGames.getResultList();
		Set<GameDTO> gamesInterestedIn = new HashSet<>();
		System.out.println("_____>>>  List of Game IDs:  " + listOfGameIds);
		for(Long id : listOfGameIds)	{
			GameDTO gmDTO = new GameDTO(findGameById(id));
			System.out.println("_____>>>  Game "+id+":  "+gmDTO);									
			gamesInterestedIn.add(gmDTO);													}
	
		//  get eventsRegisteredFor
		Query qEvents = em.createNativeQuery("select gamer_event.event_id from gamer_event where gamer_event.gamer_id = ?");
		qEvents.setParameter(1, gamerId);
		@SuppressWarnings("unchecked")
		List<Long> listOfEventIds = qEvents.getResultList();
		Set<EventDTO> eventsRegisteredFor = new HashSet<>();
		System.out.println("_____>>>  List of Event IDs:  " + eventsRegisteredFor);
		for(Long id : listOfEventIds)	{
			EventDTO evDTO = new EventDTO(findEventById(id));
			System.out.println("_____>>>  Event "+id+":  "+evDTO);									
			eventsRegisteredFor.add(evDTO);														}
	
		//  get locationsHostingFor
		Query qLocations = em.createNativeQuery("select gamer_location.location_id from gamer_location where gamer_location.gamer_id = ?");
		qLocations.setParameter(1, gamerId);
		@SuppressWarnings("unchecked")
		List<Long> listOfLocationIds = qLocations.getResultList();
		Set<LocationDTO> locationsHostingFor = new HashSet<>();
		System.out.println("_____>>>  List of Location IDs:  " + listOfLocationIds);
		for(Long id : listOfLocationIds)	{
			LocationDTO lcDTO = new LocationDTO (findLocationById(id));
			System.out.println("_____>>>  Location "+id+":  "+lcDTO);									
			locationsHostingFor.add(lcDTO);												}
		
		//  Build and return the DTOFull
		GamerDTOFull gamerDTOF = new GamerDTOFull(gamer, humanIdentity, gamesInterestedIn, eventsRegisteredFor, locationsHostingFor);
		return gamerDTOF;																	}
	
	private Gamer findGamerById(Long gamerId) {
		return gamerDao.findById(gamerId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Gamer with ID = " + gamerId + " was not found ]"));	}

//  ------------------------------------------------------------------------------------------
//  ----- [  Get Locations  ]-----------------------------------------------------------------
//  ------------------------------------------------------------------------------------------
	
	//  Returns all Entities in the DB, but only the base DTO for each
	@Transactional(readOnly = true)
	public List<LocationDTO> retrieveAllLocations() {
		List<Location> locationList = locationDao.findAll();
		List<LocationDTO> locationDTOList = new LinkedList<>();
		for (Location lc : locationList)	{
			LocationDTO locationDTO = new LocationDTO(lc);
			locationDTOList.add(locationDTO);						}
		return locationDTOList;										}
	
	//  Returns only the requested Entity, but includes the base DTO + directly related base DTOs (1 hop only) 
	@Transactional(readOnly = true)
	public LocationDTOFull retrieveLocationById(Long locationId) {

		//  get the base Event entity and create the base DTO
		Location location = findLocationById(locationId);
	
		//  Get Human POC
//		Human humanPoc = new Human();	//  placeholder
		Human humanPoc = location.getHumanPoc();
		humanPoc.getGamerIdentities().clear();
		humanPoc.getLocationsHosting().clear();

		//  get eventsScheduledAt
		Query qEvents = em.createNativeQuery("select event_location.event_id from event_location where event_location.location_id = ?");
		qEvents.setParameter(1, locationId);
		@SuppressWarnings("unchecked")
		List<Long> listOfEventIds = qEvents.getResultList();
		Set<EventDTO> eventsScheduledAt = new HashSet<>();
		System.out.println("_____>>>  List of Event IDs:  " + eventsScheduledAt);
		for(Long id : listOfEventIds)	{
			EventDTO evDTO = new EventDTO(findEventById(id));
			System.out.println("_____>>>  Event "+id+":  "+evDTO);									
			eventsScheduledAt.add(evDTO);														}
	
		//  get gamersHosting
		Query qGamers = em.createNativeQuery("select gamer_location.gamer_id from gamer_location where gamer_location.location_id = ?");
		qGamers.setParameter(1, locationId);
		@SuppressWarnings("unchecked")
		List<Long> listOfGamerIds = qGamers.getResultList();
		Set<GamerDTO> gamersHosting = new HashSet<>();
		System.out.println("_____>>>  List of Gamer IDs:  " + listOfGamerIds);
		for(Long id : listOfGamerIds)	{
			GamerDTO grDTO = new GamerDTO(findGamerById(id));
			System.out.println("_____>>>  Gamer "+id+":  "+grDTO);
			gamersHosting.add(grDTO);}
		
		//  Build and return the DTOFull
		LocationDTOFull locationDTOF = new LocationDTOFull(location, humanPoc, gamersHosting, eventsScheduledAt);
		return locationDTOF;																	}
	
	private Location findLocationById(Long locationId) {
		return locationDao.findById(locationId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Location with ID = " + locationId + " was not found ]"));	}

//  ------------------------------------------------------------------------------------------
//  ----- [  Get Humans  ]--------------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	@Transactional(readOnly = true)
	public List<HumanDTO> retrieveAllHumans() {
		List<Human> humanList = humanDao.findAll();
		List<HumanDTO> humanDTOList = new LinkedList<>();
		for (Human hu : humanList)	{
			HumanDTO humanDTO = new HumanDTO(hu);
			humanDTOList.add(humanDTO);						}
		return humanDTOList;										}
	
	public HumanDTOFull retrieveHumanById(Long humanId) {

		//  get the base Event entity and create the base DTO
		Human human = findHumanById(humanId);

		//  get gamerIdentities
		Query qGamers = em.createNativeQuery("select gamer.gamer_id from gamer where gamer.human_id = ?");
		qGamers.setParameter(1, humanId);
		@SuppressWarnings("unchecked")
		List<Long> listOfGamerIds = qGamers.getResultList();
		Set<GamerDTO> gamerIdentities = new HashSet<>();
		System.out.println("_____>>>  List of Gamer IDs:  " + listOfGamerIds);
		for(Long id : listOfGamerIds)	{
			GamerDTO grDTO = new GamerDTO(findGamerById(id));
			System.out.println("_____>>>  Gamer "+id+":  "+grDTO);
			gamerIdentities.add(grDTO);}

		//  get locationsHosting
		Query qLocations = em.createNativeQuery("select location.location_id from location where location.human_id = ?");
		qLocations.setParameter(1, humanId);
		@SuppressWarnings("unchecked")
		List<Long> listOfLocationIds = qLocations.getResultList();
		Set<LocationDTO> locationsHosting = new HashSet<>();
		System.out.println("_____>>>  List of Location IDs:  " + listOfLocationIds);
		for(Long id : listOfLocationIds)	{
			LocationDTO lcDTO = new LocationDTO (findLocationById(id));
			System.out.println("_____>>>  Location "+id+":  "+lcDTO);									
			locationsHosting.add(lcDTO);												}
		
		//  Build and return the DTOFull
		HumanDTOFull humanDTOF = new HumanDTOFull(human, gamerIdentities, locationsHosting);
		return humanDTOF;																	}

	private Human findHumanById(Long humanId) {
		return humanDao.findById(humanId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Human with ID = " + humanId + " was not found ]"));	}

}	//  End of GameupGetService Class
