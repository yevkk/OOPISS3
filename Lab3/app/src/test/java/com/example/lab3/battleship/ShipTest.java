package com.example.lab3.battleship;

import com.example.lab3.battleship.exceptions.ShipException;

import org.junit.Assert;
import org.junit.Test;

public class ShipTest {
    @Test
    public void creatingWrongSize() {
        Assert.assertThrows(ShipException.class, () -> new Ship(-2, Ship.Orientation.VERTICAL, 0, 0));
        Assert.assertThrows(ShipException.class, () -> new Ship(0, Ship.Orientation.VERTICAL, 0, 0));
        Assert.assertThrows(ShipException.class, () -> new Ship(GameSetup.boardSize + 1, Ship.Orientation.VERTICAL, 0, 0));
        Assert.assertThrows(ShipException.class, () -> new Ship(GameSetup.boardSize + 3, Ship.Orientation.VERTICAL, 0, 0));
    }

    @Test
    public void creatingWrongCoordsSingle() {
        Assert.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, -1, 0));
        Assert.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, GameSetup.boardSize, 0));
        Assert.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, GameSetup.boardSize + 3, 0));
    }

    @Test
    public void creatingWrongCoordsRangeMin() {
        Assert.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, -1));
        Assert.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize));
        Assert.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize + 3));
    }

    @Test
    public void creatingWrongCoordsRangeMax() {
        Assert.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize - 1));
        Assert.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize - 1));
        Assert.assertThrows(ShipException.class, () -> new Ship(4, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize - 2));
    }

    @Test
    public void creatingSize1H() {
        try {
            Ship ship = new Ship(1, Ship.Orientation.HORIZONTAL, 4, 3);

            Assert.assertEquals(1, ship.getSize());
            Assert.assertEquals(Ship.Orientation.HORIZONTAL, ship.getOrientation());
            Assert.assertEquals(4, ship.getCoordsSingle());
            Assert.assertEquals(3, ship.getCoordsRangeMin());
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void creatingSize2V() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.VERTICAL, 6, 5);

            Assert.assertEquals(2, ship.getSize());
            Assert.assertEquals(Ship.Orientation.VERTICAL, ship.getOrientation());
            Assert.assertEquals(6, ship.getCoordsSingle());
            Assert.assertEquals(5, ship.getCoordsRangeMin());
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void creatingSize3H() {
        try {
            Ship ship = new Ship(3, Ship.Orientation.HORIZONTAL, 6, 6);

            Assert.assertEquals(3, ship.getSize());
            Assert.assertEquals(Ship.Orientation.HORIZONTAL, ship.getOrientation());
            Assert.assertEquals(6, ship.getCoordsSingle());
            Assert.assertEquals(6, ship.getCoordsRangeMin());
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void creatingSize4V() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.VERTICAL, 3, 0);

            Assert.assertEquals(4, ship.getSize());
            Assert.assertEquals(Ship.Orientation.VERTICAL, ship.getOrientation());
            Assert.assertEquals(3, ship.getCoordsSingle());
            Assert.assertEquals(0, ship.getCoordsRangeMin());
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void placementSize2V() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.VERTICAL, 6, 5);

            Assert.assertTrue(ship.isPlacedOn(6, 5));
            Assert.assertTrue(ship.isPlacedOn(6, 6));

            Assert.assertFalse(ship.isPlacedOn(0, 0));
            Assert.assertFalse(ship.isPlacedOn(5, 7));
            Assert.assertFalse(ship.isPlacedOn(-4, 15));
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void placementSize4H() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.HORIZONTAL, 3, 0);

            Assert.assertTrue(ship.isPlacedOn(0, 3));
            Assert.assertTrue(ship.isPlacedOn(1, 3));
            Assert.assertTrue(ship.isPlacedOn(2, 3));
            Assert.assertTrue(ship.isPlacedOn(3, 3));

            Assert.assertFalse(ship.isPlacedOn(0, 0));
            Assert.assertFalse(ship.isPlacedOn(3, 4));
            Assert.assertFalse(ship.isPlacedOn(-1, 3));
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void shootInTarget2V() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.VERTICAL, 6, 5);

            Assert.assertTrue(ship.shoot(6, 5));
            Assert.assertTrue(ship.shoot(6, 6));

            Assert.assertFalse(ship.shoot(6, 5));
            Assert.assertFalse(ship.shoot(6, 6));
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void shootInTarget4H() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.HORIZONTAL, 3, 0);

            Assert.assertTrue(ship.shoot(0, 3));
            Assert.assertTrue(ship.shoot(1, 3));
            Assert.assertTrue(ship.shoot(2, 3));
            Assert.assertTrue(ship.shoot(3, 3));

            Assert.assertFalse(ship.shoot(0, 3));
            Assert.assertFalse(ship.shoot(1, 3));
            Assert.assertFalse(ship.shoot(2, 3));
            Assert.assertFalse(ship.shoot(3, 3));
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void shootMiss2V() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.VERTICAL, 6, 5);

            Assert.assertFalse(ship.shoot(0, 0));
            Assert.assertFalse(ship.shoot(5, 7));
            Assert.assertFalse(ship.shoot(-4, 15));
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void shootMiss4H() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.HORIZONTAL, 3, 0);

            Assert.assertFalse(ship.shoot(0, 0));
            Assert.assertFalse(ship.shoot(3, 4));
            Assert.assertFalse(ship.shoot(-1, 3));
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void idDeadSize2H() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.HORIZONTAL, 5, 6);

            ship.shoot(6, 5);
            Assert.assertFalse(ship.isDead());
            ship.shoot(7, 5);
            Assert.assertTrue(ship.isDead());
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }

    @Test
    public void isDeadSize4V() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.VERTICAL, 0, 3);

            ship.shoot(0, 3);
            Assert.assertFalse(ship.isDead());
            ship.shoot(0, 4);
            Assert.assertFalse(ship.isDead());
            ship.shoot(0, 5);
            Assert.assertFalse(ship.isDead());
            ship.shoot(0, 6);
            Assert.assertTrue(ship.isDead());
        } catch (ShipException e) {
            Assert.fail("unexpected ShipException");
        }
    }
}