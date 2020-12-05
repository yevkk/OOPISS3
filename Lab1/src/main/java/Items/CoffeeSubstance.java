package Items;

public enum CoffeeSubstance {
    BEANS, GROUND, INSTANT;

    public double priceMultiplier(double price) {
        return switch (this) {
            case BEANS -> price;
            case GROUND -> price * 1.1;
            case INSTANT -> price * 1.2;
        };
    }
}
