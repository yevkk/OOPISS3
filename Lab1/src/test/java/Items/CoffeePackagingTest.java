package Items;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CoffeePackagingTest {

    @Test
    public void BagsPriceTest() {
        CoffeePackaging packaging = CoffeePackaging.BAGS;

        Assertions.assertEquals(packaging.price((int) (100 * Math.random())), 5);
        Assertions.assertEquals(packaging.price((int) (100 * Math.random())), 5);
        Assertions.assertThrows(IllegalArgumentException.class, () -> packaging.price(-(int) (100 * Math.random())));
    }

    @Test
    public void JarPriceTest() {
        CoffeePackaging packaging = CoffeePackaging.JAR;
        int volume = (int) (100 * Math.random());

        Assertions.assertEquals(packaging.price(volume), 20 + (double) volume / 100);
        Assertions.assertEquals(packaging.price(volume * 4), 20 + 4 * (double) volume/ 100);
        Assertions.assertThrows(IllegalArgumentException.class, () -> packaging.price(-volume));
    }
}