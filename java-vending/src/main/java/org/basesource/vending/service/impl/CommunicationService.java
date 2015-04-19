package org.basesource.vending.service.impl;

import org.basesource.vending.annotations.Service;
import org.basesource.vending.helper.MachineHelper;
import org.basesource.vending.model.type.UserType;
import org.basesource.vending.service.ICommunicate;
import org.basesource.vending.service.MachineryService;

@Service
public class CommunicationService extends MachineryService<String,String> implements ICommunicate {

    private static final String DEFAULT_INPUT_QUESTION = "\nPlease enter your command:";

    @Override
    public void welcome(UserType userType) {
        say("Welcome!");
    }

    @Override
    public void goodbye(UserType userType) {
        if(userType.equals(MachineHelper.config().defaultUser())) {
            say("Goodbye! Machine is closing..");
        } else {
            say("Goodbye" + userType.name() + "!");
            say("-------------------------------");
        }
    }


    @Override
    public String ask() {
        return ask(DEFAULT_INPUT_QUESTION);
    }

    @Override
    public String ask(String question) {
        say(question);
        return in(question);
    }

    @Override
    public void say(String... statement) {
        out(statement);
    }
}
