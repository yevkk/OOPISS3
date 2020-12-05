package Items;

public class CoffeeItem {
    private CoffeeSort sort;
    private CoffeeSubstance substance;
    private CoffeePackaging packaging;
    private int volume;

    public CoffeeItem(CoffeeSort sort, CoffeeSubstance substance, CoffeePackaging packaging, int volume) {
        this.sort = sort;
        this.substance = substance;
        this.packaging = packaging;
        this.volume = volume;
    }
}
