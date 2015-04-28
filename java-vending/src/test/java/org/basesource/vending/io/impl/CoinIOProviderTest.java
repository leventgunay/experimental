package org.basesource.vending.io.impl;

import static org.junit.Assert.*;
import org.basesource.vending.base.SystemIOTestCase;
import org.basesource.vending.model.Coin;
import org.junit.Test;

public class CoinIOProviderTest extends SystemIOTestCase {

    protected CoinIOProvider provider = new CoinIOProvider();

    @Test
    public void shouldCoinOut() {
        provider.send(Coin.CENT);
        assertSystemOut("Money dropped cent \n");

        provider.send(Coin.CENT, Coin.DIME, Coin.NICKEL);
        assertSystemOut("Money dropped cent dime nickel \n");

        provider.send(Coin.HALF_DOLLAR, Coin.ONE_DOLLAR, Coin.QUARTER);
        assertSystemOut("Money dropped halfdollar onedollar quarterdollar \n");
    }

    @Test
    public void shouldCoinIn() {
        assertEquals("Coin CENT should be taken as an input..", Coin.CENT, provider.get(Coin.CENT.tag()));
        assertEquals("Coin NICKEL should be taken as an input..", Coin.NICKEL, provider.get(Coin.NICKEL.tag()));
        assertEquals("Coin DIME should be taken as an input..", Coin.DIME, provider.get(Coin.DIME.tag()));
        assertEquals("Coin QUARTER should be taken as an input..", Coin.QUARTER, provider.get(Coin.QUARTER.tag()));
        assertEquals("Coin HALF_DOLLAR should be taken as an input..", Coin.HALF_DOLLAR, provider.get(Coin.HALF_DOLLAR.tag()));
        assertEquals("Coin ONE_DOLLAR should be taken as an input..", Coin.ONE_DOLLAR, provider.get(Coin.ONE_DOLLAR.tag()));
    }
}
