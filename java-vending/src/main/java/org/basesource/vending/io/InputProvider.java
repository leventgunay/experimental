package org.basesource.vending.io;

public interface InputProvider<T> {

    public T get(String tag);

}
