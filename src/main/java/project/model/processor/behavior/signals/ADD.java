package project.model.processor.behavior.signals;

import project.logic.HexHandler;
import project.model.processor.ALU;
import project.model.processor.Accumulator;
import project.model.processor.InternalBus;

public final class ADD extends BaseSignal {

    Integer noOfCycles = 2;

    private static final ADD INC = new ADD();

    public static ADD getInstance() {
        return INC;
    }

    //Add the 'Q' (which is a line from intBus)
    // with 'P' (which is a line from the accumulator).
    @Override
    public void signal() {
        String intBus = InternalBus.getInstance().getValue();
        String accumulator = Accumulator.getInstance().getValue();

        String result = HexHandler.addNumbers(intBus, accumulator);

        ALU.getInstance().setValue(result);

        super.printData();
    }
}
