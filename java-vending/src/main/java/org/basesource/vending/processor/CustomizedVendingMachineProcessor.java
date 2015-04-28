package org.basesource.vending.processor;

import org.basesource.vending.model.Coin;
import org.basesource.vending.model.Inventory;
import org.basesource.vending.model.type.UserType;
import org.basesource.vending.service.*;

public class CustomizedVendingMachineProcessor extends MachineProcessor {

    protected ICommunicate communication;
    protected IPresentation presentation;
    protected IPayment<Coin> payment;
    protected IInventory inventory;

    @Override
    public void run() {

        communication.welcome(security.user());

        while(security.isSecure() && payment.doingGood()) {

            switch (security.user()) {
                case ADMIN: presentation.presentAdminUI(); break;
                case CASHIER: presentation.presentCashierUI(); break;
                case VENDOR:presentation.presentVendorUI(); break;
                default: presentation.presentDefault(payment.currentCashAdvance());
            }

            // next command
            runCommand(communication.ask());

        }

        communication.goodbye(security.user());
    }

    private void runCommand(String command) {
        communication.say(" ");
        UserType newuser = null;
        if((newuser = UserType.by(command)) != null) {
            security.login(newuser, communication.ask("Security Code ?"));
        } else if(command.equalsIgnoreCase("E")) {
            communication.goodbye(security.user());
            security.logout();
        } else if(command.equalsIgnoreCase("P")) {
            presentation.presentCashInput();
            payment.advance(communication.ask("Enter cash code: "));
        } else {
            Inventory item = inventory.pull(command);
            if(item != null && payment.purchase(item)) {
                communication.say(item.desc() + " dropped..");
                inventory.drop(item);
            } else {
                communication.say("Unable to purchase..");
            }
        }
    }

    public void communicationService(ICommunicate service) {
        communication = service;
    }

    public void presentationService(IPresentation service) {
        presentation = service;
    }

    public void paymentService(IPayment service) {
        payment = service;
    }

    public void inventoryService(IInventory service) {
        inventory = service;
    }
}
