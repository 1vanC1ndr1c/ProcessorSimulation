package project.gui.leftSide.middleLeftSide;

import lombok.Data;

@Data
public class CycleHandler {
    private int currentCycle = 0;

    private static CycleHandler ourInstance = new CycleHandler();

    public static CycleHandler getInstance() {
        return ourInstance;
    }
}
