package org.basesource.vending.service;

import org.basesource.vending.model.type.UserType;

public interface ICommunicate {

    public void welcome(UserType userType);

    public void goodbye(UserType userType);

    public String ask();

    public String ask(String question);

    public void say(String... question);

}
