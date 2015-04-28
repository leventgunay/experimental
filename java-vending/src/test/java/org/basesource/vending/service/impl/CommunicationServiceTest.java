package org.basesource.vending.service.impl;

import org.basesource.vending.base.AbstractTestCase;
import org.basesource.vending.io.InputProvider;
import org.basesource.vending.io.OutputProvider;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

public class CommunicationServiceTest extends AbstractTestCase {

    @Mock
    protected InputProvider<String> in;

    @Mock
    protected OutputProvider<String> out;

    protected CommunicationService communicationService = new CommunicationService();

    @Before
    public void initThis() {
        communicationService.init(in, out);
        doReturn("Test").when(in).get(anyString());
        doNothing().when(out).send(Matchers.<String[]>any());
    }

    @Test
    public void shouldWelcome() {
        communicationService.welcome(null);

        verify(out, only()).send(eq("Welcome!"));
        verifyZeroInteractions(in);
    }

    @Test
    public void shouldAsk() {

        assertEquals(communicationService.ask("Question?"), "Test");

        verify(in, only()).get("Question?");
    }

    @Test
    public void shouldSay() {
        String[] statements = new String[]{"Statement1", "Statement2", "Statement3"};
        communicationService.say(statements);

        verify(out, only()).send(statements);
        verifyNoMoreInteractions(in);
    }
}
