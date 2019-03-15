package project.model.processor.behavior.signals;

import project.model.processor.InternalBus;
import project.model.processor.ProgramCounter;

public final class EPC extends BaseSignal {

    Integer noOfCycles = 2;

    private static final EPC EPC = new EPC();

    public static EPC getInstance() {
        return EPC;
    }

    @Override
    public void signal() {
        InternalBus.getInstance().setValue(ProgramCounter.getInstance().getValue());

        super.printData();
    }
}
