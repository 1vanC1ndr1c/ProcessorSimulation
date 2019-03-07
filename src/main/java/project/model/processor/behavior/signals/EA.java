package project.model.processor.behavior.signals;

import project.model.processor.Accumulator;
import project.model.processor.InternalBus;

public final class EA extends BaseSignal {

    private static final EA EPC = new EA();

    public static EA getInstance() {
        return EPC;
    }

    @Override
    public void signal() {
        InternalBus.getInstance().setValue(Accumulator.getInstance().getValue());

        super.printData();
    }
}
