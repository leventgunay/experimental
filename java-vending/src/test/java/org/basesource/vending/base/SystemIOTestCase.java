package org.basesource.vending.base;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static org.junit.Assert.*;
import java.io.PrintStream;

public class SystemIOTestCase extends AbstractTestCase {

//    protected final ByteArrayInputStream in = new ByteArrayInputStream();

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Before
    public void initSystemIO() {
        System.setOut(new PrintStream(outContent));
    }

    protected void assertSystemOut(String out) {
        assertEquals(out, outContent.toString());
        outContent.reset();
    }

    @After
    public void resetSystemIO() {
        System.setOut(System.out);
    }
}
