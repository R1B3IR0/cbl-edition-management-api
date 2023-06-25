package interfaces;

import enumerations.EventType;

import java.time.LocalDate;

/**
 * Interface Event que retorna:
 * -getName
 * -getStart
 * -gentEnd
 * -getLocal
 * -getType
 */
public interface Event {

    String getName();

    LocalDate getStart();

    LocalDate getEnd();

    String getLocal();

    EventType getType();

}
