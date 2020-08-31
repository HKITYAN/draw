package draw.core;

import draw.annotation.IsCommand;
import draw.command.Command;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import java.util.*;

public class CmdContainer {
    public static final Map<String, Command> cmdMap = new HashMap();

    public static void init() throws Exception {
        Reflections reflections = new Reflections(ClasspathHelper.forPackage("draw/command"));
        Set<Class<?>> cmdClassSet = reflections.getTypesAnnotatedWith(IsCommand.class);
        for (Class<?> cmdClass : cmdClassSet) {
            Command cmd = (Command) cmdClass.newInstance();
            String cmdString = cmd.getCommandString();
            if (cmdMap.containsKey(cmdString)) {
                throw new Exception("Duplicate command key declared: " + cmdString);
            }
            cmdMap.put(cmd.getCommandString(), cmd);
        }
    }

    public static Command getCommand(String mainCommand, String arguments) throws Exception {
        Optional<Command> optionalCommand = Optional.ofNullable(cmdMap.get(mainCommand));
        if (!optionalCommand.isPresent()) {
            Object[] availableCommands = cmdMap.keySet().toArray();
            Arrays.sort(availableCommands);
            throw new Exception("No such command, available commands: " + Arrays.toString(availableCommands));
        };
        Command command = optionalCommand.get();
        command.setArgumentsArr(arguments);
        if(!command.validateFormat()) {
            throw new Exception(command.getWrongInputFormatMsg());
        };
        return command;
    };


}
