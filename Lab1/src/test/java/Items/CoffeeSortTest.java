package Items;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CoffeeSortTest {
    @Test
    public void RobustaPriceTest() {
        CoffeeSort sort = CoffeeSort.ROBUSTA;
        int volume = (int) (500 * Math.random());

        Assertions.assertEquals(sort.price(volume), 50. * volume / 100);
        Assertions.assertEquals(sort.price(volume * 2), 50. * volume * 2 / 100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> sort.price(-volume));
    }

    @Test
    public void ArabicaPriceTest() {
        CoffeeSort sort = CoffeeSort.ARABICA;
        int volume = (int) (500 * Math.random());

        Assertions.assertEquals(sort.price(volume), 75. * volume / 100);
        Assertions.assertEquals(sort.price(volume * 2), 75. * volume * 2 / 100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> sort.price(-volume));
    }

    @Test
    public void LibericaPriceTest() {
        CoffeeSort sort = CoffeeSort.LIBERICA;
        int volume = (int) (500 * Math.random());

        Assertions.assertEquals(sort.price(volume), 100. * volume / 100);
        Assertions.assertEquals(sort.price(volume * 2), 100. * volume * 2 / 100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> sort.price(-volume));
    }

    @Test
    public void BourbonPriceTest() {
        CoffeeSort sort = CoffeeSort.BOURBON;
        int volume = (int) (500 * Math.random());

        Assertions.assertEquals(sort.price(volume), 125. * volume / 100);
        Assertions.assertEquals(sort.price(volume * 2), 125. * volume * 2 / 100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> sort.price(-volume));
    }
}
