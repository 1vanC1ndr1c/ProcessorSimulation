package project.model.processor.behavior.signals;

import project.logic.HexHandler;
import project.model.processor.Accumulator;
import project.model.processor.InternalBus;

public final class AND extends BaseSignal {

    private static final AND INC = new AND();

    public static AND getInstance() {
        return INC;
    }


    //And the 'Q' (which is a line from intBus)
    // with 'P' (which is a line from the accumulator).
    @Override
    public void signal() {
        String intBus = InternalBus.getInstance().getValue();
        String accumulator = Accumulator.getInstance().getValue();

        String result = HexHandler.andNumbers(intBus, accumulator);

        Accumulator.getInstance().setValue(result);

        super.printData();

        //checkAccumulator:
        //https://www.rapidtables.com/calc/math/binary-calculator.html
    }
}
