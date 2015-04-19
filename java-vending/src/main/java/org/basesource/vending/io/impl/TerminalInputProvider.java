package org.basesource.vending.io.impl;

import org.basesource.vending.io.InputProvider;

import java.util.Scanner;

public class TerminalInputProvider implements InputProvider<String> {

    protected Scanner in = new Scanner(System.in);

    @Override
    public String get(String tag) {
        return in.nextLine();
    }
}
