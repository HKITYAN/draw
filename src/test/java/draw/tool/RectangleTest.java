package draw.tool;

import draw.command.BaseTest;
import draw.command.Command;
import draw.command.CreateCommand;
import draw.command.RectangleCommand;
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
public class RectangleTest extends BaseTest {
    private static final String createRectangleArgument = resourcesUrl + "/tool/createRectangleParam.csv";
    private Command createCommand = new CreateCommand();

    @Test
    @FileParameters(createRectangleArgument)
    public void updateCanvasTest(String createCanvasArgument, String rectangleArgument, String expected) throws Exception {
        /** In this test, we only use valid input arguments
         *  as invalid argument should be handle by the validateFormatTest method **/
        if (!createCanvasArgument.equals("")) {
            createCommand.setArgumentsArr(createCanvasArgument);
            createCommand.execute();
        }
        try {
            List<Integer> params = Arrays.asList(rectangleArgument
                    .split(" ")).stream()
                    .map(str -> Integer.parseInt(str))
                    .collect(Collectors.toList());
            Rectangle rectangle = new Rectangle(params.get(0), params.get(1), params.get(2), params.get(3));
            rectangle.updateCanvas();

            String unescapedExpected = StringEscapeUtils.unescapeJava(expected);
            assertEquals(unescapedExpected, Canvas.getActiveCanvas().getContent().toString());
        } catch (Exception exception) {
            assertEquals(expected, exception.getMessage());
        }
    }
}
