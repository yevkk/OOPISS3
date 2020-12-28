package com.example.lab3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.content.Context;


public class FieldView extends View {
    private final Paint fieldPaint;
    private float cellSize, cellDistance;
    private float hPadding, vPadding;

    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        fieldPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        hPadding = vPadding = 0;
        float diff = this.getMeasuredHeight() - this.getMeasuredWidth();
        if (diff < 0) {
            hPadding = Math.abs(diff / 2f);
        } else {
            vPadding = Math.abs(diff / 2f);
        }
        cellSize = (this.getMeasuredWidth() - 2 * hPadding) / 13;
        cellDistance = cellSize / 5;

        fieldPaint.setColor(getResources().getColor(R.color.field_color, null));
        fieldPaint.setTextAlign(Paint.Align.CENTER);
        fieldPaint.setTextSize(cellSize * 0.6f);
        fieldPaint.setStyle(Paint.Style.FILL);
        fieldPaint.setAntiAlias(true);

        float x = cellDistance + cellSize * 1.5f + hPadding;
        float y = cellSize + vPadding;
        for (int i = 1; i <= 10; i++, x += cellSize + cellDistance) {
            canvas.drawText(String.valueOf(i), x, y, fieldPaint);
        }

        char symbol = 'A';
        y = cellSize + cellDistance + vPadding;
        for (int i = 1; i <= 10; i++, y += cellSize + cellDistance) {
            x = cellSize * 0.5f + hPadding;
            canvas.drawText(String.valueOf(symbol++), x, y + cellSize * 0.5f + cellDistance, fieldPaint);
            x = cellSize + cellDistance + hPadding;
            for (int j = 1; j <= 10; j++, x += cellSize + cellDistance) {
                canvas.drawRect(x, y, x + cellSize, y + cellSize, fieldPaint);
            }
        }
    }
}
