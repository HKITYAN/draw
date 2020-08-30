package draw.command;

import draw.core.Canvas;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BucketFillCommandTest extends BaseTest {
    private static final String validateArgumentParamUrl = resourcesUrl + "/command/fill/validateArgumentParam.csv";
    private static final String bucketFillArgumentParamUrl = resourcesUrl + "/command/fill/bucketFillParam.csv";

    private Command bucketFillCommand = new BucketFillCommand();
    private Command createCommand = new CreateCommand();
    private Command lineCommand = new LineCommand();
    private Command rectangleCommand = new RectangleCommand();

    @Test
    @FileParameters(validateArgumentParamUrl)
    public void validateFormatTest(String arguments, boolean expected) {
        bucketFillCommand.setArgumentsArr(arguments);
        boolean isValidate = bucketFillCommand.validateFormat();
        assertEquals(expected, isValidate);
    }

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
            bucketFillCommand.setArgumentsArr(bucketFillArgument);
            bucketFillCommand.execute();
            String unescapedExpected = StringEscapeUtils.unescapeJava(expected);
            assertEquals(unescapedExpected, Canvas.getActiveCanvas().getContent().toString());
        } catch (Exception exception) {
            assertEquals(expected, exception.getMessage());
        }
    }
}
