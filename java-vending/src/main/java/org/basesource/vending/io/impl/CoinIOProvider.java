package org.basesource.vending.io.impl;

import org.basesource.vending.io.InputProvider;
import org.basesource.vending.io.OutputProvider;
import org.basesource.vending.model.Coin;

public class CoinIOProvider implements InputProvider<Coin>, OutputProvider<Coin> {

    @Override
    public Coin get(String tag) {
        return Coin.by(tag);
    }

    @Override
    public void send(Coin... coins) {
        System.out.print("Money dropped ");
        for(Coin coin : coins) {
            System.out.print(coin);
        } System.out.print("\n");
    }

}
