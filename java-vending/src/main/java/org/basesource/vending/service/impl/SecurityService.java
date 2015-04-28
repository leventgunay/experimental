package org.basesource.vending.service.impl;

import org.basesource.vending.annotations.Service;
import org.basesource.vending.helper.MachineHelper;
import org.basesource.vending.model.type.UserType;
import org.basesource.vending.service.ISecurity;
import org.basesource.vending.service.MachineryService;

@Service
public class SecurityService extends MachineryService<String, String> implements ISecurity {

    private UserType user = MachineHelper.config().defaultUser();

    @Override
    public boolean isSecure() {
        return user != null;
    }

    @Override
    public void login(UserType userType, String securityCode) {
        if(userType.securityCode().equals(securityCode)) {
            user = userType;
        }
    }

    @Override
    public void logout() {
        user = MachineHelper.config().defaultUser();
    }

    @Override
    public UserType user() {
        return user;
    }
}
