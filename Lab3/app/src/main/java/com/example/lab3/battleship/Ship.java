package com.example.lab3.battleship;

import com.example.lab3.battleship.exceptions.ShipException;

public class Ship {
    public enum Orientation {
        HORIZONTAL,
        VERTICAL;

        public Orientation rotate() {
            switch (this) {
                case HORIZONTAL:
                    return VERTICAL;
                case VERTICAL:
                    return HORIZONTAL;
            }
            return null;
        }
    }

    private final Orientation orientation;
    private final int coordsRangeMin, coordsSingle;
    private final int size;
    private boolean[] damaged;

    public Ship(int size, Orientation orientation, int coordsSingle, int coordsRangeMin) throws ShipException {
        if (size <= 0 || size > GameSetup.maxShipSize ||
                coordsSingle < 0 || coordsSingle >= GameSetup.boardSize ||
                coordsRangeMin < 0 || coordsRangeMin + size - 1 >= GameSetup.boardSize) {
            throw new ShipException();
        }

        this.size = size;
        this.orientation = orientation;
        this.coordsSingle = coordsSingle;
        this.coordsRangeMin = coordsRangeMin;
        damaged = new boolean[size];
        for (int i = 0; i < size; i++) {
            damaged[i] = false;
        }
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getCoordsSingle() {
        return coordsSingle;
    }

    public int getCoordsRangeMin() {
        return coordsRangeMin;
    }

    public int getSize() {
        return size;
    }

    public boolean isPlacedOn(int x, int y) {
        if (orientation == Orientation.VERTICAL) {
            int tmp = y;
            y = x;
            x = tmp;
        }
        return coordsSingle == y && x >= coordsRangeMin && x <= coordsRangeMin + size - 1;
    }

    public boolean shoot(int x, int y) {
        if (isPlacedOn(x, y)) {
            int dist = orientation == Orientation.HORIZONTAL ? x - coordsRangeMin : y - coordsRangeMin;
            if (!damaged[dist]) {
                damaged[dist] = true;
                return true;
            }
        }
        return false;
    }

    public boolean isDead() {
        for (int i = 0; i < size; i++) {
            if (!damaged[i]) {
                return false;
            }
        }
        return true;
    }
}
