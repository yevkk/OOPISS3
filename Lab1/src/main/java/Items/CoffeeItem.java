package Items;

public class CoffeeItem {
    private final CoffeeSort sort;
    private final CoffeeSubstance substance;
    private final CoffeePackaging packaging;
    private final int volume;

    public CoffeeItem(CoffeeSort sort, CoffeeSubstance substance, CoffeePackaging packaging, int volume) {
        this.sort = sort;
        this.substance = substance;
        this.packaging = packaging;
        this.volume = volume;
    }

    public CoffeeSort getSort() {
        return sort;
    }

    public CoffeeSubstance getSubstance() {
        return substance;
    }

    public CoffeePackaging getPackaging() {
        return packaging;
    }

    public int getVolume() {
        return volume;
    }

    public double price() {
        return substance.priceMultiplier(sort.price(volume)) + packaging.price(volume);
    }
}
