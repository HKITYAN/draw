package draw.command;

public abstract class Command {
    abstract public boolean validateFormat();

    abstract public String getCommandString();

    abstract public String getWrongInputFormatMsg();

    abstract public void execute() throws Exception;

    public String[] argumentArr;

    public void setArgumentsArr(String arguments) {
        if (arguments.equals("")) {
            this.argumentArr = new String[0];
            return;
        }
        this.argumentArr = arguments.split(" ");
    };
}
