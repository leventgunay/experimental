package net.researchgate.challenge.impl;

import net.researchgate.challenge.Streamer;

import java.util.stream.IntStream;

public class BaseStreamer implements Streamer {

    protected StringBuilder input;

    public BaseStreamer(String input) {

        assert input != null : "Stream input should not be empty or undefined.";

        this.input = new StringBuilder(input.replaceAll("\\s",""));
    }

    @Override
    public IntStream stream() {
        return this.input.chars();
    }
}
