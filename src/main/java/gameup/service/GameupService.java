package gameup.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gameup.controller.model.EventDTO;
import gameup.controller.model.GamerDTO;
import gameup.dao.EventDao;
import gameup.dao.GameDao;
import gameup.dao.GamerDao;
import gameup.dao.HumanDao;
import gameup.dao.LocationDao;
import gameup.entity.Event;
import gameup.entity.Gamer;


@Service
public class GameupService {
	@Autowired private EventDao eventDao;
	@Autowired private GameDao gameDao;
	@Autowired private GamerDao gamerDao;
	@Autowired private HumanDao humanDao;
	@Autowired private LocationDao locationDao;
	
	//  =====[  Retrieve Entities  ]=======================================================
	
	@Transactional(readOnly = true)
	public List<EventDTO> retrieveAllEvents()	{
		List<Event> eventList = eventDao.findAll();
		List<EventDTO> eventDTOList = new LinkedList<>();
		for(Event ev : eventList)	{
			EventDTO evDTO = new EventDTO();
			evDTO = retrieveEventById(ev.getEventId());			
			eventDTOList.add(evDTO);										}
		return eventDTOList;												}
	
	@Transactional(readOnly = true)
	public EventDTO retrieveEventById(Long eventId) {
		System.out.println(">>>>>    You have reached retrieveEventById()");
		Event event = findEventById(eventId);
		Set<Gamer> gamerSet = event.getGamersRegisteredFor();
		System.out.println(">>>>>    gamerSet = " + gamerSet);
		Set<Gamer> scrubbedGamerSet = new HashSet<>();
			for(Gamer gr : gamerSet)	{
				Gamer scrubbedGamer = new Gamer();
				scrubbedGamer = retrieveGamerById(gr.getGamerId());
				System.out.println(">>>>>    scrubbedGamer = " + scrubbedGamer);
				scrubbedGamerSet.add(scrubbedGamer);						}
		System.out.println(">>>>>    scrubbedGamerSet = " + scrubbedGamerSet);
		event.setGamersRegisteredFor(scrubbedGamerSet);
		event.getGamesIncludedIn().clear();
		event.getLocationsScheduledAt().clear();
		return new EventDTO(event);											}

	private Gamer retrieveGamerById(Long gamerId) {
		System.out.println(">>>>>    You have reached findGamerByID()");
		return gamerDao.findById(gamerId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Location with ID = "	+ gamerId + " was not found ]"));	}

	private Event findEventById(Long eventId) {
		System.out.println(">>>>>    You have reached findEventByID()");
		return eventDao.findById(eventId).orElseThrow(
				() -> new NoSuchElementException(
				"[ Location with ID = "	+ eventId + " was not found ]"));	}


}	//  End of GameupService Class
