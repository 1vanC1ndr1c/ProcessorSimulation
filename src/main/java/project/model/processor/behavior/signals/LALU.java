package project.model.processor.behavior.signals;

import project.model.processor.ALU;
import project.model.processor.TemporaryRegister;

public final class LALU extends BaseSignal {

    private static final LALU INC = new LALU();

    public static LALU getInstance() {
        return INC;
    }

    @Override
    public void signal() {
        TemporaryRegister.getInstance().setValue(ALU.getInstance().getValue());

        super.printData();
    }
}
