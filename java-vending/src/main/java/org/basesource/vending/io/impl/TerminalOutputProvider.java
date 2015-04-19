package org.basesource.vending.io.impl;

import org.basesource.vending.io.OutputProvider;

public class TerminalOutputProvider implements OutputProvider<String> {

    @Override
    public void send(String... strings) {
        System.out.printf(getStringFormat(strings.length), (Object[])strings);
    }

    private String getStringFormat(int length) {
        StringBuilder format = new StringBuilder();
        for(int i = 0; i < length; i++) {
            format.append("%-15s");
        }
        return format.append("%n").toString();
    }
}
