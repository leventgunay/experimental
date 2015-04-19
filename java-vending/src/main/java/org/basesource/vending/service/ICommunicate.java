package org.basesource.vending.service;

import org.basesource.vending.model.type.UserType;

public interface ICommunicate {

    public void welcome(UserType userType);

    public void goodbye(UserType userType);

    public void ask();

}
