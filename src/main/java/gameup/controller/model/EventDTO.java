package gameup.controller.model;

import gameup.entity.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

//  EventDTO Class with hidden, Lombok-generated Get/Setters (@NoArgsConstructor)
@Data
@NoArgsConstructor
public class EventDTO {
	private Long eventId;
	private String eventName;
	private String eventLevel;
	private String eventDate;
	private String eventStarttime;
	private String eventEndtime;

	//  Constructor that generates a EventDTO instance from a Event Entity instance
	public EventDTO(Event event)	{
		this.eventId 			= event.getEventId();
		this.eventName			= event.getEventName();
		this.eventLevel 		= event.getEventLevel();
		this.eventDate 			= event.getEventDate();
		this.eventStarttime 	= event.getEventStarttime();
		this.eventEndtime 		= event.getEventEndtime();			}
	
	//  Method on EventDTO that returns the corresponding Event Entity instance
	public Event toEvent()	{
		Event event = new Event();
		event.setEventId(eventId);
		event.setEventName(eventName);
		event.setEventLevel(eventLevel);
		event.setEventDate(eventDate);
		event.setEventStarttime(eventStarttime);
		event.setEventEndtime(eventEndtime);
		return event;												}
	
}	//  End of EventDTO Class
