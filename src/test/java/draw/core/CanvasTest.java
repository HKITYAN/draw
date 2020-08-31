package draw.core;

import draw.command.BaseTest;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class CanvasTest extends BaseTest {
    private static final String createCommandParamUrl = resourcesUrl + "/core/createCanvasParam.csv";

    @Test
    @FileParameters(createCommandParamUrl)
    public void createTest(int width, int height, String expected) {
        Canvas.create(width, height);
        String unescapedExpected = StringEscapeUtils.unescapeJava(expected);
        assertEquals(unescapedExpected, Canvas.getActiveCanvas().getContent().toString());
    }

}
