package org.basesource.vending.service;

import org.basesource.vending.model.Inventory;

import java.util.List;

public interface IInventory {

    public void load(Inventory inventory);
    public void load(List<Inventory> inventories);

    public Inventory pull(String code);

    public boolean hasItem();

    public void drop(Inventory item);
}
