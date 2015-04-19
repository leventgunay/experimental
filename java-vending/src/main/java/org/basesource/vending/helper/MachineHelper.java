package org.basesource.vending.helper;

import org.basesource.vending.model.MachineConfig;

public class MachineHelper {

    private static MachineConfig config = null;

    public static MachineConfig config() {
        return config;
    }

    public static void config(MachineConfig conf) {
        config = conf;
    }
}
