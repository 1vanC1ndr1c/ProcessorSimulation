package project.model.processor.behavior;

import lombok.Data;
import project.logic.CycleHandler;

@Data
public final class Execute {

    private static final Execute EXECUTE = new Execute();

    public static Execute getInstance() {
        return EXECUTE;
    }

    public static void execute() {
        //fetch phase lasts 7 cycles
        if (CycleHandler.getInstance().getCurrentCycle() >= 8) {
            Fetch.getInstance().getDecodedInstruction().execute();
        }
    }
}
