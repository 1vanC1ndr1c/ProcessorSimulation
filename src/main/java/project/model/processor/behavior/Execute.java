package project.model.processor.behavior;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;

@Data
public final class Execute {

    private static final Execute EXECUTE = new Execute();

    public static Execute getInstance() {
        return EXECUTE;
    }

    public static void execute() {
        if (CycleHandler.getInstance().getCurrentCycle() == 8)
            System.out.println("EXECUTE PHASE:======================================");
        if (CycleHandler.getInstance().getCurrentCycle() >= 8) Fetch.getInstance().getDecodedInstruction().execute();
    }
}
