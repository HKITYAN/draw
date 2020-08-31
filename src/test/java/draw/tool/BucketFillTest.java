package draw.tool;

import draw.command.*;
import draw.core.Canvas;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BucketFillTest extends BaseTest {
    private static final String bucketFillArgumentParamUrl = resourcesUrl + "/tool/bucketFillParam.csv";

    private Command createCommand = new CreateCommand();
    private Command lineCommand = new LineCommand();
    private Command rectangleCommand = new RectangleCommand();

    @Test
    @FileParameters(bucketFillArgumentParamUrl)
    public void executeTest(String createCanvasArgument, String commandString, String commandArgument, String bucketFillArgument, String expected) throws Exception {
        /** In this test, we only use valid input arguments
         *  as invalid argument should be tested by the validateFormatTest method **/
        createCommand.setArgumentsArr(createCanvasArgument);
        createCommand.execute();

        switch (commandString) {
            case "L":
                lineCommand.setArgumentsArr(commandArgument);
                lineCommand.execute();
                break;
            case "R":
                rectangleCommand.setArgumentsArr(commandArgument);
                rectangleCommand.execute();
                break;
            default:
                break;
        }
        try {
            String[] params = bucketFillArgument.split(" ");
            int x = Integer.parseInt(params[0]);
            int y = Integer.parseInt(params[1]);
            char color = params[2].charAt(0);
            BucketFill bucketFill = new BucketFill(x, y, color);
            bucketFill.updateCanvas();

            String unescapedExpected = StringEscapeUtils.unescapeJava(expected);
            assertEquals(unescapedExpected, Canvas.getActiveCanvas().getContent().toString());
        } catch (Exception exception) {
            assertEquals(expected, exception.getMessage());
        }
    }
}
