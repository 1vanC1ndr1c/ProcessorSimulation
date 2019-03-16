package project.model.processor.behavior.signals;

import lombok.Data;
import project.model.memory.Memory;
import project.model.processor.MemoryAddressRegister;
import project.model.processor.MemoryDataRegister;

@Data
public class WRITE extends BaseSignal {

    Integer noOfCycles = 2;

    public String MemoryContentFromMAR;

    private static final WRITE WRITE = new WRITE();

    public static WRITE getInstance() {
        return WRITE;
    }

    @Override
    public void signal() {
        String MDRValue = MemoryDataRegister.getInstance().getValue();
        String MARLocation = MemoryAddressRegister.getInstance().getValue();
        Memory.getInstance().getLocationAndContent().put(MARLocation, MDRValue);

        super.printData();
    }
}
