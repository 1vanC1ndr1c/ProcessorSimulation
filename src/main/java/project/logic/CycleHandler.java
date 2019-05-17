package project.logic;

import lombok.Data;

@Data
public class CycleHandler {
    private int currentCycle = 0;

    private int instructionStartCycle = 0;

    private static CycleHandler CYCLE_HANDLER = new CycleHandler();

    public static CycleHandler getInstance() {
        return CYCLE_HANDLER;
    }
}
