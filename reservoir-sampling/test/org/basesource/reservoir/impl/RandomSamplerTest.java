package org.basesource.reservoir.impl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RandomSamplerTest {

    RandomSampler sampler;

    @Before
    public void initialize() {
        sampler = new RandomSampler();
    }

    @Test(expected = AssertionError.class)
    public void testInvalidSamplingNullInput() {

        sampler.sample(null, 3);

    }

    @Test(expected = AssertionError.class)
    public void testInvalidSamplingZeroSize() {

        sampler.sample(new BaseStreamer("TestTestTest"), 0);

    }

    @Test
    public void testValidSampling() {

        assertEquals(sampler.sample(new BaseStreamer("TestTestTest"), 3).length(), 3);

    }

    @Test
    public void testValidSamplingWithCharacterMatching() {
        String input = "123456789qwertyuopasdfghjkl";
        StringBuilder sample = sampler.sample(new BaseStreamer("123456789qwertyuopasdfghjkl"), 3);

        assertTrue(input.indexOf((char)sample.charAt(0)) > -1);
        assertTrue(input.indexOf((char)sample.charAt(1)) > -1);
        assertTrue(input.indexOf((char)sample.charAt(2)) > -1);

    }

    @Test
    public void testDummySampling() {
        String input = "1234";
        StringBuilder sample = sampler.sample(new BaseStreamer("1234"), 4);

        assertEquals(input, sample.toString());

    }
}
