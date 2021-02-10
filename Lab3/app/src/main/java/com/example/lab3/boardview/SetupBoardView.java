package com.example.lab3.boardview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.example.lab3.R;
import com.example.lab3.battleship.Board;
import com.example.lab3.battleship.Ship;
import com.example.lab3.battleship.exceptions.ShipPlacingException;

public class SetupBoardView extends BoardView {
    public enum State {STABLE, PENDING_EDIT}
    private State state;
    private Board board;
    private Ship floatingShip;
    private Paint paint;



    public SetupBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        state = State.STABLE;
        floatingShip = null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board newBoard) {
        board = newBoard;
    }

    public Ship getFloatingShip() {
        return floatingShip;
    }

    public void setFloatingShip(Ship ship) {
        floatingShip = ship;
    }

    public void saveFloatingShip() throws ShipPlacingException {
        board.placeShip(floatingShip);
        floatingShip = null;
    }
}
