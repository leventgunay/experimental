package org.basesource.vending.base;

import org.basesource.vending.builder.MachineConfigBuilder;
import org.basesource.vending.helper.MachineHelper;
import org.basesource.vending.model.MachineConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MachineHelper.class)
public abstract class AbstractTestCase {

    protected MachineConfig testConfig = new MachineConfigBuilder()
            .defaultVending().defaultInventory().defaultCash().build();;

    @Before
    public void init() {
        PowerMockito.mockStatic(MachineHelper.class);

        when(MachineHelper.config()).thenReturn(testConfig());

        assertNotNull("Config should exist.", testConfig());

        assertEquals("Should mock", MachineHelper.config(), testConfig());
    }

    public MachineConfig testConfig() {
        return this.testConfig;
    }

    public void testConfig(MachineConfig config) {
        this.testConfig = config;
    }
}
