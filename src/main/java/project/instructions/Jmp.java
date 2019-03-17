package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.model.processor.behavior.signals.EMDR;
import project.model.processor.behavior.signals.LPC;

@Data
public final class Jmp implements BaseInstruction {

    Integer noOfCycles = 8;

    private static final Jmp JMP = new Jmp();

    public static Jmp getInstance() {
        return JMP;
    }

    @Override
    public void execute() {
        //1. PC <- MDR [23:0}, if A = 0
        if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().sendSubstring("data");
        if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9) LPC.getInstance().signal();

        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 10) CycleHandler.getInstance().setCurrentCycle(9);
    }
}
