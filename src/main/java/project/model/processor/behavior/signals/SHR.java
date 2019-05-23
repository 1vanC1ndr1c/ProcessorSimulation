package project.model.processor.behavior.signals;

import project.logic.HexHandler;
import project.model.processor.Accumulator;

public final class SHR extends BaseSignal {

    private static final SHR INC = new SHR();

    public static SHR getInstance() {
        return INC;
    }

    @Override
    public void signal() {
        String accumulator = Accumulator.getInstance().getValue();

        String result = HexHandler.shift(accumulator);

        Accumulator.getInstance().setValue(result);

        super.printData();

        //check:
        //https://www.rapidtables.com/calc/math/binary-calculator.html
    }
}
