package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.gui.leftSide.middleLeftSide.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.behavior.signals.EMDR;
import project.model.processor.behavior.signals.LPC;

import java.util.ArrayList;
import java.util.List;

@Data
public final class Jmp implements BaseInstruction {

    Integer noOfCycles = 8;

    private static final Jmp JMP = new Jmp();

    public static Jmp getInstance() {
        return JMP;
    }

    @Override
    public void execute() {
        //1. PC <- MDR [23:0}
        if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().sendSubstring("data");
        if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9) LPC.getInstance().signal();
        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 11) CycleHandler.getInstance().setCurrentCycle(10);

        drawActiveElements();
        activeOperationsExecutePhase();
    }

    private void drawActiveElements() {
        if (CycleHandler.getInstance().getCurrentCycle() == 8) {
            //EMDR
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9) {
            //LPC
            Middle.fillTheGrid(Middle.middleGroup, "pc", "intbus", "mdr");
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
            activeOperations.add("1. PC <- MDR [23:0]");
            activeOperations.add("emdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9) {
            activeOperations.add("1. PC <- MDR [23:0]");
            activeOperations.add("emdr");
            activeOperations.add("lpc");
        }
        if (CycleHandler.getInstance().getCurrentCycle() < 10) {
            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ','
            activeOperationsString = activeOperationsString.substring(0, activeOperationsString.length() - 2);

            LowerLeftSide.operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
        }
    }
}
