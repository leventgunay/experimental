package org.basesource.vending.io.impl;

import org.basesource.vending.exception.MachineRuntimeException;
import org.basesource.vending.io.InputProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalInputProvider implements InputProvider<String> {

    protected BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String get(String tag)  {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new MachineRuntimeException(e, "Error: Machine IO Problem..");
        }
    }

    public void reader(BufferedReader in) {
        this.in = in;
    }
}
