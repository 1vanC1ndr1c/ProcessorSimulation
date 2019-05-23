package project.model.processor.behavior.signals;

import lombok.Data;
import project.model.memory.Memory;
import project.model.processor.MemoryAddressRegister;

@Data
public class READ extends BaseSignal {

    public String MemoryContentFromMAR;

    private static final READ READ = new READ();

    public static READ getInstance() {
        return READ;
    }

    @Override
    public void signal() {
        String MARValue = MemoryAddressRegister.getInstance().getValue();
        MemoryContentFromMAR = Memory.getInstance().getLocationAndContent().get(MARValue);

        super.printData();
    }
}
