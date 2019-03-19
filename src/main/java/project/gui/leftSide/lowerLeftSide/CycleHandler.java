package project.gui.leftSide.lowerLeftSide;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class CycleHandler {

    private List<String> activeElements = new ArrayList<>();

    private Integer currentCycle = 0;

    private static CycleHandler ourInstance = new CycleHandler();

    public static CycleHandler getInstance() {
        return ourInstance;
    }

    public void setActiveElementsArray(String... activeElements) {
        this.activeElements.clear();
        this.activeElements.addAll(Arrays.asList());
    }
}
