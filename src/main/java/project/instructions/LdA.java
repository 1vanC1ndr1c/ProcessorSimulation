package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.model.processor.behavior.signals.*;

@Data
public class LdA implements BaseInstruction {

    Integer noOfCycles = 12;

    private static final LdA LD_A = new LdA();

    public static LdA getInstance() {
        return LD_A;
    }

    @Override
    public void execute() {
        //1. MAR <- MDR[23:0}
        if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().sendSubstring("data");
        if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9) LMAR.getInstance().signal();

        //2. MDR <- M[MAR], read
        if (CycleHandler.getInstance().getCurrentCycle() == 10) READ.getInstance().signal();
        //LMDR can be MDR <- IntBus or in this case MDR <- Data
        if (CycleHandler.getInstance().getCurrentCycle() == 11) LMDR.getInstance().setSource("data");
        if (CycleHandler.getInstance().getCurrentCycle() == 11) LMDR.getInstance().signal();

        //3. A <- MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12) EMDR.getInstance().sendSubstring("all");
        if (CycleHandler.getInstance().getCurrentCycle() == 12) EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 13) LA.getInstance().signal();

        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 14) CycleHandler.getInstance().setCurrentCycle(13);
    }
}
