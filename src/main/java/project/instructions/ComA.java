package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.logic.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.behavior.signals.COM;
import project.model.processor.behavior.signals.EALU;
import project.model.processor.behavior.signals.LA;
import project.model.processor.behavior.signals.LALU;

import java.util.ArrayList;
import java.util.List;

@Data
public class ComA implements BaseInstruction {

    Integer noOfCycles = 10;

    private static final ComA COM_A = new ComA();

    public static ComA getInstance() {
        return COM_A;
    }

    @Override
    public void execute() {
        //1. A <- not A
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
            COM.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
            LALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle())
            EALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 10 + CycleHandler.getInstance().getInstructionStartCycle())
            LA.getInstance().signal();

        drawActiveElements();
        activeOperationsExecutePhase();
    }

    private void drawActiveElements() {
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //COM, LALU
            Middle.fillTheGrid(Middle.middleGroup, "alu", "tr", "aluTrConnection");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //EALU
            Middle.fillTheGrid(Middle.middleGroup, "tr", "intbus", "trToIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 10 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //LMDR
            Middle.fillTheGrid(Middle.middleGroup, "tr", "intbus", "trToIntBus", "acc");
        }
    }

    @SuppressWarnings("Duplicates")
    public static void activeOperationsExecutePhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. A <- Not A");
            activeOperations.add("com");
            activeOperations.add("lalu");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. A <- Not A");
            activeOperations.add("ealu");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 10 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. A <- Not A");
            activeOperations.add("ealu");
            activeOperations.add("la");
        }
        if (CycleHandler.getInstance().getCurrentCycle() < 11 + CycleHandler.getInstance().getInstructionStartCycle()) {
            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ', '
            activeOperationsString = activeOperationsString.replaceAll(", $", "");

            LowerLeftSide.operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
        }
    }
}
