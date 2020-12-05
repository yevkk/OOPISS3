package Items;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CoffeeItemTest {
    @Test
    public void ArabicaGroundJarTest() {
        var sort = CoffeeSort.ARABICA;
        var substance = CoffeeSubstance.GROUND;
        var packaging = CoffeePackaging.JAR;
        int volume = 400;

        var item = new CoffeeItem(sort, substance, packaging, volume);

        Assertions.assertEquals(sort, item.getSort());
        Assertions.assertEquals(substance, item.getSubstance());
        Assertions.assertEquals(packaging, item.getPackaging());
        Assertions.assertEquals(volume, item.getVolume());
        Assertions.assertEquals(354, item.price());
    }

    @Test
    public void RobustaBeansBagsTest() {
        var sort = CoffeeSort.ROBUSTA;
        var substance = CoffeeSubstance.BEANS;
        var packaging = CoffeePackaging.BAGS;
        int volume = 10;

        var item = new CoffeeItem(sort, substance, packaging, volume);

        Assertions.assertEquals(sort, item.getSort());
        Assertions.assertEquals(substance, item.getSubstance());
        Assertions.assertEquals(packaging, item.getPackaging());
        Assertions.assertEquals(volume, item.getVolume());
        Assertions.assertEquals(10, item.price());
    }

    @Test
    public void BourbonInstantJarTest() {
        var sort = CoffeeSort.BOURBON;
        var substance = CoffeeSubstance.INSTANT;
        var packaging = CoffeePackaging.JAR;
        int volume = 350;

        var item = new CoffeeItem(sort, substance, packaging, volume);

        Assertions.assertEquals(sort, item.getSort());
        Assertions.assertEquals(substance, item.getSubstance());
        Assertions.assertEquals(packaging, item.getPackaging());
        Assertions.assertEquals(volume, item.getVolume());
        Assertions.assertEquals(548.5, item.price());
    }

    @Test
    public void NegativeVolumeTest() {
        var sort = CoffeeSort.BOURBON;
        var substance = CoffeeSubstance.INSTANT;
        var packaging = CoffeePackaging.JAR;
        int volume = -100;

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CoffeeItem(sort, substance, packaging, volume));
    }
}