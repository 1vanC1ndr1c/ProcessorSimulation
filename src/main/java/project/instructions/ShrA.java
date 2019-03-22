package project.instructions;


import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.gui.leftSide.middleLeftSide.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.behavior.signals.SHR;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShrA implements BaseInstruction {

    Integer noOfCycles = 8;

    private static final ShrA SHR_A = new ShrA();

    public static ShrA getInstance() {
        return SHR_A;
    }

    @Override
    public void execute() {
        //1. A <- shr(A)
        if (CycleHandler.getInstance().getCurrentCycle() == 8) SHR.getInstance().signal();
        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 10) CycleHandler.getInstance().setCurrentCycle(9);

        drawActiveElements();
        activeOperationsExecutePhase();
    }

    private void drawActiveElements() {
        //1. A <- shr(A)
        if (CycleHandler.getInstance().getCurrentCycle() == 8) {
            //SHR
            Middle.fillTheGrid(Middle.middleGroup, "acc", "noIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9) {
            //instruction complete, no active elements
            Middle.fillTheGrid(Middle.middleGroup);
        }
    }

    @SuppressWarnings("Duplicates")
    public static void activeOperationsExecutePhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 8) {
            activeOperations.add("1. A <- shr(A)}");
            activeOperations.add("shr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() < 9) {
            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ','
            activeOperationsString = activeOperationsString.substring(0, activeOperationsString.length() - 2);

            LowerLeftSide.operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
        }
    }
}
