package net.researchgate.challenge.impl;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class BaseStreamerTest {

    @Test(expected = AssertionError.class)
    public void testInvalidInputStream() {

        new BaseStreamer(null);

    }

    @Test
    public void testValidInput() {

        assertEquals(new BaseStreamer("TestTestTest").input.toString(), "TestTestTest");
    }

    @Test
    public void testValidInputStreaming() {
        IntStream stream = new BaseStreamer("TestTestTest").stream();

        assertNotNull(stream);

        assertEquals(stream.count(), 12);

    }

}
