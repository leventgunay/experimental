package org.basesource.vending.service;

public interface IPresentation {

    public void presentDefault(int cashAdvance);

    public void presentVendorUI();

    public void presentAdminUI();

    public void presentCashierUI();

    public void presentCashInput();

}
