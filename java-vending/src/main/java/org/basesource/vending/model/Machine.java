package org.basesource.vending.model;

import org.basesource.vending.annotations.ReflectionUtils;
import org.basesource.vending.exception.MachineRuntimeException;
import org.basesource.vending.helper.MachineHelper;
import org.basesource.vending.io.InputProvider;
import org.basesource.vending.io.OutputProvider;
import org.basesource.vending.processor.MachineProcessor;
import org.basesource.vending.service.MachineryService;

import java.util.Collection;

public abstract class Machine {

    protected MachineConfig config;

    protected MachineState state;

    protected Thread thread;

    public Machine config(MachineConfig config) {
        this.config = config;
        return this;
    }

    public MachineConfig config() {
        return this.config;
    }

    public abstract MachineProcessor processor();

    public abstract Machine processor(MachineProcessor processor);

    public Machine state(MachineState state) {
        this.state = state;
        return this;
    }

    public MachineState state() {
        return this.state;
    }

    public Machine start() {
        MachineHelper.config(config());

        loadIO();
        loadServices();

        thread = new Thread(setup().processor());

        thread.start();

        return this;
    }

    protected void loadIO() {
        Collection<Class<? extends InputProvider>> ips
                = ReflectionUtils.scanTypes(config().ioScan(), InputProvider.class);

        Collection<Class<? extends OutputProvider>> ops
                = ReflectionUtils.scanTypes(config().ioScan(), OutputProvider.class);

        addInputProviders(ips);
        addOutputProviders(ops);
    }

    protected void loadServices() {
        Collection<Class<? extends MachineryService>> services
                = ReflectionUtils.scanTypes(config().serviceScan(), MachineryService.class);

        for(Class<? extends MachineryService> service : services) {
            try {

                addService(service.newInstance());

            } catch (Exception e) {
                throw new MachineRuntimeException(e, "Error: Unable to load machinery services.");
            }
        }
    }

    protected abstract Machine setup();

    protected abstract void addInputProviders(Collection<Class<? extends InputProvider>> ips);
    protected abstract void addOutputProviders(Collection<Class<? extends OutputProvider>> ops);

    protected abstract void addService(MachineryService service);
    protected abstract void removeService(MachineryService service);

    public Machine stop() throws InterruptedException {

        if(thread != null) {
            thread.interrupt();
            thread.join();
        } else {
            throw new MachineRuntimeException("Error: Thread not found!");
        }

        return this;
    }
}
