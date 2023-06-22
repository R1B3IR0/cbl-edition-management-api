package enumerations;

public enum Rank {
    GOLD,
    SILVER,
    BRONZE;

    private Rank() {
    }

    public String toString() {
        switch (this) {
            case GOLD:
                return "Gold";
            case SILVER:
                return "Silver";
            case BRONZE:
                return "Bronze";
            default:
                return "No rank";
        }
    }
}


