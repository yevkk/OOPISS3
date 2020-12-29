package com.example.lab3.battleship;

import org.junit.Assert;

public class BoardTestHelper {
    public static void place5Ships(Board board)  {
        try {
            board.placeShip(new Ship(1, Ship.Orientation.HORIZONTAL, 0, 0));
            board.placeShip(new Ship(3, Ship.Orientation.HORIZONTAL, 3, 1));
            board.placeShip(new Ship(2, Ship.Orientation.HORIZONTAL, 5, 4));
            board.placeShip(new Ship(2, Ship.Orientation.VERTICAL, 7, 1));
            board.placeShip(new Ship(4, Ship.Orientation.VERTICAL, 9, 5));
        } catch (Exception e) {
            Assert.fail("unexpected exception");
        }
    }

    public static void checkShipPlacement(Board board, Ship ship) {
        for (int i = 0; i < ship.getSize(); i++) {
            switch (ship.getOrientation()) {
                case VERTICAL:
                    Assert.assertEquals(Board.CellState.SHIP, board.getCell(ship.getCoordsSingle(), ship.getCoordsRangeMin() + i));
                    break;
                case HORIZONTAL:
                    Assert.assertEquals(Board.CellState.SHIP, board.getCell(ship.getCoordsRangeMin() + i, ship.getCoordsSingle()));
                    break;
            }
        }
        Assert.assertTrue(board.getShips().contains(ship));
    }

    public static void checkShipRemoval(Board board, Ship ship) {
        for (int i = 0; i < ship.getSize(); i++) {
            switch (ship.getOrientation()) {
                case VERTICAL:
                    Assert.assertEquals(Board.CellState.EMPTY, board.getCell(ship.getCoordsSingle(), ship.getCoordsRangeMin() + i));
                    break;
                case HORIZONTAL:
                    Assert.assertEquals(Board.CellState.EMPTY, board.getCell(ship.getCoordsRangeMin() + i, ship.getCoordsSingle()));
                    break;
            }
        }
        Assert.assertFalse(board.getShips().contains(ship));
    }
}
