package draw.tool;

import draw.core.Canvas;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

@RequiredArgsConstructor
public class StraightLine extends DrawingTool {
    @NonNull
    private int x1;

    @NonNull
    private int y1;

    @NonNull
    private int x2;

    @NonNull
    private int y2;

    private char lineStyle = 'x';

    @Override
    public void updateCanvas() throws Exception {

        Canvas canvas = Canvas.getActiveCanvas();

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        if (Math.max(x1, x2) > canvasWidth || Math.max(y1, y2) > canvasHeight) {
            throw new Exception("Line cannot fit into canvas, please draw another line");
        }

        if (x1 == x2) {
            this.drawVerticalLine(Math.min(y1, y2), Math.max(y1, y2), x1, lineStyle);
            return;
        }

        if (y1 == y2) {
            this.drawHorizontalLine(Math.min(x1, x2), Math.max(x1, x2), y1, lineStyle);
            return;
        }
    }
}