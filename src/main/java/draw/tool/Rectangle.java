package draw.tool;

import draw.core.Canvas;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Rectangle extends DrawingTool {
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
    protected void updateCanvas() throws Exception {
        Canvas canvas = Canvas.getActiveCanvas();

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        if (Math.max(x1, x2) > canvasWidth || Math.max(y1, y2) > canvasHeight) {
            throw new Exception("Rectangle cannot fit into canvas, please draw another rectangle");
        }

        this.drawVerticalLine(Math.min(y1, y2), Math.max(y1, y2), x1, lineStyle);
        this.drawVerticalLine(Math.min(y1, y2), Math.max(y1, y2), x2, lineStyle);
        this.drawHorizontalLine(Math.min(x1, x2), Math.max(x1, x2), y1, lineStyle);
        this.drawHorizontalLine(Math.min(x1, x2), Math.max(x1, x2), y2, lineStyle);

    }
}
