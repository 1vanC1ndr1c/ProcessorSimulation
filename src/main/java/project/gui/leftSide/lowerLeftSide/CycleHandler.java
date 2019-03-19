package project.gui.leftSide.lowerLeftSide;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class CycleHandler {
    private int currentCycle = 0;

    private static CycleHandler ourInstance = new CycleHandler();

    public static CycleHandler getInstance() {
        return ourInstance;
    }
}
