package interfaces;

public interface Event_CRUD {

    String addEvent(Event event);

    void removeEvent(String var1);

    Event getEvent(String var1);

    Event[] getEvents();
}