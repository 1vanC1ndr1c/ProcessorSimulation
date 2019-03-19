package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.ConditionChecker;
import project.model.processor.behavior.signals.EMDR;
import project.model.processor.behavior.signals.LPC;

@Data
public class JmpZ implements BaseInstruction {

    Integer noOfCycles = 9;

    private static final JmpZ JMP_Z = new JmpZ();

    public static JmpZ getInstance() {
        return JMP_Z;
    }

    @Override
    public void execute() {

        drawActiveElements();

        //1. PC <- MDR [23:0}, if A = 0
        if (ConditionChecker.getInstance().checkAccumulator()) {
            //if A = 0
            if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().sendSubstring("data");
            if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().signal();
            if (CycleHandler.getInstance().getCurrentCycle() == 9) LPC.getInstance().signal();
            //end of instruction, if statement is true
            if (CycleHandler.getInstance().getCurrentCycle() == 11) CycleHandler.getInstance().setCurrentCycle(10);
        } else {
            if (CycleHandler.getInstance().getCurrentCycle() == 9)
                CycleHandler.getInstance().setCurrentCycle(8);
            System.out.println("            *** Jump not executed. Acc != 0 ***");
        }


    }


    private void drawActiveElements() {
        if (CycleHandler.getInstance().getCurrentCycle() == 8) {
            //EMDR
            if (ConditionChecker.getInstance().checkAccumulator()) {
                Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "cond");
            } else {
                Middle.fillTheGrid(Middle.middleGroup, "cond");
            }
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9) {
            //LPC
            if (ConditionChecker.getInstance().checkAccumulator())
                Middle.fillTheGrid(Middle.middleGroup, "pc", "intbus", "mdr");
        }
        if (!ConditionChecker.getInstance().checkAccumulator()) {
            if (CycleHandler.getInstance().getCurrentCycle() == 8) {
                //instruction complete, no active elements
                Middle.fillTheGrid(Middle.middleGroup);
            }
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 10) {
            //instruction complete, no active elements
            Middle.fillTheGrid(Middle.middleGroup);
        }
    }
}
