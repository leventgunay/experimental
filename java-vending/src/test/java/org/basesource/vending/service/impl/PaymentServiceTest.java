package org.basesource.vending.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.basesource.vending.base.AbstractTestCase;
import org.basesource.vending.io.InputProvider;
import org.basesource.vending.io.OutputProvider;
import org.basesource.vending.model.Coin;
import org.basesource.vending.model.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;

public class PaymentServiceTest extends AbstractTestCase {

    @Mock
    protected InputProvider<Coin> in;

    @Mock
    protected OutputProvider<Coin> out;

    protected PaymentService paymentService = new PaymentService();

    @Before
    public void initThis() {
        paymentService.init(in, out);

        paymentService.loadCash(testConfig().defaultCash());

        assertEquals(paymentService.currentCashAdvance(), 0);

        assertEquals(paymentService.cash, testConfig().defaultCash());
    }

    @Test
    public void shouldAdvance() {

        Integer previousCentCount = paymentService.cash.get(Coin.CENT);

        doReturn(Coin.CENT).when(in).get(Coin.CENT.tag());

        Coin advanced = paymentService.advance(Coin.CENT.tag());

        assertEquals(advanced, Coin.CENT);

        assertTrue(paymentService.cash.get(Coin.CENT) == previousCentCount + 1);

        assertEquals(paymentService.currentCashAdvance(), Coin.CENT.value());

        verify(in, only()).get(eq(Coin.CENT.tag()));

    }


    @Test
    public void shouldCharge() {
        doReturn(Coin.HALF_DOLLAR).when(in).get(Coin.HALF_DOLLAR.tag());

        paymentService.advance(Coin.HALF_DOLLAR.tag());

        assertEquals(paymentService.currentCashAdvance(), Coin.HALF_DOLLAR.value());

        paymentService.charge(Coin.DIME.value());

        assertEquals(paymentService.currentCashAdvance(), Coin.HALF_DOLLAR.value() - Coin.DIME.value());
    }


    @Test
    public void shouldBeDoingGood() {

        assertTrue(paymentService.doingGood());

    }

    @Test
    public void shouldNotBeDoingGood() {

        paymentService.loadCash(null);

        assertFalse(paymentService.doingGood());

        paymentService.loadCash(Collections.<Coin, Integer>emptyMap());

        assertFalse(paymentService.doingGood());

        paymentService.loadCash(testConfig().defaultCash());

        paymentService.charge(Coin.HALF_DOLLAR.value());

        assertFalse(paymentService.doingGood());

    }

    @Test
    public void shouldNotBeAbleToPurchase() {

        assertFalse(paymentService.purchase(null));

        Inventory inv = new Inventory("Code", "Product", 100, 10);

        assertFalse(paymentService.purchase(inv));

    }

    @Test
    public void shouldBeAbleToPurchase() {
        Inventory inv = new Inventory("Code", "Product", 100, 10);

        doReturn(Coin.HALF_DOLLAR).when(in).get(Coin.HALF_DOLLAR.tag());
        paymentService.advance(Coin.HALF_DOLLAR.tag());

        assertFalse(paymentService.purchase(inv));

        paymentService.advance(Coin.HALF_DOLLAR.tag());

        assertTrue(paymentService.purchase(inv));

        assertEquals(paymentService.currentCashAdvance(), 0);
    }
}
