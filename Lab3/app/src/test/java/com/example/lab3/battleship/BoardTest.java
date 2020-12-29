package com.example.lab3.battleship;

import com.example.lab3.battleship.exceptions.ShipException;
import com.example.lab3.battleship.exceptions.ShipPlacingException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

public class BoardTest {
    private Board board;

    @Before
    public void init() {
        board = new Board();
    }

    @Test
    public void normalPlacementOf1() {
        try {
            Ship ship = new Ship(4, Ship.Orientation.VERTICAL, 2, 2);
            board.placeShip(ship);

            BoardTestHelper.checkShipPlacement(board, ship);
            Assert.assertEquals(1, board.getShips().size());
        } catch (ShipException | ShipPlacingException e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test
    public void normalPlacementOf3() {
        try {
            List<Ship> ships = new ArrayList<>();
            ships.add(new Ship(3, Ship.Orientation.HORIZONTAL, 1, 3));
            ships.add(new Ship(1, Ship.Orientation.HORIZONTAL, 3, 8));
            ships.add(new Ship(4, Ship.Orientation.HORIZONTAL, 9, 0));
            for (Ship ship : ships) {
                board.placeShip(ship);
            }

            for (Ship ship : ships) {
                BoardTestHelper.checkShipPlacement(board, ship);
            }
            Assert.assertEquals(3, board.getShips().size());
        } catch (ShipException | ShipPlacingException e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test
    public void normalPlacementOf5() {
        try {
            List<Ship> ships = new ArrayList<>();
            ships.add(new Ship(1, Ship.Orientation.HORIZONTAL, 0, 0));
            ships.add(new Ship(3, Ship.Orientation.HORIZONTAL, 3, 1));
            ships.add(new Ship(2, Ship.Orientation.HORIZONTAL, 5, 4));
            ships.add(new Ship(2, Ship.Orientation.VERTICAL, 7, 1));
            ships.add(new Ship(4, Ship.Orientation.VERTICAL, 9, 5));

            for (Ship ship : ships) {
                board.placeShip(ship);
            }

            for (Ship ship : ships) {
                BoardTestHelper.checkShipPlacement(board, ship);
            }
            Assert.assertEquals(5, board.getShips().size());
        } catch (ShipException | ShipPlacingException e) {
            Assert.fail("unexpected exception");
        }
    }

    @Test
    public void placementOverlay() {
        BoardTestHelper.place5Ships(board);

        Assert.assertThrows(ShipPlacingException.class, () -> board.placeShip(new Ship(3, Ship.Orientation.HORIZONTAL, 0, 0)));
        Assert.assertThrows(ShipPlacingException.class, () -> board.placeShip(new Ship(2, Ship.Orientation.VERTICAL, 3, 2)));
        Assert.assertThrows(ShipPlacingException.class, () -> board.placeShip(new Ship(1, Ship.Orientation.VERTICAL, 5, 5)));
    }

    @Test
    public void placementAdjacentOverlay() {
        BoardTestHelper.place5Ships(board);

        Assert.assertThrows(ShipPlacingException.class, () -> board.placeShip(new Ship(3, Ship.Orientation.HORIZONTAL, 4, 1)));
        Assert.assertThrows(ShipPlacingException.class, () -> board.placeShip(new Ship(2, Ship.Orientation.HORIZONTAL, 4, 7)));
        Assert.assertThrows(ShipPlacingException.class, () -> board.placeShip(new Ship(1, Ship.Orientation.HORIZONTAL, 1, 6)));
    }

    @Test
    public void shipsRemovalSome() {
        BoardTestHelper.place5Ships(board);
        Ship[] shipSequence = {
                board.getShips().get(1),
                board.getShips().get(3),
                board.getShips().get(4)
        };

        for (int i = 0; i < 3; i++) {
            Ship ship = shipSequence[i];
            int x = (ship.getOrientation() == Ship.Orientation.HORIZONTAL) ? ship.getCoordsRangeMin() : ship.getCoordsSingle();
            int y = (ship.getOrientation() == Ship.Orientation.HORIZONTAL) ? ship.getCoordsSingle() : ship.getCoordsRangeMin();
            Assert.assertEquals(ship, board.removeShipOn(x, y));
            BoardTestHelper.checkShipRemoval(board, ship);
            Assert.assertEquals(4 - i, board.getShips().size());
        }
    }

    @Test
    public void shipsRemovalAll() {
        BoardTestHelper.place5Ships(board);
        Ship[] shipSequence = {
                board.getShips().get(3),
                board.getShips().get(4),
                board.getShips().get(2),
                board.getShips().get(0),
                board.getShips().get(1)
        };

        for (int i = 0; i < 3; i++) {
            Ship ship = shipSequence[i];
            int x = (ship.getOrientation() == Ship.Orientation.HORIZONTAL) ? ship.getCoordsRangeMin() : ship.getCoordsSingle();
            int y = (ship.getOrientation() == Ship.Orientation.HORIZONTAL) ? ship.getCoordsSingle() : ship.getCoordsRangeMin();
            Assert.assertEquals(ship, board.removeShipOn(x, y));
            BoardTestHelper.checkShipRemoval(board, ship);
            Assert.assertEquals(4 - i, board.getShips().size());
        }
    }

    @Test
    public void shipsRemovalNullReturn() {
        BoardTestHelper.place5Ships(board);

        Assert.assertNull(board.removeShipOn(1, 2));
        Assert.assertNull(board.removeShipOn(4, 3));
        Assert.assertNull(board.removeShipOn(4, 4));
        Assert.assertNull(board.removeShipOn(4, 7));
        Assert.assertNull(board.removeShipOn(9, 9));
    }

    @Test
    public void shootMissed() {
        BoardTestHelper.place5Ships(board);

        Assert.assertEquals(Board.ShotResult.MISS, board.shoot(9, 0));
        Assert.assertEquals(Board.ShotResult.MISS, board.shoot(8, 1));
        Assert.assertEquals(Board.ShotResult.MISS, board.shoot(6, 2));
        Assert.assertEquals(Board.ShotResult.MISS, board.shoot(3, 7));
    }

    @Test
    public void shootInTarget() {
        BoardTestHelper.place5Ships(board);

        Assert.assertEquals(Board.ShotResult.IN_TARGET, board.shoot(2, 3));
        Assert.assertEquals(Board.ShotResult.IN_TARGET, board.shoot(7, 1));
        Assert.assertEquals(Board.ShotResult.IN_TARGET, board.shoot(9, 6));
        Assert.assertEquals(Board.ShotResult.IN_TARGET, board.shoot(9, 8));
    }

    @Test
    public void shootInTargetWithDestroying() {
        BoardTestHelper.place5Ships(board);

        Assert.assertEquals(Board.ShotResult.DESTROYED, board.shoot(0, 0));

        Assert.assertEquals(Board.ShotResult.IN_TARGET, board.shoot(4, 5));
        Assert.assertEquals(Board.ShotResult.DESTROYED, board.shoot(5, 5));

        Assert.assertEquals(Board.ShotResult.IN_TARGET, board.shoot(2, 3));
        Assert.assertEquals(Board.ShotResult.IN_TARGET, board.shoot(3, 3));
        Assert.assertEquals(Board.ShotResult.DESTROYED, board.shoot(1, 3));
    }

    @Test
    public void shootOutOfBoard() {
        BoardTestHelper.place5Ships(board);

        Assert.assertEquals(Board.ShotResult.FAILED, board.shoot(10, 10));
        Assert.assertEquals(Board.ShotResult.FAILED, board.shoot(-1, 6));
        Assert.assertEquals(Board.ShotResult.FAILED, board.shoot(6, 15));
        Assert.assertEquals(Board.ShotResult.FAILED, board.shoot(2, 20));
    }

    @Test
    public void shootOnShotCell() {
        BoardTestHelper.place5Ships(board);

        Assert.assertNotEquals(Board.ShotResult.FAILED, board.shoot(4, 0));
        Assert.assertNotEquals(Board.ShotResult.FAILED, board.shoot(1, 6));
        Assert.assertNotEquals(Board.ShotResult.FAILED, board.shoot(9, 5));

        Assert.assertEquals(Board.ShotResult.FAILED, board.shoot(4, 0));
        Assert.assertEquals(Board.ShotResult.FAILED, board.shoot(1, 6));
        Assert.assertEquals(Board.ShotResult.FAILED, board.shoot(9, 5));
    }
}