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


    public Event_imp(String name, String start, String end) {
        this.name = name;
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
        this.local = "Porto";
        this.type = EventType.KICK_OFF_MEETINGS;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    @Override
    public String getLocal() {
        return local;
    }

    @Override
    public EventType getType() {
        return type;
    }

    @Override
    public String toString() {
        String text = "";

        text += "Name: " + name + "\n"
                + "Start: " + start + "\n"
                + "End: " + end + "\n"
                + "Local: " + local + "\n"
                + "Type: " + type + "\n";

        return text;
    }
}
