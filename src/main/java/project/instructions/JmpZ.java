package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
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

        //1. PC <- MDR [23:0}, if A = 0
        if (ConditionChecker.getInstance().checkAccumulator()) {
            //if A = 0
            if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().sendSubstring("data");
            if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().signal();
            if (CycleHandler.getInstance().getCurrentCycle() == 9) LPC.getInstance().signal();
        } else {
            if (CycleHandler.getInstance().getCurrentCycle() == 8)
                System.out.println("            *** Jump not executed. Acc != 0 ***");
        }

        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 10) CycleHandler.getInstance().setCurrentCycle(9);
    }
}
