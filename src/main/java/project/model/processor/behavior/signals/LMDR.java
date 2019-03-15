package project.model.processor.behavior.signals;

import project.model.processor.InternalBus;
import project.model.processor.MemoryDataRegister;

public class LMDR extends BaseSignal {

    Integer noOfCycles = 1;

    String source = "bus";

    private static final LMDR LMDR = new LMDR();

    public static LMDR getInstance() {
        return LMDR;
    }

    @Override
    public void signal() {
        if (source.equals("data")) {
            MemoryDataRegister.getInstance().setValue(READ.getInstance().getMemoryContentFromMAR());
        } else {
            MemoryDataRegister.getInstance().setValue(InternalBus.getInstance().getValue());
        }
        source = "bus";      //fail safe

        super.printData();
    }

    public void setSource(String source) {
        if (source.equals("data") || source.equals("bus")) {
            this.source = source;
        } else {
            throw new IllegalArgumentException("Source can only be 'data' or 'bus'");
        }
    }
}
