package Trading;

import Items.*;
import Trading.Exceptions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class CoffeeVanTest {
    @Test
    public void emptyVanCreation() {
        int maxVolume = (int) (10000 * Math.random());
        double maxCost = 10000 * Math.random();

        var van = new CoffeeVan(maxVolume, maxCost);

        Assertions.assertEquals(maxVolume, van.getMaxVolume());
        Assertions.assertEquals(maxCost, van.getMaxCost());
        Assertions.assertEquals(new ArrayList<>(), van.getProducts());
    }

    @Test
    public void fillingWith3() throws VanOverflow {
        int maxVolume = (int) (50000 + 20000 * Math.random());
        double maxCost = 50000 + 20000 * Math.random();
        CoffeeBox[] boxes = {
                new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.BEANS, CoffeePackaging.JAR, 350), 30),
                new CoffeeBox(new CoffeeItem(CoffeeSort.ROBUSTA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 10), 250),
                new CoffeeBox(new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.JAR, 400), 10),
        };

        var van = new CoffeeVan(maxVolume, maxCost);
        van.fill(boxes);

        Assertions.assertEquals(maxVolume, van.getMaxVolume());
        Assertions.assertEquals(maxCost, van.getMaxCost());
        for (var item : boxes) {
            Assertions.assertTrue(van.getProducts().contains(item));
        }
    }

    @Test
    public void fillingWith5() throws VanOverflow {
        int maxVolume = (int) (100000 + 20000 * Math.random());
        double maxCost = 100000 + 20000 * Math.random();
        CoffeeBox[] boxes = {
                new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.BEANS, CoffeePackaging.JAR, 350), 30),
                new CoffeeBox(new CoffeeItem(CoffeeSort.ROBUSTA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 10), 250),
                new CoffeeBox(new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.JAR, 400), 10),
                new CoffeeBox(new CoffeeItem(CoffeeSort.LIBERICA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 7), 300),
                new CoffeeBox(new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.BAGS, 10), 500)
        };

        var van = new CoffeeVan(maxVolume, maxCost);
        van.fill(boxes);

        Assertions.assertEquals(maxVolume, van.getMaxVolume());
        Assertions.assertEquals(maxCost, van.getMaxCost());
        for (var item : boxes) {
            Assertions.assertTrue(van.getProducts().contains(item));
        }
    }

    @Test
    public void costOverflow() {
        int maxVolume = (int) (20000 + 5000 * Math.random());
        double maxCost = 100;
        CoffeeBox[] boxes = {
                new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.BEANS, CoffeePackaging.JAR, 350), 30),
                new CoffeeBox(new CoffeeItem(CoffeeSort.ROBUSTA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 10), 250),
        };

        var van = new CoffeeVan(maxVolume, maxCost);

        Assertions.assertThrows(VanTotalCostOverflow.class, () -> van.fill(boxes));
    }

    @Test
    public void volumeOverflow() {
        int maxVolume = 100;
        double maxCost = 20000 + 5000 * Math.random();
        CoffeeBox[] boxes = {
                new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.BEANS, CoffeePackaging.JAR, 350), 30),
                new CoffeeBox(new CoffeeItem(CoffeeSort.ROBUSTA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 10), 250),
        };

        var van = new CoffeeVan(maxVolume, maxCost);

        Assertions.assertThrows(VanTotalVolumeOverflow.class, () -> van.fill(boxes));
    }

    @Test
    public void sortSingle() throws VanOverflow {
        int maxVolume = (int) (100000 + 20000 * Math.random());
        double maxCost = 100000 + 20000 * Math.random();
        CoffeeBox[] boxes = {
                new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.BEANS, CoffeePackaging.JAR, 350), 30)
        };

        var van = new CoffeeVan(maxVolume, maxCost, boxes);
        van.sortProducts();

        Assertions.assertEquals(0, van.getProducts().indexOf(boxes[0]));
    }

    @Test
    public void sortWithNoSwapping() throws VanOverflow {
        int maxVolume = (int) (100000 + 20000 * Math.random());
        double maxCost = 100000 + 20000 * Math.random();
        CoffeeBox[] boxes = {
                // 354 / 400 = 0.885
                new CoffeeBox(new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.JAR, 400), 10),
                // 11 / 10 = 1.1
                new CoffeeBox(new CoffeeItem(CoffeeSort.ROBUSTA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 10), 250),
                // 461 / 350 = 1.32
                new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.BEANS, CoffeePackaging.JAR, 350), 30),
                // 13.25 / 10 = 1.33
                new CoffeeBox(new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.BAGS, 10), 500),
                // 13.4 / 7 = 1.91
                new CoffeeBox(new CoffeeItem(CoffeeSort.LIBERICA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 7), 300)
        };

        var van = new CoffeeVan(maxVolume, maxCost, boxes);
        van.sortProducts();

        Assertions.assertEquals(0, van.getProducts().indexOf(boxes[0]));
        Assertions.assertEquals(1, van.getProducts().indexOf(boxes[1]));
        Assertions.assertEquals(2, van.getProducts().indexOf(boxes[2]));
        Assertions.assertEquals(3, van.getProducts().indexOf(boxes[3]));
        Assertions.assertEquals(4, van.getProducts().indexOf(boxes[4]));
    }

    @Test
    public void sortWithSwapping() throws VanOverflow {
        int maxVolume = (int) (100000 + 20000 * Math.random());
        double maxCost = 100000 + 20000 * Math.random();
        CoffeeBox[] boxes = {
                // 461 / 350 = 1.32
                new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.BEANS, CoffeePackaging.JAR, 350), 30),
                // 11 / 10 = 1.1
                new CoffeeBox(new CoffeeItem(CoffeeSort.ROBUSTA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 10), 250),
                // 354 / 400 = 0.885
                new CoffeeBox(new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.JAR, 400), 10),
                // 13.4 / 7 = 1.91
                new CoffeeBox(new CoffeeItem(CoffeeSort.LIBERICA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 7), 300),
                // 13.25 / 10 = 1.33
                new CoffeeBox(new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.BAGS, 10), 500)
        };

        var van = new CoffeeVan(maxVolume, maxCost, boxes);
        van.sortProducts();

        Assertions.assertEquals(2, van.getProducts().indexOf(boxes[0]));
        Assertions.assertEquals(1, van.getProducts().indexOf(boxes[1]));
        Assertions.assertEquals(0, van.getProducts().indexOf(boxes[2]));
        Assertions.assertEquals(4, van.getProducts().indexOf(boxes[3]));
        Assertions.assertEquals(3, van.getProducts().indexOf(boxes[4]));
    }

    @Test
    public void seraching() throws VanOverflow{
        int maxVolume = (int) (100000 + 20000 * Math.random());
        double maxCost = 100000 + 20000 * Math.random();
        CoffeeBox[] boxes = {
                // 461 / 350 = 1.32
                new CoffeeBox(new CoffeeItem(CoffeeSort.BOURBON, CoffeeSubstance.BEANS, CoffeePackaging.JAR, 350), 30),
                // 11 / 10 = 1.1
                new CoffeeBox(new CoffeeItem(CoffeeSort.ROBUSTA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 10), 250),
                // 354 / 400 = 0.885
                new CoffeeBox(new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.JAR, 400), 10),
                // 13.4 / 7 = 1.91
                new CoffeeBox(new CoffeeItem(CoffeeSort.LIBERICA, CoffeeSubstance.INSTANT, CoffeePackaging.BAGS, 7), 300),
                // 13.25 / 10 = 1.33
                new CoffeeBox(new CoffeeItem(CoffeeSort.ARABICA, CoffeeSubstance.GROUND, CoffeePackaging.BAGS, 10), 500)
        };

        var van = new CoffeeVan(maxVolume, maxCost, boxes);
        var searchResult = van.searchItems().filterBySort(CoffeeSort.ARABICA).get();

        Assertions.assertTrue(searchResult.contains(boxes[2].getItem()));
        Assertions.assertTrue(searchResult.contains(boxes[4].getItem()));

        Assertions.assertFalse(searchResult.contains(boxes[0].getItem()));
        Assertions.assertFalse(searchResult.contains(boxes[1].getItem()));
        Assertions.assertFalse(searchResult.contains(boxes[3].getItem()));
    }
}
