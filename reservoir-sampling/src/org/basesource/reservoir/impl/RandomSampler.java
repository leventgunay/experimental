package org.basesource.reservoir.impl;

import org.basesource.reservoir.Sampler;
import org.basesource.reservoir.Streamer;

import java.security.SecureRandom;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * RandomSampler
 *
 * A stream reducer/collector which provides sample data picked randomly through given source.
 *
 * @implements Sampler - abstraction over sampling functionality
 * @implements Collector - leveraging stream collector interface of Java 8
 *
 * @see Stream#collect(Collector)
 * @see Collectors
 */
public class RandomSampler implements Sampler, Collector<Integer, StringBuilder, StringBuilder> {

    final static Set<Characteristics> CHARACTERISTICS =
            EnumSet.of(Collector.Characteristics.UNORDERED, Collector.Characteristics.IDENTITY_FINISH);

    private int size = 0, counter = 0;
    private final Random rand = new SecureRandom();

    /**
     * A function that creates and returns a subset sample data of given stream source.
     *
     * @return StringBuilder which represents requested sample of the source.
     */
    @Override
    public StringBuilder sample(Streamer input, int size) {
        this.size = size;
        this.counter = 0;

        assert input != null : "Input stream should be passed.";

        assert size > 0 : "Size should be greater than zero.";

        return input.stream().boxed().collect(this);
    }


    /**
     * Implements mutable result container of Collector interface.
     *
     * @return a function which returns a new, mutable result container
     */
    @Override
    public Supplier<StringBuilder> supplier() {
        return StringBuilder::new;
    }

    /**
     * Implements sample folding into a result subset.
     *
     * @return a function which folds samples into a result subset.
     */
    @Override
    public BiConsumer<StringBuilder, Integer> accumulator() {
        return (final StringBuilder in, Integer s) -> {
            if (counter++ < size) {
                in.append((char) s.intValue());
            }
            else {
                int replaceInIndex = (int) (rand.nextDouble() * ((counter++) + 1));
                if (replaceInIndex < size) {
                    in.deleteCharAt(replaceInIndex);
                    in.insert(replaceInIndex, (char) s.intValue());
                }
            }
        };
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return StringBuilder::append;
    }

    @Override
    public Function<StringBuilder, StringBuilder> finisher() {
        return (i) -> i;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return CHARACTERISTICS;
    }
}
