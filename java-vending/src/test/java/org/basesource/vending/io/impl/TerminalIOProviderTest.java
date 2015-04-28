package org.basesource.vending.io.impl;

import org.basesource.vending.base.SystemIOTestCase;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TerminalIOProviderTest extends SystemIOTestCase {

    protected TerminalInputProvider inputProvider;

    protected TerminalOutputProvider outputProvider;

    @Before
    public void initTest() {
        inputProvider = new TerminalInputProvider();
        outputProvider = new TerminalOutputProvider();

        inputProvider.reader(in);
    }

    @Test
    public void shouldTerminalOut() {
        outputProvider.send("Sample String Output..");

        assertSystemOut("Sample String Output..\n");
    }

    @Test
    public void shouldTerminalIn() throws Exception {
        prepareSystemIn("User Input Buffer");

        assertEquals("Should read user input.", inputProvider.get("Get Sample Input.."), "User Input Buffer");
    }
}
