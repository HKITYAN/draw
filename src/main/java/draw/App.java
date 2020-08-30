package draw;

import draw.command.Command;
import draw.core.Canvas;
import draw.core.CmdContainer;
import lombok.NoArgsConstructor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        CmdContainer.init();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command:");

            String inputCommand = scanner.nextLine();
            String[] fullCommand = commandParser(inputCommand);
            try {
                Command command = CmdContainer.getCommand(fullCommand[0], fullCommand[1]);
                command.execute();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                if (Canvas.getActiveCanvas() != null) {
                    System.out.println("Current Canvas:");
                    System.out.println(Canvas.getActiveCanvas().getContent());
                }
            }

        }

    }

    private static String[] commandParser(String command) {
        String regex = "(\\w+ *)(.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        String[] fullCommand = new String[2];

        if (matcher.find()) {
            fullCommand[0] = matcher.group(1).trim();
            fullCommand[1] = matcher.group(2).trim();
        }
        return fullCommand;
    }
}
