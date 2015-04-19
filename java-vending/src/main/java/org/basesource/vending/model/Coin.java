package org.basesource.vending.model;

public enum Coin {

    ONE_DOLLAR("onedollar", 100),

    HALF_DOLLAR("halfdollar", 50),

    QUARTER("quarterdollar", 25),

    DIME("dime", 10),

    NICKEL("nickel", 5),

    CENT("cent", 1);


    private String tag;
    private int value;

    Coin(String tag, int val) {
        this.tag = tag;
        this.value = val;
    }

    public String tag() {
        return this.tag;
    }

    public int value() {
        return this.value;
    }

    @Override
    public String toString() {
        return tag();
    }

    public static Coin by(String t) {
        for(Coin c : Coin.values()) {
            if(c.tag().equals(t)) {
                return c;
            }
        } return null;
    }

    public static Coin by(int val) {
        for(Coin c : Coin.values()) {
            if(c.value() == val) {
                return c;
            }
        } return null;
    }
}
