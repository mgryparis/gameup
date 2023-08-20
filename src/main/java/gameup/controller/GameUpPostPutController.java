package gameup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import gameup.service.GameupPostPutService;
import lombok.extern.slf4j.Slf4j;
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

@RestController
@RequestMapping("/gameup")
@Slf4j
public class GameUpPostPutController {
	@Autowired
	private GameupPostPutService gameupPostPutService;
		
	//  @POST/@PUT Events --------------------------------------------------------
	
	//  Add an event
	@PostMapping("/event")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EventDTO createEvent(@RequestBody EventDTO eventDTO)	{
		log.info("Creating Event {}", eventDTO);
		return gameupPostPutService.saveEvent(eventDTO);					}
	
	//  Edit an event
	@PutMapping("/event/{eventId}")
	public EventDTO updateEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO) {
		eventDTO.setEventId(eventId);
		log.info("Updating Event {}", eventDTO);
		return gameupPostPutService.saveEvent(eventDTO);						}

	//  Update event to include a new game
	@PostMapping("/event/addGame")
	public Event2GameDTO addGame2Event(@RequestBody Event2GameDTO event2GameDTO) {
		log.info("Adding Game " + event2GameDTO.getGameId()
					+ " to Event " + event2GameDTO.getEventId());
		return gameupPostPutService.saveEvent2Game(event2GameDTO);						}
	
	//  Update event as include a new location
	@PostMapping("/event/addLocation")
	public Event2LocationDTO addLocation2Event(@RequestBody Event2LocationDTO event2LocationDTO) {
		log.info("Adding Game " + event2LocationDTO.getLocationId()
					+ " to Event " + event2LocationDTO.getEventId());
		return gameupPostPutService.saveEvent2Location(event2LocationDTO);						}
	
	//  @POST/@PUT Games ---------------------------------------------------------
	
	//  Add a game
	@PostMapping("/game")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GameDTO createGame(@RequestBody GameDTO gameDTO)	{
		log.info("Creating Game {}", gameDTO);
		return gameupPostPutService.saveGame(gameDTO);					}
	
	//  Edit a game
	@PutMapping("/game/{gameId}")
	public GameDTO updateGame(@PathVariable Long gameId, @RequestBody GameDTO gameDTO) {
		gameDTO.setGameId(gameId);
		log.info("Updating Game {}", gameDTO);
		return gameupPostPutService.saveGame(gameDTO);						}
	
	//  @POST/@PUT Gamers --------------------------------------------------------
	
	//  Add a gamer
	@PostMapping("/gamer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GamerDTO createGamer(@RequestBody GamerDTO gamerDTO)	{
		log.info("Creating Gamer {}", gamerDTO);
		return gameupPostPutService.saveGamer(gamerDTO);								}
	
	//  Edit a gamer
	@PutMapping("/gamer/{gamerId}")
	public GamerDTO updateGamer(@PathVariable Long gamerId, @RequestBody GamerDTO gamerDTO) {
		gamerDTO.setGamerId(gamerId);
		log.info("Updating Gamer {}", gamerDTO);
		return gameupPostPutService.saveGamer(gamerDTO);								}
	
	//  Register gamer's interest in a game
	@PostMapping("/gamer/addGame")
	public Gamer2GameDTO addGame2Gamer(@RequestBody Gamer2GameDTO gamer2GameDTO) {
		log.info("Adding Game " + gamer2GameDTO.getGameId()
					+ " to Gamer " + gamer2GameDTO.getGamerId());
		return gameupPostPutService.saveGamer2Game(gamer2GameDTO);						}
	
	//  Register gamer for an event
	@PostMapping("/gamer/addEvent")
	public Gamer2EventDTO addEvent2Gamer(@RequestBody Gamer2EventDTO gamer2EventDTO) {
		log.info("Adding Event " + gamer2EventDTO.getEventId()
					+ " to Gamer " + gamer2EventDTO.getGamerId());
		return gameupPostPutService.saveGamer2Event(gamer2EventDTO);					}
		
	//  Register gamer as a location host
	@PostMapping("/gamer/addLocation")
	public Gamer2LocationDTO addLocation2Gamer(@RequestBody Gamer2LocationDTO gamer2LocationDTO) {
		log.info("Adding Location " + gamer2LocationDTO.getLocationId()
					+ " to Gamer " + gamer2LocationDTO.getGamerId());
		return gameupPostPutService.saveGamer2Location(gamer2LocationDTO);					}
	
	//  @POST/@PUT Locations -----------------------------------------------------

	//  Add a location
	@PostMapping("/location")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LocationDTO createLocation(@RequestBody LocationDTO locationDTO)	{
		log.info("Creating Location {}", locationDTO);
		return gameupPostPutService.saveLocation(locationDTO);				}
	
	//  Edit a location
	@PutMapping("/location/{locationId}")
	public LocationDTO updateLocation(@PathVariable Long locationId, @RequestBody LocationDTO locationDTO) {
		locationDTO.setLocationId(locationId);
		log.info("Updating Location {}", locationDTO);
		return gameupPostPutService.saveLocation(locationDTO);						}
	
	//  @POST/@PUT Humans --------------------------------------------------------

	//  Add a human
	@PostMapping("/human")
	@ResponseStatus(code = HttpStatus.CREATED)
	public HumanDTO createHuman(@RequestBody HumanDTO humanDTO)	{
		log.info("Creating Human{}", humanDTO);
		return gameupPostPutService.saveHuman(humanDTO);				}
	
	//  Edit a human
	@PutMapping("/human/{humanId}")
	public HumanDTO updateHuman(@PathVariable Long humanId, @RequestBody HumanDTO humanDTO) {
		humanDTO.setHumanId(humanId);
		log.info("Updating Human{}", humanDTO);
		return gameupPostPutService.saveHuman(humanDTO);						}
	
	//  Register human as a location POC
	@PutMapping("/human/addLocation")
	public Human2LocationDTO addHuman2Location(@RequestBody Human2LocationDTO human2LocationDTO) {
		log.info("Mapping Location " + human2LocationDTO.getLocationId()
					+ " to Human " + human2LocationDTO.getHumanId());
		return gameupPostPutService.saveHuman2Location(human2LocationDTO);					}
	
	//  Register human as a gamer's identity
	@PutMapping("/human/addGamer")
	public Human2GamerDTO addHuman2Gamer(@RequestBody Human2GamerDTO human2GamerDTO) {
		log.info("Mapping Gamer " + human2GamerDTO.getGamerId()
					+ " to Human " + human2GamerDTO.getHumanId());
		return gameupPostPutService.saveHuman2Gamer(human2GamerDTO);					}

}	//  End of GameUpPostPutController Class
