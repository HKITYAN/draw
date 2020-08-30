package draw.command;

import draw.annotation.IsCommand;
import draw.core.Canvas;
import draw.utils.Utils;

@IsCommand()
public class CreateCommand extends Command {
    private static final String commandString = "C";

    private static final String wrongInputFormatMsg =
            "Incorrect syntax or format\n" +
            "Use \"C w h\" where w is width and h is height, both positive";


    @Override
    public boolean validateFormat() {
        if (this.argumentArr.length != 2) {
            return false;
        }

        boolean isAllPositive = Utils.isAllPositiveInteger(this.argumentArr);
        if (!isAllPositive) {
            return false;
        }

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
        int width = Integer.parseInt(this.argumentArr[0]);
        int height = Integer.parseInt(this.argumentArr[1]);
        Canvas.create(width, height);
    }
}
