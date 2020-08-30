package draw.command;


import draw.core.Canvas;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class LineCommandTest extends BaseTest{
    private static final String validateArgumentParamUrl = resourcesUrl + "/command/line/validateArgumentParam.csv";
    private static final String createLineParamUrl = resourcesUrl + "/command/line/createLineParam.csv";
    private Command lineCommand = new LineCommand();
    private Command createCommand = new CreateCommand();

    @Test
    @FileParameters(validateArgumentParamUrl)
    public void validateFormatTest(String arguments, boolean expected) {
        lineCommand.setArgumentsArr(arguments);
        boolean isValidate = lineCommand.validateFormat();
        assertEquals(expected, isValidate);
    }

    @Test
    @FileParameters(createLineParamUrl)
    public void executeTest(String createCanvasArgument, String lineArgument, String expected) throws Exception {
        /** In this test, we only use valid input arguments
         *  as invalid argument should be tested by the validateFormatTest method **/

        if (!createCanvasArgument.equals("")) {
            createCommand.setArgumentsArr(createCanvasArgument);
            createCommand.execute();
        }
        try {
            lineCommand.setArgumentsArr(lineArgument);
            lineCommand.execute();
            String unescapedExpected = StringEscapeUtils.unescapeJava(expected);
            assertEquals(unescapedExpected, Canvas.getActiveCanvas().getContent().toString());
        } catch (Exception exception) {
            assertEquals(expected, exception.getMessage());
        }
    }
}
