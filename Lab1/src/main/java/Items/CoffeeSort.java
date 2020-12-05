package Items;

public enum CoffeeSort {
    ROBUSTA, ARABICA , LIBERICA, BOURBON;

    public double price (int volume) {
        return switch (this) {
            case ROBUSTA -> 50 * (double) volume / 100;
            case ARABICA -> 75 * (double) volume / 100;
            case LIBERICA -> 100 * (double) volume / 100;
            case BOURBON -> 125 * (double) volume / 100;
        };
    }
}
