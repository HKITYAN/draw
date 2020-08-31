package draw.tool;

import draw.core.Canvas;

public abstract class DrawingTool {
    protected abstract void updateCanvas() throws Exception;

    public void draw() throws Exception {
        Canvas canvas = Canvas.getActiveCanvas();
        System.out.println(canvas);
        if (canvas == null) {
            throw new Exception("Please create canvas first");
        }
        updateCanvas();
        System.out.println(canvas.getContent());

    }

    final protected void drawHorizontalLine(int startX, int endX, int yIndex, char lineStyle) {
        Canvas canvas = Canvas.getActiveCanvas();
        for (int x = startX; x <= endX; x++) {
            int charIndex = getIndexInCanvas(x, yIndex, canvas.getWidth());
            canvas.getContent().setCharAt(charIndex, lineStyle);
        }
    }

    final protected void drawVerticalLine(int startY, int endY, int xIndex, char lineStyle) {
        Canvas canvas = Canvas.getActiveCanvas();
        for (int y = startY; y <= endY; y++) {
            int charIndex = getIndexInCanvas(xIndex, y, canvas.getWidth());
            canvas.getContent().setCharAt(charIndex, lineStyle);
        }
    }

    final protected int getIndexInCanvas(int x, int y, int width) {
        return y * (width + 3) + x;
    }

}
