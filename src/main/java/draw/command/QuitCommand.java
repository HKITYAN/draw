package draw.command;

import draw.annotation.IsCommand;

import java.util.Arrays;

@IsCommand
public class QuitCommand extends Command {
    private static final String commandString = "Q";

    private static final String wrongInputFormatMsg =
            "Incorrect syntax or format\n" +
                    "Use \"Q\" without any arguments";

    @Override
    public boolean validateFormat() {
        if (this.argumentArr.length != 0) return false;
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
        System.out.println("Exit programme");
        System.exit(0);
    }
}
