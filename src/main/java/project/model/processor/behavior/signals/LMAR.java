package project.model.processor.behavior.signals;

import project.model.processor.InternalBus;
import project.model.processor.MemoryAddressRegister;

public class LMAR extends BaseSignal {

    Integer noOfCycles = 1;

    private static final LMAR LMAR = new LMAR();

    public static LMAR getInstance() {
        return LMAR;
    }

    @Override
    public void signal() {
        MemoryAddressRegister.getInstance().setValue(InternalBus.getInstance().getValue());

        super.printData();
    }
}
