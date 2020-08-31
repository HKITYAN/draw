package draw.core;

import draw.command.BaseTest;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class CmdContainerTest extends BaseTest {
    private static final String getCommandParamUrl = resourcesUrl + "/core/getCommandParam.csv";

    @BeforeClass
    public static void intCommandContainer() throws Exception {
        CmdContainer.init();
    }

    @Test
    @FileParameters(getCommandParamUrl)
    public void getCommandTest(String mainCommand, String arguments, String expected) throws Exception {
        try {
            CmdContainer.getCommand(mainCommand, arguments);
            assertEquals(1, 1);
        } catch (Exception exception) {
            String unescapedExpected = StringEscapeUtils.unescapeJava(expected);
            assertEquals(unescapedExpected, exception.getMessage());
        }
    }
}
