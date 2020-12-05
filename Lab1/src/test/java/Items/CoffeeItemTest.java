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

        Assertions.assertEquals(item.getSort(), sort);
        Assertions.assertEquals(item.getSubstance(), substance);
        Assertions.assertEquals(item.getPackaging(), packaging);
        Assertions.assertEquals(item.getVolume(), volume);
        Assertions.assertEquals(item.price(), 354);
    }

    @Test
    public void RobustaBeansBagsTest() {
        var sort = CoffeeSort.ROBUSTA;
        var substance = CoffeeSubstance.BEANS;
        var packaging = CoffeePackaging.BAGS;
        int volume = 10;

        var item = new CoffeeItem(sort, substance, packaging, volume);

        Assertions.assertEquals(item.getSort(), sort);
        Assertions.assertEquals(item.getSubstance(), substance);
        Assertions.assertEquals(item.getPackaging(), packaging);
        Assertions.assertEquals(item.getVolume(), volume);
        Assertions.assertEquals(item.price(), 10);
    }

    @Test
    public void BourbonInstantJarTest() {
        var sort = CoffeeSort.BOURBON;
        var substance = CoffeeSubstance.INSTANT;
        var packaging = CoffeePackaging.JAR;
        int volume = 350;

        var item = new CoffeeItem(sort, substance, packaging, volume);

        Assertions.assertEquals(item.getSort(), sort);
        Assertions.assertEquals(item.getSubstance(), substance);
        Assertions.assertEquals(item.getPackaging(), packaging);
        Assertions.assertEquals(item.getVolume(), volume);
        Assertions.assertEquals(item.price(), 548.5);
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