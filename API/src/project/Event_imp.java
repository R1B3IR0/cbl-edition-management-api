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
     * Construtor da classe Event_imp.
     * @param name O nome do evento.
     * @param start A data de início do evento no formato de string ("yyyy-MM-dd").
     * @param end A data de término do evento no formato de string ("yyyy-MM-dd").
     */

    public Event_imp(String name, String start, String end) {
        this.name = name;
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
        this.local = "Porto";
        this.type = EventType.KICK_OFF_MEETINGS;
    }


    /**
     * Retorna o nome do evento.
     * @return O nome do evento.
     */

    @Override
    public String getName() {
        return null;
    }

    /**
     * Retorna a data de início do evento.
     * @return A data de início do evento.
     */

    @Override
    public LocalDate getStart() {
        return start;
    }

    /**
     * Retorna a data de fim do evento.
     * @return A data de fim do evento.
     */
    @Override
    public LocalDate getEnd() {
        return end;
    }

    /**
     * Retorna o local do evento.
     * @return O local do evento.
     */

    @Override
    public String getLocal() {
        return local;
    }

    /**
     * Retorna o type do evento.
     * @return O type do evento.
     */

    @Override
    public EventType getType() {
        return type;
    }

    /**
     * Retorna uma representação em formato de string do evento.
     * @return A representação em formato de string do evento.
     */

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
