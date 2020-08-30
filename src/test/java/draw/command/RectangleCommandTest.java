package draw.command;

import draw.core.Canvas;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class RectangleCommandTest extends BaseTest {
    private static final String validateArgumentParamUrl = resourcesUrl + "/command/rectangle/validateArgumentParam.csv";
    private static final String createRectangleArgument = resourcesUrl + "/command/rectangle/createRectangleParam.csv";
    private Command rectangleCommand = new RectangleCommand();
    private Command createCommand = new CreateCommand();

    @Test
    @FileParameters(validateArgumentParamUrl)
    public void validateFormatTest(String arguments, boolean expected) {
        rectangleCommand.setArgumentsArr(arguments);
        boolean isValidate = rectangleCommand.validateFormat();
        assertEquals(expected, isValidate);
    }

    @Test
    @FileParameters(createRectangleArgument)
    public void executeTest(String createCanvasArgument, String rectangleArgument, String expected) throws Exception {
        /** In this test, we only use valid input arguments
         *  as invalid argument should be handle by the validateFormatTest method **/
        if (!createCanvasArgument.equals("")) {
            createCommand.setArgumentsArr(createCanvasArgument);
            createCommand.execute();
        }
        try {
            rectangleCommand.setArgumentsArr(rectangleArgument);
            rectangleCommand.execute();
            String unescapedExpected = StringEscapeUtils.unescapeJava(expected);
            assertEquals(unescapedExpected, Canvas.getActiveCanvas().getContent().toString());
        } catch (Exception exception) {
            assertEquals(expected, exception.getMessage());
        }
    }
}
