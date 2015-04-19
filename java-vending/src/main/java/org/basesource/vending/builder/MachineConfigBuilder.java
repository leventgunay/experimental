package org.basesource.vending.builder;

import org.basesource.vending.model.Coin;
import org.basesource.vending.model.Inventory;
import org.basesource.vending.model.MachineConfig;
import org.basesource.vending.model.type.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class MachineConfigBuilder {

    public final static Integer MAX_CASH_ADVANCE = 500;
    public final static Integer USER_WAITING_TIMEOUT = 5000;
    public final static Integer CONSECUTIVE_PRODUCT_DROP_COUNT = 1;
    public final static String SERVICE_PACKAGE_SCAN = "org.basesource.vending.service.impl";
    public final static String IO_PACKAGE_SCAN = "org.basesource.vending.io.impl";
    private static final int MINIMUM_CASH_ADVANCE = 50;

    protected MachineConfig config;

    public MachineConfigBuilder() {
        config = new MachineConfig();
    }


    public MachineConfigBuilder defaultVending() {
        config.allowCoin(Coin.ONE_DOLLAR)
        .allowCoin(Coin.HALF_DOLLAR)
        .allowCoin(Coin.QUARTER)
        .allowCoin(Coin.DIME)
        .max(MAX_CASH_ADVANCE) // 100 * Dollar
        .timeout(USER_WAITING_TIMEOUT) // milliseconds
        .consecutive(CONSECUTIVE_PRODUCT_DROP_COUNT)
        .defaultUser(UserType.BUYER)
        .serviceScan(SERVICE_PACKAGE_SCAN)
        .ioScan(IO_PACKAGE_SCAN)
        .minCash(MINIMUM_CASH_ADVANCE);

        return this;
    }

    public MachineConfigBuilder by(Properties properties) {
        // override config externally
        return this;
    }

    public MachineConfig build() {
        return config;
    }

    public MachineConfigBuilder defaultInventory() {
        config.defaultInventories(new ArrayList<Inventory>(){{
             add(new Inventory("11", "Crackers", 50, 10));
             add(new Inventory("12", "Chocolates", 100, 10));
             add(new Inventory("13", "Snacks", 100, 10));
             add(new Inventory("14", "Water", 60, 10));
             add(new Inventory("15", "Coke", 200, 10));
             add(new Inventory("16", "Beer", 300, 10));
        }});
        return this;
    }

    public MachineConfigBuilder defaultCash() {
        config.defaultCash(new HashMap<Coin, Integer>() {{
            put(Coin.ONE_DOLLAR, 0);
            put(Coin.HALF_DOLLAR, 100);
            put(Coin.QUARTER, 100);
            put(Coin.DIME, 100);
            put(Coin.NICKEL, 100);
            put(Coin.CENT, 100);
        }});
        return this;
    }
}
