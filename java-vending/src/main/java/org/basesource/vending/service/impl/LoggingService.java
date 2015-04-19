package org.basesource.vending.service.impl;


import org.basesource.vending.annotations.Service;
import org.basesource.vending.model.type.SeverityType;
import org.basesource.vending.service.ILogging;
import org.basesource.vending.service.MachineryService;

@Service
public class LoggingService extends MachineryService<String, String> implements ILogging {


    @Override
    public void log(String desc) {

    }

    @Override
    public void log(String desc, SeverityType type) {

    }
}
