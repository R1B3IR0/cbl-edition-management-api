package interfaces;

import enumerations.EventType;

import java.time.LocalDate;

public interface Event {

    String getName();
    LocalDate getStart();
    LocalDate getEnd();
    String getLocal();
    EventType getType();


}
