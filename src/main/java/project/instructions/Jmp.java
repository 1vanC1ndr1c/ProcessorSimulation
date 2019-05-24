package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.logic.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.behavior.signals.EMDR;
import project.model.processor.behavior.signals.LPC;

import java.util.ArrayList;
import java.util.List;

@Data
public final class Jmp implements BaseInstruction {

    Integer noOfCycles = 9;

    private static final Jmp JMP = new Jmp();

    public static Jmp getInstance() {
        return JMP;
    }

    @Override
    public void execute() {
        //1. PC <- MDR [23:0}
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().sendSubstring("data");
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle())
            LPC.getInstance().signal();

        drawActiveElements();
        activeOperationsExecutePhase();
    }

    private void drawActiveElements() {
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //EMDR
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //LPC
            Middle.fillTheGrid(Middle.middleGroup, "pc", "intbus", "mdr");
        }
    }

    @SuppressWarnings("Duplicates")
    public static void activeOperationsExecutePhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. PC <- MDR [23:0]");
            activeOperations.add("emdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. PC <- MDR [23:0]");
            activeOperations.add("emdr");
            activeOperations.add("lpc");
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
