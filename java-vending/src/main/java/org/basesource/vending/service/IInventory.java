package org.basesource.vending.service;

import org.basesource.vending.model.Inventory;

public interface IInventory {

    public void load(Inventory inventory);

    public Inventory pull(String code);

}
