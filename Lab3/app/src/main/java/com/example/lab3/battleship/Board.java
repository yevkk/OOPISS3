package com.example.lab3.battleship;

import com.example.lab3.battleship.exceptions.ShipPlacingException;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public enum CellState {
        EMPTY(false),
        SHIP(false),
        SHOT_EMPTY(true),
        SHOT_SHIP(true);

        private final boolean isShot;

        private CellState(boolean isShot) {
            this.isShot = isShot;
        }

        public boolean isShot() {
            return isShot;
        }
    }

    public enum ShotResult {
        MISS,
        IN_TARGET,
        DESTROYED,
        FAILED
    }

    private final CellState[][] cells;
    private final List<Ship> ships;

    public Board() {
        int size = GameSetup.boardSize;
        cells = new CellState[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = CellState.EMPTY;
            }
        }

        ships = new ArrayList<>();
    }

    public List<Ship> getShips() {
        return ships;
    }

    public ShotResult shoot(int x, int y) {
        if (x < 0 || x > GameSetup.boardSize || y < 0 || y > GameSetup.boardSize || cells[x][y].isShot()) {
            return ShotResult.FAILED;
        }

        for (Ship ship : ships) {
            if (ship.shoot(x, y)) {
                cells[x][y] = CellState.SHOT_SHIP;
                return ship.isDead() ? ShotResult.DESTROYED : ShotResult.IN_TARGET;
            }
        }

        cells[x][y] = CellState.SHOT_EMPTY;
        return ShotResult.MISS;
    }

    public void placeShip(Ship newShip) throws ShipPlacingException {
        boolean isHorizontal = newShip.getOrientation() == Ship.Orientation.HORIZONTAL;
        int shipCoordsSingle = newShip.getCoordsSingle();
        int shipCoordsRangeMin = newShip.getCoordsRangeMin();
        int shipSize = newShip.getSize();

        int minX = Math.max(isHorizontal ? shipCoordsRangeMin - 1 : shipCoordsSingle - 1, 0);
        int maxX = Math.min(isHorizontal ? shipCoordsRangeMin + shipSize : shipCoordsSingle + 1, GameSetup.boardSize);

        int minY = Math.max(isHorizontal ? shipCoordsSingle - 1 : shipCoordsRangeMin - 1, 0);
        int maxY = Math.min(isHorizontal ? shipCoordsSingle + 1 : shipCoordsRangeMin + shipSize, GameSetup.boardSize);

        for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
                for (Ship ship : ships) {
                    if (ship.isPlacedOn(x, y)) {
                        throw new ShipPlacingException();
                    }
                }
            }
        }

        for (int i = 0; i < shipSize; i++) {
            int x = isHorizontal ? shipCoordsRangeMin + i : shipCoordsSingle;
            int y = isHorizontal ? shipCoordsSingle : shipCoordsRangeMin + i;
            cells[x][y] = CellState.SHIP;
        }
        ships.add(newShip);
    }

    public Ship removeShipOn(int x, int y) {
        Ship removedShip = null;
        for (Ship ship : ships) {
            if (ship.isPlacedOn(x, y)) {
                removedShip = ship;
                break;
            }
        }

        ships.remove(removedShip);
        boolean isHorizontal = removedShip.getOrientation() == Ship.Orientation.HORIZONTAL;
        for(int i = 0; i < removedShip.getSize(); i++) {
            x = isHorizontal ? removedShip.getCoordsRangeMin() + i : removedShip.getCoordsSingle();
            y = isHorizontal ? removedShip.getCoordsSingle() : removedShip.getCoordsRangeMin() + i;
            cells[x][y] = CellState.EMPTY;
        }

        return removedShip;
    }
}
