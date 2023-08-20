package gameup.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gameup.controller.model.Event2GameDTO;
import gameup.controller.model.Event2LocationDTO;
import gameup.controller.model.EventDTO;
import gameup.controller.model.GameDTO;
import gameup.controller.model.Gamer2EventDTO;
import gameup.controller.model.Gamer2GameDTO;
import gameup.controller.model.Gamer2LocationDTO;
import gameup.controller.model.GamerDTO;
import gameup.controller.model.Human2GamerDTO;
import gameup.controller.model.Human2LocationDTO;
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
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class GameupPostPutService {
	@Autowired private EventDao eventDao;
	@Autowired private GameDao gameDao;
	@Autowired private GamerDao gamerDao;
	@Autowired private HumanDao humanDao;
	@Autowired private LocationDao locationDao;
	@Autowired private EntityManager em;
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Events  ]---------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	

	//  Save an Event
	@Transactional(readOnly = false)
	public EventDTO saveEvent(EventDTO eventDTO) {
		Event event = eventDTO.toEvent();
		Event savedEvent = eventDao.save(event);
		return new EventDTO(savedEvent);				}

	//	Save a new Event-to-Game relationship ("includes") to the event_game table
	@Transactional(readOnly = false)
	public Event2GameDTO saveEvent2Game(Event2GameDTO event2GameDTO)	{
		Long evId = event2GameDTO.getEventId();
		Long gmId = event2GameDTO.getGameId();
		
		//  1 - Check to see if the Game is already included in the Event
		Query qGameAlreadyInEvent = em.createNativeQuery(
				"select count(event_game.event_id) from event_game "
				+ "where event_game.event_id = ? and event_game.game_id =?");
		qGameAlreadyInEvent.setParameter(1, evId);
		qGameAlreadyInEvent.setParameter(2, gmId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qGameAlreadyInEvent.getResultList();
		if(resultList.get(0)>0)	{
			event2GameDTO.setGoNoGoResult("No update attempted:  Game already included in Event");
			return event2GameDTO;															}			
		
		//  2 - Check to see if Event/Game IDs exist and Set CheckResult strings accordingly
		boolean eventExists = checkIfEventIdExists(evId);
		boolean gameExists = checkIfGameIdExists(gmId);
		if(eventExists)	{	event2GameDTO.setEventCheckResult("Event " + evId + " exists");	}
		else	{	event2GameDTO.setEventCheckResult("Event " + evId + " does not exist");	}
		if(gameExists)	{	event2GameDTO.setGameCheckResult("Game " + gmId + " exists");	}
		else	{	event2GameDTO.setGameCheckResult("Game " + gmId + " does not exist");	}

		//  3 -  If both exist then attempt to insert the row
		if(eventExists && gameExists) {
			event2GameDTO.setGoNoGoResult("Executing operation");
			Query qInsertRowInEvent2GameTable = em.createNativeQuery(
					"insert into event_game (event_id, game_id) values (?,?)");
			qInsertRowInEvent2GameTable.setParameter(1, evId);
			qInsertRowInEvent2GameTable.setParameter(2, gmId);
			qInsertRowInEvent2GameTable.executeUpdate();									}
		//  Else abort the operation
		else	{	event2GameDTO.setGoNoGoResult("Aborting operation");					}
		return event2GameDTO;																}
	
	//	Save a new Event-to-Location relationship ("includes") to the event_location table
	@Transactional(readOnly = false)
	public Event2LocationDTO saveEvent2Location(Event2LocationDTO event2LocationDTO) {
		Long evId = event2LocationDTO.getEventId();
		Long lcId = event2LocationDTO.getLocationId();
		
		//  1 - Check to see if the Location is already included in the Event
		Query qLocationAlreadyInEvent = em.createNativeQuery(
				"select count(event_location.event_id) from event_location "
				+ "where event_location.event_id = ? and event_location.location_id =?");
		qLocationAlreadyInEvent.setParameter(1, evId);
		qLocationAlreadyInEvent.setParameter(2, lcId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qLocationAlreadyInEvent.getResultList();
		if(resultList.get(0)>0)	{
			event2LocationDTO.setGoNoGoResult("No update attempted:  Location already included in Event");
			return event2LocationDTO;															}			
		
		//  2 - Check to see if Event/Location IDs exist and Set CheckResult strings accordingly
		boolean eventExists = checkIfEventIdExists(evId);
		boolean locationExists = checkIfLocationIdExists(lcId);
		if(eventExists)	{	event2LocationDTO.setEventCheckResult("Event " + evId + " exists");	}
		else	{	event2LocationDTO.setEventCheckResult("Event " + evId + " does not exist");	}
		if(locationExists)	{	event2LocationDTO.setLocationCheckResult("Location " + lcId + " exists");	}
		else	{	event2LocationDTO.setLocationCheckResult("Location " + lcId + " does not exist");	}

		//  3 -  If both exist then attempt to insert the row
		if(eventExists && locationExists) {
			event2LocationDTO.setGoNoGoResult("Executing operation");
			Query qInsertRowInEvent2LocationTable = em.createNativeQuery(
					"insert into event_location (event_id, location_id) values (?,?)");
			qInsertRowInEvent2LocationTable.setParameter(1, evId);
			qInsertRowInEvent2LocationTable.setParameter(2, lcId);
			qInsertRowInEvent2LocationTable.executeUpdate();									}
		//  Else abort the operation
		else	{	event2LocationDTO.setGoNoGoResult("Aborting operation");					}
		return event2LocationDTO;																}
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Games  ]----------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	

	//  Save a Game
	@Transactional(readOnly = false)
	public GameDTO saveGame(GameDTO gameDTO) {
		Game game = gameDTO.toGame();
		Game savedGame = gameDao.save(game);
		return new GameDTO(savedGame);				}
		
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Gamers  ]---------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	//  Save a Gamer
	@Transactional(readOnly = false)
	public GamerDTO saveGamer(GamerDTO gamerDTO) {
		Gamer gamer = gamerDTO.toGamer();
		Gamer savedGamer = gamerDao.save(gamer);
		return new GamerDTO(savedGamer);				}

	//	Save a new Gamer-to-Game relationship ("interested in") to the gamer_game table
	@Transactional(readOnly = false)
	public Gamer2GameDTO saveGamer2Game(Gamer2GameDTO gamer2GameDTO)	{
		Long grId = gamer2GameDTO.getGamerId();
		Long gmId = gamer2GameDTO.getGameId();
		
		//  1 - Check to see if the Game is already interested in the Game
		Query qGamerAlreadyInterestedInGame = em.createNativeQuery(
				"select count(gamer_game.gamer_id) from gamer_game "
				+ "where gamer_game.gamer_id = ? and event_game.game_id =?");
		qGamerAlreadyInterestedInGame.setParameter(1, grId);
		qGamerAlreadyInterestedInGame.setParameter(2, gmId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qGamerAlreadyInterestedInGame.getResultList();
		if(resultList.get(0)>0)	{
			gamer2GameDTO.setGoNoGoResult("No update attempted:  Game already included in Event");
			return gamer2GameDTO;															}			
		
		//  2 - Check to see if Gamer/Game IDs exist and Set CheckResult strings accordingly
		boolean gamerExists = checkIfGamerIdExists(grId);
		boolean gameExists = checkIfGameIdExists(gmId);
		if(gamerExists)	{	gamer2GameDTO.setGamerCheckResult("Gamer " + grId + " exists");	}
		else	{	gamer2GameDTO.setGamerCheckResult("Gamer " + grId + " does not exist");	}
		if(gameExists)	{	gamer2GameDTO.setGameCheckResult("Game " + gmId + " exists");	}
		else	{	gamer2GameDTO.setGameCheckResult("Game " + gmId + " does not exist");	}

		//  3 -  If both exist then attempt to insert the row
		if(gamerExists && gameExists) {
			gamer2GameDTO.setGoNoGoResult("Executing operation");
			Query qInsertRowInGamer2GameTable = em.createNativeQuery(
					"insert into gamer_game (gamer_id, game_id) values (?,?)");
			qInsertRowInGamer2GameTable.setParameter(1, grId);
			qInsertRowInGamer2GameTable.setParameter(2, gmId);
			qInsertRowInGamer2GameTable.executeUpdate();									}
		//  Else abort the operation
		else	{	gamer2GameDTO.setGoNoGoResult("Aborting operation");					}
		return gamer2GameDTO;																}
	
	//	Save a new Gamer-to-Event relationship ("registered for") to the gamer_event table
	@Transactional(readOnly = false)
	public Gamer2EventDTO saveGamer2Event(Gamer2EventDTO gamer2EventDTO) {
		Long grId = gamer2EventDTO.getGamerId();
		Long evId = gamer2EventDTO.getEventId();
		
		//  1 - Check to see if the Gamer is already registered for the event
		Query qGamerAlreadyRegisteredForEvent = em.createNativeQuery(
				"select count(gamer_event.gamer_id) from gamer_event "
				+ "where gamer_event.gamer_id = ? and gamer_event.event_id =?");
		qGamerAlreadyRegisteredForEvent.setParameter(1, grId);
		qGamerAlreadyRegisteredForEvent.setParameter(2, evId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qGamerAlreadyRegisteredForEvent.getResultList();
		if(resultList.get(0)>0)	{
			gamer2EventDTO.setGoNoGoResult("No update attempted:  Gamer already registered for Event");
			return gamer2EventDTO;															}			
		
		//  2 - Check to see if Gamer/Event IDs exist and Set CheckResult strings accordingly
		boolean gamerExists = checkIfGamerIdExists(grId);
		boolean eventExists = checkIfEventIdExists(evId);
		if(gamerExists)	{	gamer2EventDTO.setGamerCheckResult("Gamer " + grId + " exists");	}
		else	{	gamer2EventDTO.setGamerCheckResult("Gamer " + grId + " does not exist");	}
		if(eventExists)	{	gamer2EventDTO.setEventCheckResult("Event " + evId + " exists");	}
		else	{	gamer2EventDTO.setEventCheckResult("Event " + evId + " does not exist");	}

		//  3 -  If both exist then attempt to insert the row
		if(gamerExists && eventExists) {
			gamer2EventDTO.setGoNoGoResult("Executing operation");
			Query qInsertRowInGamer2EventTable = em.createNativeQuery(
					"insert into gamer_event (gamer_id, event_id) values (?,?)");
			qInsertRowInGamer2EventTable.setParameter(1, grId);
			qInsertRowInGamer2EventTable.setParameter(2, evId);
			qInsertRowInGamer2EventTable.executeUpdate();									}
		//  Else abort the operation
		else	{	gamer2EventDTO.setGoNoGoResult("Aborting operation");					}
		return gamer2EventDTO;																}

	//	Save a new Gamer-to-Location relationship ("hosting for") to the gamer_location table
	@Transactional(readOnly = false)
	public Gamer2LocationDTO saveGamer2Location(Gamer2LocationDTO gamer2LocationDTO) {
		Long grId = gamer2LocationDTO.getGamerId();
		Long lcId = gamer2LocationDTO.getLocationId();
		
		//  1 - Check to see if the Gamer is already hosting the Location
		Query qGamerAlreadyHostingLocation = em.createNativeQuery(
				"select count(gamer_location.gamer_id) from gamer_location "
				+ "where gamer_location.gamer_id = ? and gamer_location.location_id =?");
		qGamerAlreadyHostingLocation.setParameter(1, grId);
		qGamerAlreadyHostingLocation.setParameter(2, lcId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qGamerAlreadyHostingLocation.getResultList();
		if(resultList.get(0)>0)	{
			gamer2LocationDTO.setGoNoGoResult("No update attempted:  Gamer already hosting for location");
			return gamer2LocationDTO;															}			
		
		//  2 - Check to see if Gamer/Location IDs exist and Set CheckResult strings accordingly
		boolean gamerExists = checkIfGamerIdExists(grId);
		boolean locationExists = checkIfLocationIdExists(lcId);
		if(gamerExists)	{	gamer2LocationDTO.setGamerCheckResult("Gamer " + grId + " exists");	}
		else	{	gamer2LocationDTO.setGamerCheckResult("Gamer " + grId + " does not exist");	}
		if(locationExists)	{	gamer2LocationDTO.setLocationCheckResult("Location " + lcId + " exists");	}
		else	{	gamer2LocationDTO.setLocationCheckResult("Location " + lcId + " does not exist");	}

		//  3 -  If both exist then attempt to insert the row
		if(gamerExists && locationExists) {
			gamer2LocationDTO.setGoNoGoResult("Executing operation");
			Query qInsertRowInGamer2LocationTable = em.createNativeQuery(
					"insert into gamer_location (gamer_id, location_id) values (?,?)");
			qInsertRowInGamer2LocationTable.setParameter(1, grId);
			qInsertRowInGamer2LocationTable.setParameter(2, lcId);
			qInsertRowInGamer2LocationTable.executeUpdate();									}
		//  Else abort the operation
		else	{	gamer2LocationDTO.setGoNoGoResult("Aborting operation");					}
		return gamer2LocationDTO;																}
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Locations  ]------------------------------------------------------------
//  ------------------------------------------------------------------------------------------

	//  Save a Location
	@Transactional(readOnly = false)
	public LocationDTO saveLocation(LocationDTO locationDTO) {
		Location location= locationDTO.toLocation();
		Location savedLocation = locationDao.save(location);
		return new LocationDTO(savedLocation);				}
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Humans  ]---------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	//  Save a Human
	@Transactional(readOnly = false)
	public HumanDTO saveHuman(HumanDTO humanDTO) {
		Human human = humanDTO.toHuman();
		Human savedHuman= humanDao.save(human);
		return new HumanDTO(savedHuman);				}
	
	//	Save a new Human-to-Location relationship ("POC for") to the location table (o2m)
	@Transactional(readOnly = false)
	public Human2LocationDTO saveHuman2Location(Human2LocationDTO human2LocationDTO) {
		Long hmId = human2LocationDTO.getHumanId();
		Long lcId = human2LocationDTO.getLocationId();
//		System.out.println("_____>>>  Human ID passed to saveHumanToLocation: "+hmId);
//		System.out.println("_____>>>  Location ID passed to saveHumanToLocation: "+lcId);

		//  1 - Check to see if Human/Location IDs exist and Set CheckResult strings accordingly
		//	If one or both does NOT exist > Abort
		boolean humanExists = checkIfHumanIdExists(hmId);
		boolean locationExists = checkIfLocationIdExists(lcId);
//		System.out.println("_____>>>  Human Exists:  "+humanExists);
//		System.out.println("_____>>>  Location Exists:  "+locationExists);
		if(humanExists)	{	human2LocationDTO.setHumanCheckResult("Human " + hmId + " exists");	}
		else	{	human2LocationDTO.setHumanCheckResult("Human " + hmId + " does not exist");	}
		if(locationExists)	{	human2LocationDTO.setLocationCheckResult("Location " + lcId + " exists");	}
		else	{	human2LocationDTO.setLocationCheckResult("Location " + lcId + " does not exist");	}
		if(!humanExists || !locationExists) {
			human2LocationDTO.setGoNoGoResult("Aborting operation");
			return human2LocationDTO;													}
		
		//  3 -  If both exist, set the Human as the POC - This will overwrite any previous POC
		human2LocationDTO.setGoNoGoResult("Executing operation");
//		System.out.println("_____>>>  Executing Operation");
		Query qUpdateHumanPocInLocationRecord = em.createNativeQuery(
				"update location set location.human_id=? where location.location_id = ?");
		qUpdateHumanPocInLocationRecord.setParameter(1, hmId);
		qUpdateHumanPocInLocationRecord.setParameter(2, lcId);
		qUpdateHumanPocInLocationRecord.executeUpdate();
		return human2LocationDTO;																}
	
	//	Save a new Human-to-Gamer relationship ("identity of") to the gamer table (o2m)
	@Transactional(readOnly = false)
	public Human2GamerDTO saveHuman2Gamer(Human2GamerDTO human2GamerDTO) {
		Long hmId = human2GamerDTO.getHumanId();
		Long grId = human2GamerDTO.getGamerId();
//		System.out.println("_____>>>  Human ID passed to saveHumanToLocation: "+hmId);
//		System.out.println("_____>>>  Gamer ID passed to saveHumanToLocation: "+grId);

		//  1 - Check to see if Human/Gamer IDs exist and Set CheckResult strings accordingly
		//	If one or both does NOT exist > Abort
		boolean humanExists = checkIfHumanIdExists(hmId);
		boolean gamerExists = checkIfGamerIdExists(grId);
//		System.out.println("_____>>>  Human Exists:  "+humanExists);
//		System.out.println("_____>>>  Gamer Exists:  "+gamerExists);
		if(humanExists)	{	human2GamerDTO.setHumanCheckResult("Human " + hmId + " exists");	}
		else	{	human2GamerDTO.setHumanCheckResult("Human " + hmId + " does not exist");	}
		if(gamerExists)	{	human2GamerDTO.setGamerCheckResult("Location " + grId + " exists");	}
		else	{	human2GamerDTO.setGamerCheckResult("Location " + grId + " does not exist");	}
		if(!humanExists || !gamerExists) {
			human2GamerDTO.setGoNoGoResult("Aborting operation");
			return human2GamerDTO;													}
		
		//  3 -  If both exist, set the Human as the Gamer's identity - This will overwrite any previous identity
		human2GamerDTO.setGoNoGoResult("Executing operation");
//		System.out.println("_____>>>  Executing Operation");
		Query qUpdateHumanIdInGamerPersonaRecord = em.createNativeQuery(
				"update gamer set gamer.human_id=? where gamer.gamer_id = ?");
		qUpdateHumanIdInGamerPersonaRecord.setParameter(1, hmId);
		qUpdateHumanIdInGamerPersonaRecord.setParameter(2, grId);
		qUpdateHumanIdInGamerPersonaRecord.executeUpdate();
		return human2GamerDTO;																}
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Re-used ID Check Methods ]-------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
		
	private boolean checkIfEventIdExists(Long evId) {
		Query qEventIdExists = em.createNativeQuery("select count(event_id) from event where event.event_id = ?");
		qEventIdExists.setParameter(1, evId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qEventIdExists.getResultList();
		if(resultList.get(0)>0)	{	return true;						}			
		return false;													}

	private boolean checkIfGameIdExists(Long gmId) {
		Query qGameIdExists = em.createNativeQuery("select count(game_id) from game where game.game_id = ?");
		qGameIdExists.setParameter(1, gmId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qGameIdExists.getResultList();
		if(resultList.get(0)>0)	{	return true;						}			
		return false;													}

	private boolean checkIfLocationIdExists(Long lcId) {
		Query qLocationIdExists = em.createNativeQuery("select count(location_id) from location where location.location_id = ?");
		qLocationIdExists.setParameter(1, lcId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qLocationIdExists.getResultList();
		if(resultList.get(0)>0)	{	return true;						}			
		return false;													}
	
	private boolean checkIfGamerIdExists(Long grId) {
		Query qGamerIdExists = em.createNativeQuery("select count(gamer_id) from gamer where gamer.gamer_id = ?");
		qGamerIdExists.setParameter(1, grId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qGamerIdExists.getResultList();
		if(resultList.get(0)>0)	{	return true;						}			
		return false;													}

	private boolean checkIfHumanIdExists(Long hmId) {
		Query qHumanIdExists = em.createNativeQuery("select count(human_id) from human where human.human_id = ?");
		qHumanIdExists.setParameter(1, hmId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qHumanIdExists.getResultList();
		if(resultList.get(0)>0)	{	return true;						}			
		return false;													}



}	//  End of GameupPostPutService Class
