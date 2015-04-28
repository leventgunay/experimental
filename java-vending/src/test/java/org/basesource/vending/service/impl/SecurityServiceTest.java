package org.basesource.vending.service.impl;

import org.basesource.vending.base.AbstractTestCase;
import static org.junit.Assert.*;

import org.basesource.vending.model.type.UserType;
import org.junit.Before;
import org.junit.Test;

public class SecurityServiceTest extends AbstractTestCase {

    public SecurityService securityService;

    @Before
    public void initThis() {
        securityService = new SecurityService();
    }

    @Test
    public void shouldLogin() {
        testConfig().defaultUser(UserType.BUYER);
        assertNotEquals(securityService.user(), UserType.ADMIN);

        securityService.login(UserType.ADMIN, UserType.ADMIN.securityCode());

        assertEquals(securityService.user(), UserType.ADMIN);
    }

    @Test
    public void shouldNotSuccessLogin() {
        securityService.login(UserType.ADMIN, UserType.VENDOR.securityCode());

        assertNotEquals(securityService.user(), UserType.ADMIN);
    }

    @Test
    public void shouldLogout() {
        securityService.login(UserType.ADMIN, UserType.ADMIN.securityCode());

        assertEquals(securityService.user(), UserType.ADMIN);

        securityService.logout();

        assertNotEquals(securityService.user(), UserType.ADMIN);
        assertEquals(securityService.user(), testConfig().defaultUser());
    }

    @Test
    public void shouldHoldUser() {
        assertEquals("Should access default user config..", securityService.user(), testConfig().defaultUser());

        testConfig().defaultUser(UserType.ADMIN);
        assertEquals("Should access user config..", new SecurityService().user(), UserType.ADMIN);
        assertEquals("Should access user config..", new SecurityService().user(), testConfig().defaultUser());
    }

    @Test
    public void shouldIndicateInsecurityWhenUserIsNotExisting() {

        testConfig().defaultUser(null);
        assertFalse("Should listen default user..", new SecurityService().isSecure());

        testConfig().defaultUser(UserType.BUYER);
        assertTrue("Should listen default user..", new SecurityService().isSecure());

    }
}
