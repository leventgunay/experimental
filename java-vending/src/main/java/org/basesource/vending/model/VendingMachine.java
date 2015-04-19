package org.basesource.vending.model;

import org.basesource.vending.annotations.ReflectionUtils;
import org.basesource.vending.exception.MachineRuntimeException;
import org.basesource.vending.helper.MachineHelper;
import org.basesource.vending.io.InputProvider;
import org.basesource.vending.io.OutputProvider;
import org.basesource.vending.processor.CustomizedVendingMachineProcessor;
import org.basesource.vending.processor.MachineProcessor;
import org.basesource.vending.service.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VendingMachine extends Machine {

    private CustomizedVendingMachineProcessor processor;
    private Map<Class, MachineryService> services = new HashMap<Class, MachineryService>();
    private Map<Class<? extends InputProvider>, InputProvider> inputProvides = new HashMap<Class<? extends InputProvider>, InputProvider>();
    private Map<Class<? extends OutputProvider>, OutputProvider> outputProvides = new HashMap<Class<? extends OutputProvider>, OutputProvider>();

    @Override
    protected Machine setup() {

        processor.securityService((ISecurity) services.get(ISecurity.class));
        processor.configurationService((IConfigure) services.get(IConfigure.class));
        processor.loggingService((ILogging) services.get(ILogging.class));
        processor.communicationService((ICommunicate) services.get(ICommunicate.class));
        processor.presentationService((IPresentation) services.get(IPresentation.class));

        IPayment paymentService = (IPayment) services.get(IPayment.class);
        paymentService.loadCash(MachineHelper.config().defaultCash());
        processor.paymentService(paymentService);

        IInventory inventoryService = (IInventory) services.get(IInventory.class);
        inventoryService.load(config().defaultInventories());
        processor.inventoryService(inventoryService);

        return this;
    }

    @Override
    protected void addInputProviders(Collection<Class<? extends InputProvider>> ips) {
        try {
            for (Class<? extends InputProvider> ip : ips) {
                inputProvides.put(ReflectionUtils.genInterfaceParamType(ip, InputProvider.class, 0), ip.newInstance());
            }
        } catch (Exception e) {
            throw new MachineRuntimeException(e, "Error: Unable to load IO providers.");
        }
    }

    @Override
    protected void addOutputProviders(Collection<Class<? extends OutputProvider>> ops) {
        try {
            for (Class<? extends OutputProvider> op : ops) {
                outputProvides.put(ReflectionUtils.genInterfaceParamType(op, OutputProvider.class, 0), op.newInstance());
            }
        } catch (Exception e) {
            throw new MachineRuntimeException(e, "Error: Unable to load IO providers.");
        }
    }

    @Override
    protected void addService(MachineryService service) {
        InputProvider ip = inputProvides.get(ReflectionUtils.genSuperClassParamType(service.getClass(), 0));
        OutputProvider op = outputProvides.get(ReflectionUtils.genSuperClassParamType(service.getClass(), 1));

        service.init(ip, op);

        for(Class iservice : service.getClass().getInterfaces()) {
            services.put(iservice, service);
        }
    }

    @Override
    protected void removeService(MachineryService service) {
        services.remove(service.getClass());
    }


    @Override
    public MachineProcessor processor() {
        return processor;
    }

    @Override
    public Machine processor(MachineProcessor processor) {
        this.processor = (CustomizedVendingMachineProcessor) processor;
        return this;
    }


}
