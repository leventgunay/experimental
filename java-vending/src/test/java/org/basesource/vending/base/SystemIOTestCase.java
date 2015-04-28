package org.basesource.vending.base;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@PrepareForTest(Scanner.class)
public class SystemIOTestCase extends AbstractTestCase {
    @Mock
    protected BufferedReader in;

    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void initSystemIO() throws Exception {
        PowerMockito.whenNew(BufferedReader.class).withAnyArguments().thenReturn(in);
        System.setOut(new PrintStream(outContent));
    }

    protected void assertSystemOut(String out) {
        assertEquals(out, outContent.toString());
        outContent.reset();
    }

    protected void prepareSystemIn(String inputLine) throws Exception {
        doReturn(inputLine).when(in).readLine();
    }

    @After
    public void resetSystemIO() {
        System.setOut(System.out);
    }
}
