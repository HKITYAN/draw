package draw.command;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BucketFillCommandTest extends BaseTest {
    private static final String validateArgumentParamUrl = resourcesUrl + "/command/fill/validateArgumentParam.csv";
    private static final String bucketFillArgument = resourcesUrl + "/command/fill/bucketFillParam.csv";

    private Command bucketFillCommand = new BucketFillCommand();
    private Command createCommand = new CreateCommand();

    @Test
    @FileParameters(validateArgumentParamUrl)
    public void validateFormatTest(String arguments, boolean expected) {
        bucketFillCommand.setArgumentsArr(arguments);
        boolean isValidate = bucketFillCommand.validateFormat();
        assertEquals(expected, isValidate);
    }
}
