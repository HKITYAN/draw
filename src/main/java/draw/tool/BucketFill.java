package draw.tool;

import draw.core.Canvas;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nonnull;

@RequiredArgsConstructor
public class BucketFill extends DrawingTool {
    @NonNull
    private int x;

    @NonNull
    private int y;

    @Nonnull
    private char fillColor;

    @Override
    protected void updateCanvas() throws Exception {
        Canvas canvas = Canvas.getActiveCanvas();

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        if (x > canvasWidth || y > canvasHeight) {
            throw new Exception("Cannot fill outside the canvas, please fill in another position");
        }
        int charIndex = this.getIndexInCanvas(this.x, this.y, canvasWidth);
        char originalColor = canvas.getContent().charAt(charIndex);
        bucketFill(canvas, canvasWidth, canvasHeight, this.x, this.y, originalColor, this.fillColor);
    }

    private void bucketFill(Canvas canvas, int canvasWidth, int canvasHeight, int x, int y, char originalColor, char fillColor) {
        if (x > canvasWidth || y > canvasHeight || x < 1 || y < 1) return;
        int charIndex = y * (canvasWidth + 3) + x;
        if (canvas.getContent().charAt(charIndex) != originalColor) return;
        canvas.getContent().setCharAt(charIndex, fillColor);
        bucketFill(canvas, canvasWidth, canvasHeight, x + 1, y, originalColor, fillColor);
        bucketFill(canvas, canvasWidth, canvasHeight, x - 1, y, originalColor, fillColor);
        bucketFill(canvas, canvasWidth, canvasHeight, x, y + 1, originalColor, fillColor);
        bucketFill(canvas, canvasWidth, canvasHeight, x , y - 1, originalColor, fillColor);
    }
}
