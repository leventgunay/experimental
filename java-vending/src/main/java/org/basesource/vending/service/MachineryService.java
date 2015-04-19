package org.basesource.vending.service;

import org.basesource.vending.io.InputProvider;
import org.basesource.vending.io.OutputProvider;

import java.io.Serializable;

public abstract class MachineryService<IT extends Serializable, OT extends Serializable> {

    protected InputProvider<IT> in;
    protected OutputProvider<OT> out;

    public void init(InputProvider<IT> in, OutputProvider<OT> out) {
        this.in = in;
        this.out = out;
    }

    protected IT in(String input) {
        return in.get(input);
    }

    protected void out(OT output) {
        out.send(output);
    }

}
