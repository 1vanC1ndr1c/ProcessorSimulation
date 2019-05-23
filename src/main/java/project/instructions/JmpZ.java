package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.logic.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.ConditionChecker;
import project.model.processor.behavior.signals.EMDR;
import project.model.processor.behavior.signals.LPC;

import java.util.ArrayList;
import java.util.List;

@Data
public class JmpZ implements BaseInstruction {

    Integer noOfCycles = 9;

    private static final JmpZ JMP_Z = new JmpZ();

    public static JmpZ getInstance() {
        return JMP_Z;
    }

    @Override
    public void execute() {
        //1. PC <- MDR [23:0}, if A = 0
        if (ConditionChecker.getInstance().checkAccumulator()) {
            //if A = 0
            if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
                EMDR.getInstance().sendSubstring("data");
            if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
                EMDR.getInstance().signal();
            if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
                LPC.getInstance().signal();
            }
        }
        drawActiveElements();
        activeOperationsExecutePhase();
    }

    private void drawActiveElements() {
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //EMDR
            if (ConditionChecker.getInstance().checkAccumulator()) {
                Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "cond");
            } else {
                Middle.fillTheGrid(Middle.middleGroup, "cond");
            }
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //LPC
            if (ConditionChecker.getInstance().checkAccumulator())
                Middle.fillTheGrid(Middle.middleGroup, "pc", "intbus", "mdr");
        }
    }


    @SuppressWarnings("Duplicates")
    public static void activeOperationsExecutePhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            JmpZ.getInstance().setNoOfCycles(8);
            activeOperations.add("1. PC <- MDR [23:0] (if A = 0)");
            if (ConditionChecker.getInstance().checkAccumulator()) activeOperations.add("emdr");
            else activeOperations.add("A != 0. No jump!");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
            if (ConditionChecker.getInstance().checkAccumulator()) {
                JmpZ.getInstance().setNoOfCycles(9);
                activeOperations.add("1. PC <- MDR [23:0] (if A = 0)");
                activeOperations.add("emdr");
                activeOperations.add("lpc");
            }
        }
        if (CycleHandler.getInstance().getCurrentCycle() < 10 + CycleHandler.getInstance().getInstructionStartCycle()) {
            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ', '
            activeOperationsString = activeOperationsString.replaceAll(", $", "");

            LowerLeftSide.operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
        }
    }


}
