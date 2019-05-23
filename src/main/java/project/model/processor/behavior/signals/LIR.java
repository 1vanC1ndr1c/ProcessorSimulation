package project.model.processor.behavior.signals;

import project.model.processor.InstructionRegister;
import project.model.processor.InternalBus;

public final class LIR extends BaseSignal {

    private static final LIR LIR = new LIR();

    public static LIR getInstance() {
        return LIR;
    }

    @Override
    public void signal() {
        InstructionRegister.getInstance().setValue(InternalBus.getInstance().getValue());

        super.printData();
    }
}
