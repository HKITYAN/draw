package draw.core;

import lombok.Getter;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Canvas {
    @Getter
    private static Map<String, Canvas> canvasRepo = new HashMap();

    @Getter
    private static Canvas activeCanvas;


    public static void create(int width, int height) {
        String canvasName = "draw.core.Canvas-" + new Date();
        Canvas canvas = new Canvas(width, height);

        canvasRepo.put(canvasName, canvas);
        activeCanvas = canvas;
        for (int h = 0; h <= height + 1; h++) {
            if (h == 0 || h == height + 1) {;
                String line = String.join("",
                        Collections.nCopies(width + 2, Character.toString(canvas.horizontalLineStyle)));
                if (h == height + 1) canvas.content.append(line);
                if (h == 0) canvas.content.append(line).append("\n");
                continue;
            }

            String middlePart = String.join("", Collections.nCopies(width, " "));
            String headOrTail = Character.toString(canvas.verticalLineStyle);
            String line = headOrTail.concat(middlePart).concat(headOrTail);
            canvas.content.append(line).append("\n");
        }
        System.out.println(canvas.content);
    }

    public static void clear() {
        activeCanvas = null;
        canvasRepo.clear();
    }

    private Canvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private char horizontalLineStyle = '-';
    private char verticalLineStyle = '|';

    private StringBuilder content = new StringBuilder("");

    private int width;

    private int height;
}
