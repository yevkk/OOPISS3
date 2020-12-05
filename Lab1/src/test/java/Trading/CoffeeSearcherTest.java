package Trading;

import Items.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CoffeeSearcherTest {
    CoffeeBox[] boxes;
    CoffeeSearcher searcher;

    @Before
    public void init() {
        boxes = new CoffeeBox[]{
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

        searcher = new CoffeeSearcher(boxes);
    }

    @Test
    public void sortSearchArabica() {
        var result = searcher.filterBySort(CoffeeSort.ARABICA).get();

        Assertions.assertTrue(result.contains(boxes[2].getItem()));
        Assertions.assertTrue(result.contains(boxes[4].getItem()));

        Assertions.assertFalse(result.contains(boxes[0].getItem()));
        Assertions.assertFalse(result.contains(boxes[1].getItem()));
        Assertions.assertFalse(result.contains(boxes[3].getItem()));
    }

    @Test
    public void sortSearchLiberica() {
        var result = searcher.filterBySort(CoffeeSort.LIBERICA).get();

        Assertions.assertTrue(result.contains(boxes[3].getItem()));

        Assertions.assertFalse(result.contains(boxes[0].getItem()));
        Assertions.assertFalse(result.contains(boxes[1].getItem()));
        Assertions.assertFalse(result.contains(boxes[2].getItem()));
        Assertions.assertFalse(result.contains(boxes[4].getItem()));
    }

    @Test
    public void substanceSearchInstant() {
        var result = searcher.filterBySubstance(CoffeeSubstance.INSTANT).get();

        Assertions.assertTrue(result.contains(boxes[1].getItem()));
        Assertions.assertTrue(result.contains(boxes[3].getItem()));

        Assertions.assertFalse(result.contains(boxes[0].getItem()));
        Assertions.assertFalse(result.contains(boxes[2].getItem()));
        Assertions.assertFalse(result.contains(boxes[4].getItem()));
    }

    @Test
    public void substanceSearchBeans() {
        var result = searcher.filterBySubstance(CoffeeSubstance.BEANS).get();

        Assertions.assertTrue(result.contains(boxes[0].getItem()));

        Assertions.assertFalse(result.contains(boxes[1].getItem()));
        Assertions.assertFalse(result.contains(boxes[2].getItem()));
        Assertions.assertFalse(result.contains(boxes[3].getItem()));
        Assertions.assertFalse(result.contains(boxes[4].getItem()));
    }

    @Test
    public void packagingSearchJar() {
        var result = searcher.filterByPackaging(CoffeePackaging.JAR).get();

        Assertions.assertTrue(result.contains(boxes[0].getItem()));
        Assertions.assertTrue(result.contains(boxes[2].getItem()));

        Assertions.assertFalse(result.contains(boxes[1].getItem()));
        Assertions.assertFalse(result.contains(boxes[3].getItem()));
        Assertions.assertFalse(result.contains(boxes[4].getItem()));
    }

    @Test
    public void packagingSearchBags() {
        var result = searcher.filterByPackaging(CoffeePackaging.BAGS).get();

        Assertions.assertTrue(result.contains(boxes[1].getItem()));
        Assertions.assertTrue(result.contains(boxes[3].getItem()));
        Assertions.assertTrue(result.contains(boxes[4].getItem()));

        Assertions.assertFalse(result.contains(boxes[0].getItem()));
        Assertions.assertFalse(result.contains(boxes[2].getItem()));
    }

    @Test
    public void priceSearch() {
        var result = searcher.filterByPrice(10., 15.).get();

        Assertions.assertTrue(result.contains(boxes[1].getItem()));
        Assertions.assertTrue(result.contains(boxes[3].getItem()));
        Assertions.assertTrue(result.contains(boxes[4].getItem()));

        Assertions.assertFalse(result.contains(boxes[0].getItem()));
        Assertions.assertFalse(result.contains(boxes[2].getItem()));
    }

    @Test
    public void volumeSearch() {
        var result = searcher.filterByVolume(9, 375).get();

        Assertions.assertTrue(result.contains(boxes[0].getItem()));
        Assertions.assertTrue(result.contains(boxes[1].getItem()));
        Assertions.assertTrue(result.contains(boxes[4].getItem()));

        Assertions.assertFalse(result.contains(boxes[2].getItem()));
        Assertions.assertFalse(result.contains(boxes[3].getItem()));
    }

    @Test
    public void sequentialSearch() {
        var result =
                searcher.filterByVolume(9, 375)
                        .filterByPrice(10., 15.)
                        .filterBySort(CoffeeSort.ARABICA)
                        .get();

        Assertions.assertTrue(result.contains(boxes[4].getItem()));

        Assertions.assertFalse(result.contains(boxes[0].getItem()));
        Assertions.assertFalse(result.contains(boxes[1].getItem()));
        Assertions.assertFalse(result.contains(boxes[2].getItem()));
        Assertions.assertFalse(result.contains(boxes[3].getItem()));
    }

    @Test
    public void sequentialSearchWithNulls() {
        var result =
                searcher.filterByPackaging(null)
                        .filterBySort(null)
                        .filterBySubstance(null)
                        .filterByVolume(null, null)
                        .filterByPrice(null, null)
                        .get();

        Assertions.assertTrue(result.contains(boxes[0].getItem()));
        Assertions.assertTrue(result.contains(boxes[1].getItem()));
        Assertions.assertTrue(result.contains(boxes[2].getItem()));
        Assertions.assertTrue(result.contains(boxes[3].getItem()));
        Assertions.assertTrue(result.contains(boxes[4].getItem()));
    }

    @Test
    public void emptyResult() {
        var result =
                searcher.filterByPrice(Double.POSITIVE_INFINITY, null).get();

        Assertions.assertFalse(result.contains(boxes[0].getItem()));
        Assertions.assertFalse(result.contains(boxes[1].getItem()));
        Assertions.assertFalse(result.contains(boxes[2].getItem()));
        Assertions.assertFalse(result.contains(boxes[3].getItem()));
        Assertions.assertFalse(result.contains(boxes[4].getItem()));
    }
}