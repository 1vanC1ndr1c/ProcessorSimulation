package project.model.processor.behavior.signals;

import project.model.processor.Accumulator;
import project.model.processor.InternalBus;

public final class LA extends BaseSignal {

    private static final LA INC = new LA();

    public static LA getInstance() {
        return INC;
    }

    @Override
    public void signal() {
        Accumulator.getInstance().setValue(InternalBus.getInstance().getValue());

        super.printData();
    }
}
