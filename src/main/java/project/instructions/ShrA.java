package project.instructions;


import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.logic.CycleHandler;
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
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
            SHR.getInstance().signal();

        drawActiveElements();
        activeOperationsExecutePhase();
    }

    private void drawActiveElements() {
        //1. A <- shr(A)
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //SHR
            Middle.fillTheGrid(Middle.middleGroup, "acc", "noIntBus");
        }
    }

    @SuppressWarnings("Duplicates")
    public static void activeOperationsExecutePhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. A <- shr(A)}");
            activeOperations.add("shr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() < 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ', '
            activeOperationsString = activeOperationsString.replaceAll(", $", "");

            LowerLeftSide.operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
        }
    }
}
