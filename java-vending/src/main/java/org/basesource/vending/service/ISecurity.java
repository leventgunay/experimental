package org.basesource.vending.service;

import org.basesource.vending.model.type.UserType;

public interface ISecurity {

    public boolean isSecure();

    public void login(UserType userType, String securityCode);

    public void logout();

    public UserType user();
}
