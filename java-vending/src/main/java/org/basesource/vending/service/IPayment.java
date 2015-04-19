package org.basesource.vending.service;

import org.basesource.vending.model.Coin;
import org.basesource.vending.model.Inventory;

import java.io.Serializable;
import java.util.Map;

public interface IPayment<CURRENCY extends Serializable> {

    public CURRENCY advance(String code);

    public void charge(int amt);

    public void change();

    public boolean doingGood();

    public int currentCashAdvance();

    public void loadCash(Map<Coin, Integer> cash);

    public boolean purchase(Inventory item);
}
