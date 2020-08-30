package draw.command;

import draw.utils.Utils;
import draw.annotation.IsCommand;
import draw.tool.DrawingTool;
import draw.tool.StraightLine;

@IsCommand()
public class LineCommand extends Command implements GenericAdaptor {
    private static final String commandString = "L";

    private static final String wrongInputFormatMsg =
            "Incorrect syntax or format\n" +
            "use \"L x1 y1 x2 y2\" where x1, y1, x2, y2 should be positive" +
            "and line should be horizontal or vertical (x1 = x2 or y1 = y2)";

    @Override
    public boolean validateFormat() {
        if (this.argumentArr.length != 4) return false;

        boolean isAllPositive = Utils.isAllPositiveInteger(this.argumentArr);
        if (!isAllPositive) return false;

        int x1 = Integer.parseInt(this.argumentArr[0]);
        int y1 = Integer.parseInt(this.argumentArr[1]);
        int x2 = Integer.parseInt(this.argumentArr[2]);
        int y2 = Integer.parseInt(this.argumentArr[3]);
        boolean isHorizontal = (y1 == y2);
        boolean isVertical = (x1 == x2);

        if (!(isHorizontal || isVertical)) return false;

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
        DrawingTool lineTool = this.convertToTool();
        lineTool.draw();
    }

    @Override
    public DrawingTool convertToTool() {
        int x1 = Integer.parseInt(this.argumentArr[0]);
        int y1 = Integer.parseInt(this.argumentArr[1]);
        int x2 = Integer.parseInt(this.argumentArr[2]);
        int y2 = Integer.parseInt(this.argumentArr[3]);
        return new StraightLine(x1, y1, x2, y2);
    }
}
