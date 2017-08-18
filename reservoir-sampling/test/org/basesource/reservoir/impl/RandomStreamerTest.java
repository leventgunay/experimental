package org.basesource.reservoir.impl;

import org.junit.Test;
import static org.junit.Assert.*;

public class RandomStreamerTest {

    @Test
    public void testBasicStreaming() {

        RandomStreamer streamer = new RandomStreamer(10);

        assertEquals(streamer.stream().count(), RandomStreamer.RANDOM_STREAM_FACTOR * 10);

    }
}
