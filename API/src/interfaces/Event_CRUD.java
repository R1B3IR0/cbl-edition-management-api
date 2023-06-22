package interfaces;

public interface Event_CRUD {

    void addEvent(Event event);

    void removeEvent(String var1);

    Event getEvent(String var1);

    Event[] getEvents();
}
