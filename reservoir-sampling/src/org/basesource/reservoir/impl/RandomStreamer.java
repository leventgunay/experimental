package org.basesource.reservoir.impl;

import org.basesource.reservoir.Streamer;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomStreamer implements Streamer {

    final static int RANDOM_STREAM_FACTOR = 100;

    final Random rand = new SecureRandom();

    int size;

    public RandomStreamer(int size) {
        this.size = size * RANDOM_STREAM_FACTOR;
    }

    @Override
    public IntStream stream() {
        return this.rand.ints(48,122)
            .filter(i-> (i<57 || i>65) && (i <90 || i>97))
            .mapToObj(i -> (char) i)
            .limit(size)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).chars();
    }
}
