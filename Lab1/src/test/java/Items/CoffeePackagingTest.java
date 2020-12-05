package Items;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CoffeePackagingTest {

    @Test
    public void BagsPriceTest() {
        CoffeePackaging packaging = CoffeePackaging.BAGS;

        Assertions.assertEquals(5, packaging.price((int) (100 * Math.random())));
        Assertions.assertEquals(5, packaging.price((int) (100 * Math.random())));
        Assertions.assertThrows(IllegalArgumentException.class, () -> packaging.price(-(int) (100 * Math.random())));
    }

    @Test
    public void JarPriceTest() {
        CoffeePackaging packaging = CoffeePackaging.JAR;
        int volume = (int) (100 * Math.random());

        Assertions.assertEquals(20 + (double) volume / 100, packaging.price(volume));
        Assertions.assertEquals(20 + 4 * (double) volume/ 100, packaging.price(volume * 4));
        Assertions.assertThrows(IllegalArgumentException.class, () -> packaging.price(-volume));
    }
}