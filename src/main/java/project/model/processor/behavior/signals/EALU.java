package project.model.processor.behavior.signals;

import project.model.processor.InternalBus;
import project.model.processor.TemporaryRegister;

public final class EALU extends BaseSignal {

    private static final EALU INC = new EALU();

    public static EALU getInstance() {
        return INC;
    }

    @Override
    public void signal() {
        InternalBus.getInstance().setValue(TemporaryRegister.getInstance().getValue());

        super.printData();
    }
}
