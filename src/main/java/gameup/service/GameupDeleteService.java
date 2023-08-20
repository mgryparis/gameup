package gameup.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameup.controller.model.DeleteEventDTO;
import gameup.controller.model.DeleteGamer2EventDTO;
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
public class GameupDeleteService {
	@Autowired private EventDao eventDao;
	@Autowired private GameDao gameDao;
	@Autowired private GamerDao gamerDao;
	@Autowired private HumanDao humanDao;
	@Autowired private LocationDao locationDao;
	@Autowired private EntityManager em;
	

	
//  ------------------------------------------------------------------------------------------
//  ----- [  Delete Events  ]---------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	

	@Transactional(readOnly = false)
	public DeleteEventDTO deleteEvent(Long eventId) {
		DeleteEventDTO deleteEventDTO = new DeleteEventDTO();

		//	Check if the Event exists, Build Result strings & Abort||Continue
		if(!checkIfEventIdExists(eventId))	{	
			deleteEventDTO.setEventCheckResult("Event " + eventId + " does not exist");
			deleteEventDTO.setGoNoGoResult("No update attempted");
			return deleteEventDTO;															}
		deleteEventDTO.setEventCheckResult("Event " + eventId + "  exists");
		deleteEventDTO.setGoNoGoResult("Executing Operation");
		
		//	Build the gamersRegisteredFor_String
		Query qGamersRegisteredFor = em.createNativeQuery(
				"select gamer_event.gamer_id from gamer_event where gamer_event.event_id = ?");
		qGamersRegisteredFor.setParameter(1, eventId);
		@SuppressWarnings("unchecked")
		List<Long> gamersRegisteredForList = qGamersRegisteredFor.getResultList();
		deleteEventDTO.setGamersRegisteredFor_String(gamersRegisteredForList.toString());
		
		//	Build the gamesIncludedIn_String
		Query qGamesIncludedIn = em.createNativeQuery(
				"select event_game.game_id from event_game where event_game.event_id = ?");
		qGamesIncludedIn.setParameter(1, eventId);
		@SuppressWarnings("unchecked")
		List<Long> gamesIncludedInList = qGamesIncludedIn.getResultList();
		deleteEventDTO.setGamesIncludedIn_String(gamesIncludedInList.toString());
		
		//	Build the locationsScheduledAt_String
		Query qLocationsScheduledAt = em.createNativeQuery(
				"select event_location.location_id from event_location where event_location.event_id = ?");
		qLocationsScheduledAt.setParameter(1, eventId);
		@SuppressWarnings("unchecked")
		List<Long> locationsScheduledAtList = qLocationsScheduledAt.getResultList();
		deleteEventDTO.setLocationsScheduledAt_String(locationsScheduledAtList.toString());
		
		//	Delete the Event and its Relationships from the m2m Join tables
		Integer delRowCount;
		Query qDeleteGamersRegisteredFor = em.createNativeQuery(
				"delete from gamer_event where gamer_event.event_id = ?");
		qDeleteGamersRegisteredFor.setParameter(1, eventId);
		delRowCount = qDeleteGamersRegisteredFor.executeUpdate();
		deleteEventDTO.setGamersRegisteredFor_RowsDeleted_String(delRowCount.toString());
		
		Query qDeleteGamesIncludedIn = em.createNativeQuery(
				"delete from event_game where event_game.event_id = ?");
		qDeleteGamesIncludedIn.setParameter(1, eventId);
		delRowCount = qDeleteGamesIncludedIn.executeUpdate();
		deleteEventDTO.setGamesIncludedIn_RowsDeleted_String(delRowCount.toString());

		Query qDeleteLocationsScheduledAt = em.createNativeQuery(
				"delete from event_location where event_location.event_id = ?");
		qDeleteLocationsScheduledAt.setParameter(1, eventId);
		delRowCount = qDeleteLocationsScheduledAt.executeUpdate();
		deleteEventDTO.setGamesIncludedIn_RowsDeleted_String(delRowCount.toString());

		Query qDeleteEvent = em.createNativeQuery(
				"delete from event where event.event_id = ?");
		qDeleteEvent.setParameter(1, eventId);
		delRowCount = qDeleteEvent.executeUpdate();
		deleteEventDTO.setEvent_RowsDeleted_String(delRowCount.toString());
		
		return deleteEventDTO;															}
	
	@Transactional(readOnly = false)
	public DeleteGamer2EventDTO deleteGamer2Event(DeleteGamer2EventDTO deleteGamer2EventDTO) {
		
		//	Remove the Gamer from the Event
		Integer delRowCount;
		Long evId = deleteGamer2EventDTO.getEventId();
		Long grId = deleteGamer2EventDTO.getGamerId();
		Query qDeleteGamer2Event = em.createNativeQuery(
				"delete from gamer_event where gamer_event.gamer_id = ? and gamer_event.event_id = ?");
		qDeleteGamer2Event.setParameter(1, grId);
		qDeleteGamer2Event.setParameter(2, evId);
		delRowCount = qDeleteGamer2Event.executeUpdate();
		deleteGamer2EventDTO.setRowsDeleted_string(delRowCount.toString());

		//	If there are no more Gamers left in the Event, Cancel it (delete it)
		Query qAnyGamersLeftInEvent = em.createNativeQuery(
				"select count(gamer_event.event_id) from gamer_event where gamer_event.event_id = ?");
		qAnyGamersLeftInEvent.setParameter(1, evId);
		@SuppressWarnings("unchecked")
		List<Long> resultList = qAnyGamersLeftInEvent.getResultList();
		if(resultList.get(0)==0)	{	
			deleteGamer2EventDTO.setOpResult("No Gamers Left > Deleting Event");
			deleteEvent(evId);														}
		
		return deleteGamer2EventDTO;												}

	
//  ------------------------------------------------------------------------------------------
//  ----- [  Delete Games  ]----------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	

	
//  ------------------------------------------------------------------------------------------
//  ----- [  Delete Gamers  ]---------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	

	
//  ------------------------------------------------------------------------------------------
//  ----- [  Delete Locations  ]------------------------------------------------------------
//  ------------------------------------------------------------------------------------------

	
//  ------------------------------------------------------------------------------------------
//  ----- [  Delete Humans  ]---------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	

	
	
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



	
	
}	//  End of GameUpDeleteService
