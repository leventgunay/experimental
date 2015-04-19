package org.basesource.vending.service.impl;

import org.basesource.vending.model.Inventory;
import org.basesource.vending.service.IVending;
import org.basesource.vending.service.MachineryService;

public class VendingService extends MachineryService<String,String> implements IVending {


    @Override
    public void load(Inventory inventory) {

    }

    @Override
    public void pull(String code) {

    }
}
