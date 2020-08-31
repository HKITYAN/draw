package draw.command;

import draw.annotation.IsCommand;
import draw.tool.DrawingTool;
import draw.tool.Rectangle;
import draw.utils.Utils;

@IsCommand
public class RectangleCommand extends Command implements GenericAdaptor {

    private static final String commandString = "R";

    private static final String wrongInputFormatMsg =
            "Incorrect syntax or format\n" +
            "use \"R x1 y1 x2 y2\" where x1, y1, x2, y2 should be positive whose upper left corner is (x1,y1) and lower right corner is (x2,y2)";

    @Override
    public boolean validateFormat() {
        if (this.argumentArr.length != 4) return false;
        if(!Utils.isAllPositiveInteger(this.argumentArr)) return false;
        return true;
    }

    @Override
    public String getCommandString() {
        return commandString;
    }

    @Override
    public String getWrongInputFormatMsg() {
        return wrongInputFormatMsg;
    }

    @Override
    public void execute() throws Exception {
        DrawingTool rectangleTool = this.convertToTool();
        rectangleTool.draw();
    }

    @Override
    public DrawingTool convertToTool() {
        int x1 = Integer.parseInt(this.argumentArr[0]);
        int y1 = Integer.parseInt(this.argumentArr[1]);
        int x2 = Integer.parseInt(this.argumentArr[2]);
        int y2 = Integer.parseInt(this.argumentArr[3]);
        return new Rectangle(x1, y1, x2, y2);
    }
}
