package org.basesource.vending.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.basesource.vending.annotations.Secure;
import org.basesource.vending.annotations.Service;
import org.basesource.vending.helper.MachineHelper;
import org.basesource.vending.model.Coin;
import org.basesource.vending.model.Inventory;
import org.basesource.vending.model.type.UserType;
import org.basesource.vending.service.IInventory;
import org.basesource.vending.service.IPresentation;
import org.basesource.vending.service.MachineryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendingService extends MachineryService<String,String> implements IInventory, IPresentation {

    private static final String CODE_COLUMN_HEADER = "Code";
    private static final String PRODUCT_COLUMN_HEADER = "Product";
    private static final String COST_COLUMN_HEADER = "$";
    private static final String COUNT_COLUMN_HEADER = "#";

    private static final String COLUMN_HEADER_BORDER = "----------";

    private static final String NO_PRODUCT_MESSAGE = "\nThere is not any product at this moment.\n";
    private static final String BUY_COMMAND = "<Code> - Buy\n";
    private static final String NOT_ABLE_TO_BUY = "Credit is not enough to purchase..\n";
    private static final String[] DEFAULT_COMMANDS =  new String[] {
            "P - Put Coin",
            "A - Admin",
            "C - Cashier",
            "V - Vendor"
    };

    private static final String[] VENDOR_COMMANDS = new String[] {
            "L - Load Product",
            "E - Exit"
    };

    private static final String[] ADMIN_COMMANDS = new String[] {
            "S - System Status",
            "E - Exit"
    };

    private static final String[] CASHIER_COMMANDS = new String[] {
            "T - Take Cash",
            "E - Exit"
    };
    private static final String LINE_BREAK = " ";

    private List<Inventory> inventories = new ArrayList<Inventory>();

    @Override
    public void load(Inventory inventory) {
        inventories.add(inventory);
    }

    @Override
    public void load(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    @Override
    public Inventory pull(String code) {
        for(Inventory inventory : inventories) {
            if(inventory.code().equalsIgnoreCase(code) && inventory.productCount() > 0) {
                return inventory;
            }
        }
        return null;
    }

    @Override
    public boolean hasItem() {
        for(Inventory inventory : inventories) {
            if(inventory.productCount() > 0) {
                return true;
            }
        } return false;
    }

    @Override
    public void drop(Inventory item) {
        if(item != null && item.productCount() > 0) {
            item.productCount(item.productCount() - 1);
        }
    }


    @Override
    public void presentDefault(int cashAdvance) {
        if(CollectionUtils.isEmpty(inventories)) {
            out(NO_PRODUCT_MESSAGE);
        } else {
            out("\nCredit: " + cashAdvance + "\n");
            out(CODE_COLUMN_HEADER, PRODUCT_COLUMN_HEADER, COST_COLUMN_HEADER, COUNT_COLUMN_HEADER);
            out(COLUMN_HEADER_BORDER, COLUMN_HEADER_BORDER, COLUMN_HEADER_BORDER, COLUMN_HEADER_BORDER);

            for(Inventory inventory : inventories) {
                if(inventory.productCount() > 0) {
                    out(inventory.code(), inventory.desc(), String.valueOf(inventory.cost()), String.valueOf(inventory.productCount()));
                }
            }
        }

        out(LINE_BREAK);
        if(cashAdvance < MachineHelper.config().minCash()) out(NOT_ABLE_TO_BUY);
        else out(BUY_COMMAND);
        out(DEFAULT_COMMANDS);
    }

    @Override
    public void presentVendorUI() {
        out("Vendor UI is not ready..");
        out(VENDOR_COMMANDS);
    }

    @Override
    public void presentAdminUI() {
        out("Admin UI is not ready..");
        out(ADMIN_COMMANDS);
    }

    @Override
    public void presentCashierUI() {
        out("Cashier UI is not ready..");
        out(CASHIER_COMMANDS);
    }

    @Override
    public void presentCashInput() {
        for(Coin coin : MachineHelper.config().allowedCoins()) {
            out(coin.tag() + " - 1/" + 100/coin.value() + "$");
        }
    }
}
