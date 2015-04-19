import org.basesource.vending.builder.MachineConfigBuilder;
import org.basesource.vending.model.Machine;
import org.basesource.vending.model.MachineConfig;
import org.basesource.vending.model.VendingMachine;
import org.basesource.vending.processor.CustomizedVendingMachineProcessor;

public class Application {

    public static void main(String[] args) {

        // configuration
        MachineConfig vendingConfig = new MachineConfigBuilder()
                .defaultVending()
                .defaultInventory()
                .defaultCash()
                .by(System.getProperties())
                .build();

        // execution
        Machine vendingMachine = new VendingMachine()
                .config(vendingConfig)
                .processor(new CustomizedVendingMachineProcessor())
                .start();

    }
}
