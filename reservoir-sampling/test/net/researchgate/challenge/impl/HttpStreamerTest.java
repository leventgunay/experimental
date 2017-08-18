package net.researchgate.challenge.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpStreamerTest {

    @Test(expected = AssertionError.class)
    public void testInvalidHttpUrl() {

        new HttpStreamer(null);

    }

    @Test(expected = AssertionError.class)
    public void testInvalidHttpUrlRandom() {

        new HttpStreamer("http");

    }

    @Test
    public void testValidHttpStream() {

        HttpStreamer streamer = new HttpStreamer("https://www.random.org/strings/?num=10&len=10&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new");

        assertEquals(streamer.stream().count(), 100);

    }
}
