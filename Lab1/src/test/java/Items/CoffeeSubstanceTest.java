package Items;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CoffeeSubstanceTest {
    @Test
    public void beansPriceMultiplier() {
        CoffeeSubstance substance = CoffeeSubstance.BEANS;
        double price = Math.random() * 1000;

        Assertions.assertEquals(price, substance.priceMultiplier(price));
        Assertions.assertEquals(price * 2, substance.priceMultiplier(price * 2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> substance.priceMultiplier(-price));
    }

    @Test
    public void groundPriceMultiplier() {
        CoffeeSubstance substance = CoffeeSubstance.GROUND;
        double price = Math.random() * 1000;

        Assertions.assertEquals(price * 1.1, substance.priceMultiplier(price));
        Assertions.assertEquals(price * 2.2, substance.priceMultiplier(price * 2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> substance.priceMultiplier(-price));
    }

    @Test
    public void instantPriceMultiplier() {
        CoffeeSubstance substance = CoffeeSubstance.INSTANT;
        double price = Math.random() * 1000;

        Assertions.assertEquals(price * 1.2, substance.priceMultiplier(price));
        Assertions.assertEquals(price * 2.4, substance.priceMultiplier(price * 2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> substance.priceMultiplier(-price));
    }
}
