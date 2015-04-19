package org.basesource.vending.model;

import org.basesource.vending.model.type.UserType;

import java.util.*;

public class MachineConfig {

    private Integer max;
    private Integer timeout;
    private Integer consecutive;
    private Set<Coin> allowedCoins = new HashSet<Coin>();
    private String serviceScan;
    private String ioScan;
    private UserType defaultUser;
    private List<Inventory> defaultInventories;
    private Map<Coin, Integer> defaultCash;
    private int minCash;

    public String serviceScan() {
        return serviceScan;
    }

    public MachineConfig serviceScan(String pkg) {
        serviceScan = pkg;
        return this;
    }

    public String ioScan() {
        return ioScan;
    }

    public MachineConfig ioScan(String ioScan) {
        this.ioScan = ioScan;
        return this;
    }

    public MachineConfig allowCoin(Coin coin) {
        allowedCoins.add(coin);
        return this;
    }

    public Collection<Coin> allowedCoins() {
        return allowedCoins;
    }

    public int max() {
        return max.intValue();
    }

    public MachineConfig max(Integer m) {
        max = m;
        return this;
    }

    public int timeout() {
        return timeout.intValue();
    }

    public MachineConfig timeout(Integer t) {
        timeout = t;
        return this;
    }

    public int consecutive() {
        return consecutive.intValue();
    }

    public MachineConfig consecutive(Integer c) {
        consecutive = c;
        return this;
    }

    public MachineConfig defaultUser(UserType def) {
        defaultUser = def;
        return this;
    }

    public UserType defaultUser() {
        return defaultUser;
    }

    public List<Inventory> defaultInventories() {
        return defaultInventories;
    }

    public void defaultInventories(List<Inventory> defaultInventories) {
        this.defaultInventories = defaultInventories;
    }

    public Map<Coin, Integer> defaultCash() {
        return defaultCash;
    }

    public void defaultCash(Map<Coin, Integer> defaultCash) {
        this.defaultCash = defaultCash;
    }

    public int minCash() {
        return this.minCash;
    }

    public void minCash(int amt) {
        this.minCash = amt;
    }
}
