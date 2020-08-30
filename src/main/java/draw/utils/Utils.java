package draw.utils;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Utils {
    public static boolean isAllPositiveInteger(String[] testStringArr) {
        boolean isAllPositive;
        String isPositiveNumber = "[1-9]+[0-9]*";
        isAllPositive = Arrays.stream(testStringArr).allMatch(testString -> Pattern.matches(isPositiveNumber, testString));
        return isAllPositive;
    }
}
