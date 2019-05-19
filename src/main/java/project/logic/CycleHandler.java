package project.logic;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CycleHandler {
    private int currentCycle = 0;

    private int instructionStartCycle = 0;

    // map that has cycle and corresponding start of the instruction for that cycle
    //<integer(cycle), integer(instructionstart)>
    public static Map<Integer, Integer> startOfTheInstructions = new HashMap<>();

    private static CycleHandler CYCLE_HANDLER = new CycleHandler();

    public static CycleHandler getInstance() {
        return CYCLE_HANDLER;
    }
}
