package Trading;

import Items.*;
import Trading.Exceptions.EmptyCoffeBox;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CoffeeBoxTest {
    @Test
    public void ArabicaGroundJar10Test() {
        var item = new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.JAR, 400);
        int number = 10;

        var box = new CoffeeBox(item, number);

        Assertions.assertEquals(item, box.getItem());
        Assertions.assertEquals(number, box.getNumber());
        Assertions.assertEquals(number * 400, box.volume());
        Assertions.assertEquals(number * 354, box.cost());
    }

    @Test
    public void RobustaBeansBags250Test() {
        var item = new CoffeeItem(CoffeeSort.ROBUSTA, CoffeeSubstance.BEANS, CoffeePackaging.BAGS, 10);
        int number = 250;

        var box = new CoffeeBox(item, number);

        Assertions.assertEquals(item, box.getItem());
        Assertions.assertEquals(number, box.getNumber());
        Assertions.assertEquals(number * 10, box.volume());
        Assertions.assertEquals(number * 10, box.cost());
    }

    @Test
    public void BourbonInstantJar30Test() {
        var item = new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.INSTANT, CoffeePackaging.JAR, 350);
        int number = 30;

        var box = new CoffeeBox(item, number);

        Assertions.assertEquals(item, box.getItem());
        Assertions.assertEquals(number, box.getNumber());
        Assertions.assertEquals(number * 350, box.volume());
        Assertions.assertEquals(number * 548.5, box.cost());

    }

    @Test
    public void ItemConsumingTest() throws EmptyCoffeBox {
        int number = 10;

        var box = new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.INSTANT, CoffeePackaging.JAR, 350), number);

        for (int i = number; i > 0; i--) {
            Assertions.assertEquals(i, box.getNumber());
            box.consumeItem();
        }
    }

    @Test
    public void RunningOutOfItemsTest() throws EmptyCoffeBox {
        int number = 2;

        var box = new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.INSTANT, CoffeePackaging.JAR, 350), number);
        for (int i = number; i > 0; i--) {
            Assertions.assertEquals(i, box.getNumber());
            box.consumeItem();
        }

        Assertions.assertThrows(EmptyCoffeBox.class, box::consumeItem);
    }

    @Test
    public void NegativeNumberTest() {
        var item = new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.INSTANT, CoffeePackaging.JAR, 350);
        int number = - 30;

        Assertions.assertThrows(IllegalArgumentException.class, () -> new CoffeeBox(item, number));
    }
}