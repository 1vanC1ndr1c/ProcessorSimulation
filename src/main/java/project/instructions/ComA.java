package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.gui.leftSide.middleLeftSide.CycleHandler;
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
        if (CycleHandler.getInstance().getCurrentCycle() == 8) COM.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 8) LALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9) EALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 10) LA.getInstance().signal();
        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 12) CycleHandler.getInstance().setCurrentCycle(11);

        drawActiveElements();
        activeOperationsExecutePhase();
    }

    private void drawActiveElements() {
        if (CycleHandler.getInstance().getCurrentCycle() == 8) {
            //COM, LALU
            Middle.fillTheGrid(Middle.middleGroup, "alu", "tr", "aluTrConnection");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9) {
            //EALU
            Middle.fillTheGrid(Middle.middleGroup, "tr", "intbus", "trToIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 10) {
            //LMDR
            Middle.fillTheGrid(Middle.middleGroup, "tr", "intbus", "trToIntBus", "acc");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 11) {
            //instruction complete, no active elements
            Middle.fillTheGrid(Middle.middleGroup);
        }
    }

    @SuppressWarnings("Duplicates")
    public static void activeOperationsExecutePhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 8) {
            activeOperations.add("1. A <- Not A");
            activeOperations.add("com");
            activeOperations.add("lalu");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9) {
            activeOperations.add("1. A <- Not A");
            activeOperations.add("ealu");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 10) {
            activeOperations.add("1. A <- Not A");
            activeOperations.add("ealu");
            activeOperations.add("la");
        }
        if (CycleHandler.getInstance().getCurrentCycle() < 11) {
            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ','
            activeOperationsString = activeOperationsString.substring(0, activeOperationsString.length() - 2);

            LowerLeftSide.operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
        }
    }
}
