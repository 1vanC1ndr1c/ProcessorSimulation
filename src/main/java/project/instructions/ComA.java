package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.behavior.signals.COM;
import project.model.processor.behavior.signals.EALU;
import project.model.processor.behavior.signals.LA;
import project.model.processor.behavior.signals.LALU;

@Data
public class ComA implements BaseInstruction {

    Integer noOfCycles = 10;

    private static final ComA COM_A = new ComA();

    public static ComA getInstance() {
        return COM_A;
    }

    @Override
    public void execute() {

        drawActiveElements();

        //1. A <- not A
        if (CycleHandler.getInstance().getCurrentCycle() == 8) COM.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 8) LALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9) EALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 10) LA.getInstance().signal();

        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 12) CycleHandler.getInstance().setCurrentCycle(11);
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
}
