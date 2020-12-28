package com.example.lab3.battleship;

import com.example.lab3.battleship.exceptions.ShipException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShipTest {
    @Test
    public void creatingWrongSize() {
        Assertions.assertThrows(ShipException.class, () -> new Ship(-2, Ship.Orientation.VERTICAL, 0, 0));
        Assertions.assertThrows(ShipException.class, () -> new Ship(0, Ship.Orientation.VERTICAL, 0, 0));
        Assertions.assertThrows(ShipException.class, () -> new Ship(GameSetup.boardSize + 1, Ship.Orientation.VERTICAL, 0, 0));
        Assertions.assertThrows(ShipException.class, () -> new Ship(GameSetup.boardSize + 3, Ship.Orientation.VERTICAL, 0, 0));
    }

    @Test
    public void creatingWrongCoordsSingle() {
        Assertions.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, -1, 0));
        Assertions.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, GameSetup.boardSize, 0));
        Assertions.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, GameSetup.boardSize + 3, 0));
    }

    @Test
    public void creatingWrongCoordsRangeMin() {
        Assertions.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, -1));
        Assertions.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize));
        Assertions.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize + 3));
    }

    @Test
    public void creatingWrongCoordsRangeMax() {
        Assertions.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize - 1));
        Assertions.assertThrows(ShipException.class, () -> new Ship(2, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize - 1));
        Assertions.assertThrows(ShipException.class, () -> new Ship(4, Ship.Orientation.VERTICAL, 0, GameSetup.boardSize - 2));
    }

    @Test
    public void creatingSize1H() {
        try {
            Ship ship = new Ship(1, Ship.Orientation.HORIZONTAL, 4, 3);

            Assertions.assertEquals(1, ship.getSize());
            Assertions.assertEquals(Ship.Orientation.HORIZONTAL, ship.getOrientation());
            Assertions.assertEquals(4, ship.getCoordsSingle());
            Assertions.assertEquals(3, ship.getCoordsRangeMin());
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void creatingSize2V() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.VERTICAL, 6, 5);

            Assertions.assertEquals(2, ship.getSize());
            Assertions.assertEquals(Ship.Orientation.VERTICAL, ship.getOrientation());
            Assertions.assertEquals(6, ship.getCoordsSingle());
            Assertions.assertEquals(5, ship.getCoordsRangeMin());
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void creatingSize3H() {
        try {
            Ship ship = new Ship(3, Ship.Orientation.HORIZONTAL, 6, 6);

            Assertions.assertEquals(3, ship.getSize());
            Assertions.assertEquals(Ship.Orientation.HORIZONTAL, ship.getOrientation());
            Assertions.assertEquals(6, ship.getCoordsSingle());
            Assertions.assertEquals(6, ship.getCoordsRangeMin());
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void creatingSize4V() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.VERTICAL, 3, 0);

            Assertions.assertEquals(4, ship.getSize());
            Assertions.assertEquals(Ship.Orientation.VERTICAL, ship.getOrientation());
            Assertions.assertEquals(3, ship.getCoordsSingle());
            Assertions.assertEquals(0, ship.getCoordsRangeMin());
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void placementSize2V() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.VERTICAL, 6, 5);

            Assertions.assertTrue(ship.isPlacedOn(6, 5));
            Assertions.assertTrue(ship.isPlacedOn(6, 6));

            Assertions.assertFalse(ship.isPlacedOn(0, 0));
            Assertions.assertFalse(ship.isPlacedOn(5, 7));
            Assertions.assertFalse(ship.isPlacedOn(-4, 15));
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void placementSize4H() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.HORIZONTAL, 3, 0);

            Assertions.assertTrue(ship.isPlacedOn(0, 3));
            Assertions.assertTrue(ship.isPlacedOn(1, 3));
            Assertions.assertTrue(ship.isPlacedOn(2, 3));
            Assertions.assertTrue(ship.isPlacedOn(3, 3));

            Assertions.assertFalse(ship.isPlacedOn(0, 0));
            Assertions.assertFalse(ship.isPlacedOn(3, 4));
            Assertions.assertFalse(ship.isPlacedOn(-1, 3));
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void shootInTarget2V() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.VERTICAL, 6, 5);

            Assertions.assertTrue(ship.shoot(6, 5));
            Assertions.assertTrue(ship.shoot(6, 6));

            Assertions.assertFalse(ship.shoot(6, 5));
            Assertions.assertFalse(ship.shoot(6, 6));
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void shootInTarget4H() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.HORIZONTAL, 3, 0);

            Assertions.assertTrue(ship.shoot(0, 3));
            Assertions.assertTrue(ship.shoot(1, 3));
            Assertions.assertTrue(ship.shoot(2, 3));
            Assertions.assertTrue(ship.shoot(3, 3));

            Assertions.assertFalse(ship.shoot(0, 3));
            Assertions.assertFalse(ship.shoot(1, 3));
            Assertions.assertFalse(ship.shoot(2, 3));
            Assertions.assertFalse(ship.shoot(3, 3));
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void shootMiss2V() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.VERTICAL, 6, 5);

            Assertions.assertFalse(ship.shoot(0, 0));
            Assertions.assertFalse(ship.shoot(5, 7));
            Assertions.assertFalse(ship.shoot(-4, 15));
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void shootMiss4H() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.HORIZONTAL, 3, 0);

            Assertions.assertFalse(ship.shoot(0, 0));
            Assertions.assertFalse(ship.shoot(3, 4));
            Assertions.assertFalse(ship.shoot(-1, 3));
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void idDeadSize2H() {
        try {
            Ship ship = new Ship(2, Ship.Orientation.HORIZONTAL, 5, 6);

            ship.shoot(6, 5);
            Assertions.assertFalse(ship.isDead());
            ship.shoot(7, 5);
            Assertions.assertTrue(ship.isDead());
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }

    @Test
    public void isDeadSize4V() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.VERTICAL, 0, 3);

            ship.shoot(0, 3);
            Assertions.assertFalse(ship.isDead());
            ship.shoot(0, 4);
            Assertions.assertFalse(ship.isDead());
            ship.shoot(0, 5);
            Assertions.assertFalse(ship.isDead());
            ship.shoot(0, 6);
            Assertions.assertTrue(ship.isDead());
        } catch (ShipException e) {
            Assertions.fail("unexpected ShipException");
        }
    }
}