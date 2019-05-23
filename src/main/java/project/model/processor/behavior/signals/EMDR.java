package project.model.processor.behavior.signals;

import lombok.Data;
import project.model.processor.InternalBus;
import project.model.processor.MemoryDataRegister;

@Data
public final class EMDR extends BaseSignal {

    private String sendPart = "opCode";

    private static final EMDR EMDR = new EMDR();

    public static EMDR getInstance() {
        return EMDR;
    }

    @Override
    public void signal() {
        if (sendPart.equals("opcode")) {
            String MDRValue = MemoryDataRegister.getInstance().getValue().substring(0, 1);
            InternalBus.getInstance().setValue(MDRValue);
        } else if (sendPart.equals("data")) {
            String MDRDataPart = MemoryDataRegister.getInstance().getValue().substring(2);
            InternalBus.getInstance().setValue(MDRDataPart);
        } else {
            InternalBus.getInstance().setValue(MemoryDataRegister.getInstance().getValue());
        }
        super.printData();
    }

    //Because of the fact that MDR can in one moment contain opcode or the following 24 bits of data
    // or the whole MDR can be just a 32bit data from the memory, there needs to be a specification of what data is
    //being sent to the internal bus so that the receiver doesn't get faulty data
    public void sendSubstring(String part) {
        if (part.equals("data") || part.equals("opcode") || part.equals("all")) {
            sendPart = part;
        } else {
            throw new IllegalArgumentException("MDR can send only the 'opcode', 'data' or 'all'");
        }
    }
}
