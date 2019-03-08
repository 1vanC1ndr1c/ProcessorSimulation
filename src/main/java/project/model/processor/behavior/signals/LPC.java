package project.model.processor.behavior.signals;

import project.model.processor.InternalBus;
import project.model.processor.ProgramCounter;

public final class LPC extends BaseSignal {

    private static final LPC LPC = new LPC();

    public static LPC getInstance() {
        return LPC;
    }

    @Override
    public void signal() {
        ProgramCounter.getInstance().setValue(InternalBus.getInstance().getValue());

        super.printData();
    }
}
