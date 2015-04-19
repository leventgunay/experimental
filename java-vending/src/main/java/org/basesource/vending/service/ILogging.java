package org.basesource.vending.service;

import org.basesource.vending.model.type.SeverityType;

public interface ILogging {

    public void log(String desc);

    public void log(String desc, SeverityType type);

}
