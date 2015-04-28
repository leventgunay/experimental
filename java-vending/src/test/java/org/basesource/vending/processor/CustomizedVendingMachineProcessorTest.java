package org.basesource.vending.processor;

import org.basesource.vending.base.AbstractTestCase;
import org.basesource.vending.model.Coin;
import org.basesource.vending.model.type.UserType;
import org.basesource.vending.service.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class CustomizedVendingMachineProcessorTest extends AbstractTestCase {

    @Mock
    protected ISecurity security;

    @Mock
    protected IConfigure configuration;

    @Mock
    protected ILogging logger;

    @Mock
    protected ICommunicate communication;

    @Mock
    protected IPresentation presentation;

    @Mock
    protected IPayment<Coin> payment;

    @Mock
    protected IInventory inventory;

    @InjectMocks
    protected CustomizedVendingMachineProcessor processor = new CustomizedVendingMachineProcessor();

    @Before
    public void initThis() {
        assertNotNull(communication);
        assertNotNull(presentation);
        assertNotNull(payment);
        assertNotNull(inventory);
        assertNotNull(security);
        assertNotNull(configuration);
        assertNotNull(logger);
        assertNotNull(processor);
        assertEquals(processor.communication, communication);
        assertEquals(processor.presentation, presentation);
        assertEquals(processor.payment, payment);
        assertEquals(processor.inventory, inventory);
        assertEquals(processor.security, security);
        assertEquals(processor.configuration, configuration);
        assertEquals(processor.logger, logger);
    }

    @Test
    public void shouldGreetUser() {
        doReturn(UserType.ADMIN).when(security).user();

        processor.run();

        verify(communication, times(1)).welcome(eq(UserType.ADMIN));
        verify(communication, times(1)).goodbye(eq(UserType.ADMIN));

    }

    @Test
    public void shouldExitIfNotSecure() {
        doReturn(false).when(security).isSecure();

        processor.run();

        verify(security, times(1)).isSecure();
        verify(communication, never()).ask();
        verify(communication, never()).ask(anyString());
        verifyZeroInteractions(payment);
        verifyZeroInteractions(presentation);
    }

    @Test
    public void shouldExitIfPaymentNotDoingGood() {
        doReturn(true).when(security).isSecure();
        doReturn(false).when(payment).doingGood();

        processor.run();

        verify(security, times(1)).isSecure();
        verify(payment, only()).doingGood();
        verify(communication, never()).ask();
        verify(communication, never()).ask(anyString());
        verifyZeroInteractions(presentation);
    }

    protected void shouldReceiveByUserType(UserType user) {
        doReturn(user).when(security).user();
        doReturn(true).when(payment).doingGood();
        doReturn("Sample Command").when(communication).ask();

        final Boolean[] isSecure = {true};
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                if(isSecure[0]) {
                    isSecure[0] = false;
                    return true;
                } return false;
            }
        }).when(security).isSecure();

        processor.run();

        verify(communication, times(1)).ask();
        verify(communication, timeout(100)).ask();

        if(user.equals(UserType.ADMIN)) {
            verify(presentation, only()).presentAdminUI();
            verify(payment, only()).doingGood();
        } else if(user.equals(UserType.CASHIER)) {
            verify(presentation, only()).presentCashierUI();
            verify(payment, only()).doingGood();
        } else if(user.equals(UserType.VENDOR)) {
            verify(presentation, only()).presentVendorUI();
            verify(payment, only()).doingGood();
        } else if(user.equals(UserType.BUYER)) {
            verify(presentation, only()).presentDefault(0);
            verify(payment, times(1)).doingGood();
            verify(payment, times(1)).currentCashAdvance();
        }
    }

    @Test
    public void shouldReceiveAdmin() {
        shouldReceiveByUserType(UserType.ADMIN);
    }

    @Test
    public void shouldReceiveCashier() {
        shouldReceiveByUserType(UserType.CASHIER);
    }

    @Test
    public void shouldReceiveVendor() {
        shouldReceiveByUserType(UserType.VENDOR);
    }

    @Test
    public void shouldReceiveBuyer() {
        shouldReceiveByUserType(UserType.BUYER);
    }

    @Test
    public void shouldRunCommands() {

    }
}
