package gameup.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameup.controller.model.DeleteEventDTO;
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

	public DeleteEventDTO deleteEvent(Long eventId) {
		DeleteEventDTO deleteEventDTO = new DeleteEventDTO();

		//	Check if the Event exists, Build eventCheckResult string & Abort|Continue
		if(!checkIfEventIdExists(eventId))	{	
			deleteEventDTO.setEventCheckResult("Event " + eventId + " does not exist");
			return deleteEventDTO;															}
		deleteEventDTO.setEventCheckResult("Event " + eventId + "  exists");
		
		//	Get all the gamersRegisteredFor & build the gamersRegisteredFor_String

		
		//	Get all the gamesIncludedIn & build the gamesIncludedIn_String
		//	Get all the locationsScheduledAt & build the locationsScheduledAt_String
		//	Delete the Event and its Relationships from the m2m Join tables
		return null;
	}

	
	
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Games  ]----------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	

	
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Gamers  ]---------------------------------------------------------------
//  ------------------------------------------------------------------------------------------	
	
	
	
//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Locations  ]------------------------------------------------------------
//  ------------------------------------------------------------------------------------------

//  ------------------------------------------------------------------------------------------
//  ----- [  Post/Put Humans  ]---------------------------------------------------------------
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
