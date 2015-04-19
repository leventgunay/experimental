package org.basesource.vending.io;

public interface OutputProvider<T> {

    public void send(T... tag);

}
