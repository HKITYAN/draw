package draw.tool;

import draw.command.BaseTest;
import draw.command.CreateCommand;
import draw.core.Canvas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DrawingToolTest extends BaseTest {
    @Before
    public void clearCanvas() {
        Canvas.clear();
    }

    @Test
    public void drawExceptionTest() {
        try {
            DrawingTool anyTool = new StraightLine(1, 1, 1, 1);
            anyTool.draw();
            fail();
        } catch (Exception exception) {
            assertEquals("Please create canvas first", exception.getMessage());
        }
    }

    @Test
    public void drawSuccessTest() {
        try {
            CreateCommand createCommand = new CreateCommand();
            createCommand.setArgumentsArr("2 2");
            createCommand.execute();
            DrawingTool anyTool = new StraightLine(1, 1, 1, 1);
            anyTool.draw();
            assertEquals(1, 1);
        } catch (Exception exception) {
            fail();
        }

    }
}
