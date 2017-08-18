import org.basesource.reservoir.Streamer;
import org.basesource.reservoir.impl.BaseStreamer;
import org.basesource.reservoir.impl.HttpStreamer;
import org.basesource.reservoir.impl.RandomSampler;
import org.basesource.reservoir.impl.RandomStreamer;

public class Main {

    public static void main(String[] args) {

        assert (args.length > 0) : "Please pass the first argument as sample size.";

        assert (args[0].trim().matches("^[1-9]+\\d*$")) : "Sample size should be numeric and greater than zero.";

        // default running state
        int samplesize = Integer.valueOf(args[0]);
        Streamer streamer = new RandomStreamer(samplesize);

        // input options
        if(args.length > 1 && !args[1].equalsIgnoreCase("-random")) {
            if(args[1].equalsIgnoreCase("-http") && args.length > 2) {
                streamer = new HttpStreamer(args[2]);
            } else {
                streamer = new BaseStreamer(args[1]);
            }
        }

        System.out.println("Result: " + new RandomSampler().sample(streamer, samplesize));
    }

}
