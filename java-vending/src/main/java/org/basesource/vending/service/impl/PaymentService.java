package org.basesource.vending.service.impl;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.basesource.vending.annotations.Service;
import org.basesource.vending.model.Coin;
import org.basesource.vending.model.Inventory;
import org.basesource.vending.service.IPayment;
import org.basesource.vending.service.MachineryService;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService extends MachineryService<Coin, Coin> implements IPayment<Coin> {

    private Map<Coin, Integer> cash = new HashMap<Coin, Integer>();

    private int currentCashAdvance;

    @Override
    public Coin advance(String code) {
        Coin add = in(code);
        if(add != null) {
            currentCashAdvance += add.value();
            Integer count = cash.get(add);
            cash.put(add, count + 1);
        }
        return add;
    }

    @Override
    public void charge(int amt) {
        currentCashAdvance -= amt;
    }

    @Override
    public void change() {
        while(currentCashAdvance > 0) {
            for( Map.Entry<Coin, Integer> e : cash.entrySet()) {
                Coin coin = e.getKey();
                if(coin.value() <= currentCashAdvance()) {
                    currentCashAdvance -= coin.value();
                    e.setValue(e.getValue()-1);
                    out(coin);
                }
            }
        }
    }

    @Override
    public boolean doingGood() {
        return currentCashAdvance() > -1 && !cash.isEmpty();
    }

    @Override
    public int currentCashAdvance() {
        return currentCashAdvance;
    }

    @Override
    public void loadCash(Map<Coin, Integer> cash) {
        this.cash.putAll(cash);
    }

    @Override
    public boolean purchase(Inventory item) {
        if(item != null && item.cost() <= currentCashAdvance()) {
            charge(item.cost());
            change();

            return Boolean.TRUE;
        } return Boolean.FALSE;
    }
}
