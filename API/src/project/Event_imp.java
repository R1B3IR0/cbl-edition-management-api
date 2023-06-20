package project;


import enumerations.EventType;
import interfaces.Event;

import java.time.LocalDate;


public class Event_imp implements Event {

    private String name;
    private LocalDate start;
    private LocalDate end;
    private String local;
    private EventType type;

    /**
     * Constructor of the class Event_imp
     *
     * @param name  the name of the event
     * @param start the start date of the event
     * @param end   the end date of the event
     */
    public Event_imp(String name ,LocalDate start, LocalDate end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.local = "Porto";
        this.type = EventType.KICK_OFF_MEETINGS;
    }

    /**
     * Method to get the name of the event
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Method to get the start date of the event
     * @return
     */
    @Override
    public LocalDate getStart() {
        return start;
    }
    /**
     * Method to get the end date of the event
     * @return
     */
    @Override
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Method to get the local of the event
     * @return
     */
    @Override
    public String getLocal() {
        return local;
    }

    /**
     * Method to get the type of the event
     * @return
     */
    @Override
    public EventType getType() {
        return type;
    }


}
