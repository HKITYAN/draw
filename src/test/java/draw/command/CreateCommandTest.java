package draw.command;

import draw.core.Canvas;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class CreateCommandTest extends BaseTest {
    private static final String validateArgumentParamUrl = resourcesUrl + "/command/create/validateArgumentParam.csv";
    private static final String createCanvasParamUrl = resourcesUrl + "/command/create/createCanvasParam.csv";
    private Command createCommand = new CreateCommand();

    @Test
    @FileParameters(validateArgumentParamUrl)
    public void validateFormatTest(String arguments, boolean expected) {
        createCommand.setArgumentsArr(arguments);
        boolean isValidate = createCommand.validateFormat();
        assertEquals(expected, isValidate);
    }

    @Test
    @FileParameters(createCanvasParamUrl)
    public void executeTest(String argument, String expected) throws Exception {
        /** In this test, we only use valid input arguments
         *  as invalid argument should be handle by the validateFormatTest method **/
        createCommand.setArgumentsArr(argument);
        createCommand.execute();
        String unescapedExpected = StringEscapeUtils.unescapeJava(expected);
        assertEquals(unescapedExpected, Canvas.getActiveCanvas().getContent().toString());
    }
}
