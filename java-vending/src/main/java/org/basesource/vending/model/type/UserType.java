package org.basesource.vending.model.type;

public enum UserType {

    ADMIN("A", "scAdmin"),
    BUYER("B"),
    CASHIER("C", "scCashier"),
    VENDOR("V", "scVendor");

    protected String tag;
    protected String securityCode;

    UserType(String tag) {
        this.tag = tag;
    }
    UserType(String tag, String sc) {
        this(tag);
        this.securityCode = sc;
    }

    public String tag() {
        return this.tag;
    }

    public String securityCode() { return this.securityCode; }

    public static UserType by(String t) {
        for(UserType u : UserType.values()) {
            if(u.tag().equals(t)) {
                return u;
            }
        } return null;
    }
}
