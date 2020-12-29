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

    }

    @Test
    public void placementAdjacentOverlay() {

    }

    @Test
    public void shipsRemovalSome() {

    }

    @Test
    public void shipsRemovalAll() {

    }

    @Test
    public void shipsRemovalNullReturn() {

    }

    @Test
    public void shootOutOfBoard() {

    }

    @Test
    public void shootOnShotCell() {

    }

    @Test
    public void shootInTarget() {

    }

    @Test
    public void shootInTargetWithDestroying() {

    }

    @Test
    public void shootMissed() {

    }
}