package org.basesource.vending.model.type;

public enum UserType {

    ADMIN("A"),
    BUYER("B"),
    CASHIER("C"),
    VENDOR("V");

    protected String tag;

    UserType(String tag) {
        this.tag = tag;
    }

    public String tag() {
        return this.tag;
    }

    public UserType by(String t) {
        for(UserType u : UserType.values()) {
            if(u.tag().equals(t)) {
                return u;
            }
        } return null;
    }
}
