package enumerations;

public enum EventType {
    KICK_OFF_MEETINGS,
    PITCHES,
    BOOTCAMPS;

    private EventType() {
    }

    public String toString() {
        switch (this) {
            case KICK_OFF_MEETINGS:
                return "Kick-off meetings";
            case PITCHES:
                return "Pitches";
            case BOOTCAMPS:
                return "Bootcamps";
            default:
                return "Unknown";
        }
    }

}


