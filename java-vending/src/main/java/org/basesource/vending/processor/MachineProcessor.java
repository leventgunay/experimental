package org.basesource.vending.processor;

import org.basesource.vending.model.Machine;
import org.basesource.vending.model.MachineState;
import org.basesource.vending.service.*;

public abstract class MachineProcessor implements Runnable {

    protected ISecurity security;
    protected IConfigure configuration;
    protected ILogging logger;

    public void securityService(ISecurity service) {
        security = service;
    }

    public void configurationService(IConfigure service) {
        configuration = service;
    }

    public void loggingService(ILogging service) {
        logger = service;
    }
}
