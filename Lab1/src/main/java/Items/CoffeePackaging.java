package Items;
import java.lang.IllegalArgumentException;

public enum CoffeePackaging {
    JAR, BAGS;

    public double price(int volume) {
        if (volume < 0) {
            throw new IllegalArgumentException("volume should have be non-negative value");
        }
        return switch (this) {
            case JAR -> 5;
            case BAGS -> 20 + (double) volume / 100;
        };
    }
}
