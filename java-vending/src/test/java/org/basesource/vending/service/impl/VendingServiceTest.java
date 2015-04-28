package org.basesource.vending.service.impl;

import org.basesource.vending.base.AbstractTestCase;
import org.basesource.vending.io.InputProvider;
import org.basesource.vending.io.OutputProvider;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.basesource.vending.model.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

public class VendingServiceTest extends AbstractTestCase {

    @Mock
    protected InputProvider<String> in;

    @Mock
    protected OutputProvider<String> out;

    protected VendingService vendingService = new VendingService();

    @Before
    public void initThis() {
        vendingService.init(in, out);
        doReturn("Test").when(in).get(anyString());
        doNothing().when(out).send(Matchers.<String[]>any());
    }


    @Test
    public void shouldCheckIfThereIsItem() {

        assertFalse(vendingService.hasItem());

        vendingService.load(new Inventory("11", "Products", 100, 0));

        assertFalse(vendingService.hasItem());

        vendingService.load(new Inventory("12", "Coffee", 100, 4));

        assertTrue(vendingService.hasItem());

        vendingService.load(Collections.<Inventory>emptyList());

        assertFalse(vendingService.hasItem());
    }

    @Test
    public void shouldDropItem() {

    }

    @Test
    public void shouldNotDropItem() {
        Inventory inv = new Inventory("11", "Coffee", 100, 0);

        vendingService.load(new ArrayList<Inventory>());

        vendingService.load(inv);

        vendingService.drop(inv);

        assertEquals(inv.productCount(), 0);

        inv.productCount(10);

        vendingService.drop(inv);

        assertEquals(inv.productCount(), 9);
    }

    @Test
    public void shouldPullItem() {

    }

    @Test
    public void shouldNotPullItem() {

    }

    @Test
    public void shouldPresentUIForDefault() {


    }

    @Test
    public void shouldPresentUIForAdmin() {


    }

    @Test
    public void shouldPresentUIForVendor() {


    }

    @Test
    public void shouldPresentUIForCashier() {


    }
}
