package project.model.processor.behavior.signals;

import project.logic.HexHandler;
import project.model.processor.ALU;
import project.model.processor.Accumulator;

public final class COM extends BaseSignal {

    Integer noOfCycles = 1;

    private static final COM COM = new COM();

    public static COM getInstance() {
        return COM;
    }

    @Override
    public void signal() {
        String accumulator = Accumulator.getInstance().getValue();

        String result = HexHandler.complement(accumulator);

        ALU.getInstance().setValue(result);

        super.printData();

        //check:
        //https://www.rapidtables.com/calc/math/binary-calculator.html
    }
}
