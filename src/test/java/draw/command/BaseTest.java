package draw.command;

import java.io.OutputStream;
import java.io.PrintStream;

public class BaseTest {
    protected static final PrintStream originalStream = System.out;
    protected static final String resourcesUrl = "src/test/resources";

    protected static final PrintStream silentStream = new PrintStream(new OutputStream() {
        public void write(int b) {}
    });
    static {
        System.setOut(silentStream);
    }
}
