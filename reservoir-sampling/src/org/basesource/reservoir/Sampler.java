package org.basesource.reservoir;

public interface Sampler {

    StringBuilder sample(Streamer input, int length);

}
