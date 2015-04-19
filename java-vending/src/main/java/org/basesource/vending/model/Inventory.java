package org.basesource.vending.model;

public class Inventory {

    private int cost;
    private int productCount;
    private String code;
    private String desc;

    public Inventory(String code, String desc, int cost, int count) {
        code(code);
        desc(desc);
        cost(cost);
        productCount(count);
    }

    public int cost() {
        return cost;
    }

    public void cost(int cost) {
        this.cost = cost;
    }

    public int productCount() {
        return productCount;
    }

    public void productCount(int productCount) {
        this.productCount = productCount;
    }

    public String code() {
        return code;
    }

    public void code(String code) {
        this.code = code;
    }

    public String desc() {
        return desc;
    }

    public void desc(String desc) {
        this.desc = desc;
    }
}
