package enumerations;

public enum RankType {
    ONE_STAR,
    TWO_STARS,
    THREE_STARS,
    FOUR_STARS,
    FIVE_STARS;

    private RankType() {
    }

    public String toString() {
        switch (this) {
            case ONE_STAR:
                return "1 Star";
            case TWO_STARS:
                return "2 Stars";
            case THREE_STARS:
                return "3 Stars";
            case FOUR_STARS:
                return "4 Stars";
            case FIVE_STARS:
                return "5 Stars";
            default:
                return "No rank";
        }
    }
}


