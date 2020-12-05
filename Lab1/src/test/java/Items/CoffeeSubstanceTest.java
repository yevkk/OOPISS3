package Items;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CoffeeSubstanceTest {
    @Test
    public void BeansPriceMultiplierTest() {
        CoffeeSubstance substance = CoffeeSubstance.BEANS;
        double price = Math.random() * 1000;

        Assertions.assertEquals(substance.priceMultiplier(price), price);
        Assertions.assertEquals(substance.priceMultiplier(price * 2), price * 2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> substance.priceMultiplier(-price));
    }

    @Test
    public void GroundPriceMultiplierTest() {
        CoffeeSubstance substance = CoffeeSubstance.GROUND;
        double price = Math.random() * 1000;

        Assertions.assertEquals(substance.priceMultiplier(price), price * 1.1);
        Assertions.assertEquals(substance.priceMultiplier(price * 2), price * 2.2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> substance.priceMultiplier(-price));
    }

    @Test
    public void InstantPriceMultiplierTest() {
        CoffeeSubstance substance = CoffeeSubstance.INSTANT;
        double price = Math.random() * 1000;

        Assertions.assertEquals(substance.priceMultiplier(price), price * 1.2);
        Assertions.assertEquals(substance.priceMultiplier(price * 2), price * 2.4);
        Assertions.assertThrows(IllegalArgumentException.class, () -> substance.priceMultiplier(-price));
    }
}
