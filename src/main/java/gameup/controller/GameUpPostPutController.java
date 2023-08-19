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
import gameup.controller.model.EventDTO;
import gameup.controller.model.GameDTO;
import gameup.controller.model.GamerDTO;
import gameup.controller.model.HumanDTO;
import gameup.controller.model.LocationDTO;

@RestController
@RequestMapping("/gameup")
@Slf4j
public class GameUpPostPutController {
	@Autowired
	private GameupPostPutService gameupPostPutService;
		
	//  @POST/@PUT Events --------------------------------------------------------
	
	@PostMapping("/event")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EventDTO createEvent(@RequestBody EventDTO eventDTO)	{
		log.info("Creating Event {}", eventDTO);
		return gameupPostPutService.saveEvent(eventDTO);					}
	
	@PutMapping("/event/{eventId}")
	public EventDTO updateEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO) {
		eventDTO.setEventId(eventId);
		log.info("Updating location {}", eventDTO);
		return gameupPostPutService.saveEvent(eventDTO);						}
	
	//  @POST/@PUT Games ---------------------------------------------------------
	
	@PostMapping("/game")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GameDTO createGame(@RequestBody GameDTO gameDTO)	{
		log.info("Creating Game {}", gameDTO);
		return gameupPostPutService.saveGame(gameDTO);					}
	
	@PutMapping("/game/{gameId}")
	public GameDTO updateGame(@PathVariable Long gameId, @RequestBody GameDTO gameDTO) {
		gameDTO.setGameId(gameId);
		log.info("Updating Game {}", gameDTO);
		return gameupPostPutService.saveGame(gameDTO);						}
	
	//  @POST/@PUT Gamers --------------------------------------------------------
	
	@PostMapping("/gamer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GamerDTO createGamer(@RequestBody GamerDTO gamerDTO)	{
		log.info("Creating Game {}", gamerDTO);
		return gameupPostPutService.saveGamer(gamerDTO);				}
	
	@PutMapping("/gamer/{gamerId}")
	public GamerDTO updateGamer(@PathVariable Long gamerId, @RequestBody GamerDTO gamerDTO) {
		gamerDTO.setGamerId(gamerId);
		log.info("Updating Gamer {}", gamerDTO);
		return gameupPostPutService.saveGamer(gamerDTO);						}
	
	//  @POST/@PUT Locations -----------------------------------------------------

	@PostMapping("/location")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LocationDTO createLocation(@RequestBody LocationDTO locationDTO)	{
		log.info("Creating Location {}", locationDTO);
		return gameupPostPutService.saveLocation(locationDTO);				}
	
	@PutMapping("/location/{locationId}")
	public LocationDTO updateLocation(@PathVariable Long locationId, @RequestBody LocationDTO locationDTO) {
		locationDTO.setLocationId(locationId);
		log.info("Updating Location {}", locationDTO);
		return gameupPostPutService.saveLocation(locationDTO);						}
	
	//  @POST/@PUT Humans --------------------------------------------------------

	@PostMapping("/human")
	@ResponseStatus(code = HttpStatus.CREATED)
	public HumanDTO createHuman(@RequestBody HumanDTO humanDTO)	{
		log.info("Creating Human{}", humanDTO);
		return gameupPostPutService.saveHuman(humanDTO);				}
	
	@PutMapping("/human/{humanId}")
	public HumanDTO updateHuman(@PathVariable Long humanId, @RequestBody HumanDTO humanDTO) {
		humanDTO.setHumanId(humanId);
		log.info("Updating Human{}", humanDTO);
		return gameupPostPutService.saveHuman(humanDTO);						}

}	//  End of GameUpPostPutController Class
