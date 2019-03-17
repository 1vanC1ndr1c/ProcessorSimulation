package project.gui.leftSide.lowerLeftSide;

import lombok.Data;

@Data
public class CycleHandler {

    private Integer currentCycle = 0;

    private static CycleHandler ourInstance = new CycleHandler();

    public static CycleHandler getInstance() {
        return ourInstance;
    }
}
