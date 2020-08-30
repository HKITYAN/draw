package draw.command;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;

@RunWith(JUnitParamsRunner.class)
public class CommandTest extends BaseTest {

    @Test
    @Parameters(method = "getInputParams") // not using @FileParameter as it does not support Array
    public void setArgumentTest(String arguments, String[] expectedArray) {
        Command anyCommand = new CreateCommand(); // you can replace by other command type
        anyCommand.setArgumentsArr(arguments);
        assertArrayEquals(anyCommand.argumentArr, expectedArray);
    }

    private Object[] getInputParams() {
        return new Object[] {
                new Object[]{"2 3", new String[]{"2", "3"}},
                new Object[]{"", new String[0]},
                new Object[]{"2 3 4 5", new String[]{"2", "3", "4", "5"}}
        };
    }
}
