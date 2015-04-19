package org.basesource.vending.service.impl;

import org.basesource.vending.annotations.Secure;
import org.basesource.vending.annotations.Service;
import org.basesource.vending.service.IConfigure;
import org.basesource.vending.service.MachineryService;

@Secure
@Service
public class ConfigurationService extends MachineryService<String, String> implements IConfigure {


}
