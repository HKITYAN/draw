package draw.command;

import draw.annotation.IsCommand;
import draw.tool.BucketFill;
import draw.tool.DrawingTool;
import draw.utils.Utils;

import java.util.Arrays;

@IsCommand
public class BucketFillCommand extends Command implements GenericAdaptor {
    private static final String commandString = "B";

    private static final String wrongInputFormatMsg =
            "Incorrect syntax or format\n" +
            "use \"B x y c\" where x, y should be positive and c is a char";

    @Override
    public DrawingTool convertToTool() {
        int x1 = Integer.parseInt(this.argumentArr[0]);
        int x2 = Integer.parseInt(this.argumentArr[1]);
        char color =  this.argumentArr[2].charAt(0);
        return new BucketFill(x1, x2, color);
    }

    @Override
    public boolean validateFormat() {
        if (this.argumentArr.length != 3) return false;
        if (!Utils.isAllPositiveInteger(
                Arrays.copyOfRange(this.argumentArr, 0, 2))
        ) return false;
        if (this.argumentArr[2].length() != 1) return false;
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
        DrawingTool bucketFillTool = this.convertToTool();
        bucketFillTool.draw();
    }
}
