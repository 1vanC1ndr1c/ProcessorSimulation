package project.model.processor.behavior.signals;

import project.logic.HexHandler;
import project.model.processor.ProgramCounter;

public final class INC extends BaseSignal {

    Integer noOfCycles = 1;

    private static final INC INC = new INC();

    public static INC getInstance() {
        return INC;
    }

    @Override
    public void signal() {
        String newValue = HexHandler.increaseValueByOne(ProgramCounter.getInstance().getValue());
        ProgramCounter.getInstance().setValue(newValue);

        super.printData();
    }
}
