package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.model.processor.behavior.signals.*;

@Data
public class StA implements BaseInstruction {

    Integer noOfCycles = 13;

    private static final StA ST_A = new StA();

    public static StA getInstance() {
        return ST_A;
    }


    @Override
    public void execute() {
        //1. MAR <- MDR[23:0}
        if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().sendSubstring("data");
        if (CycleHandler.getInstance().getCurrentCycle() == 8) EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9) LMAR.getInstance().signal();

        //2. MDR <- A
        if (CycleHandler.getInstance().getCurrentCycle() == 10) EA.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 11) LMDR.getInstance().setSource("bus");
        if (CycleHandler.getInstance().getCurrentCycle() == 11) LMDR.getInstance().signal();

        //3. M{MAR} <- MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12) WRITE.getInstance().signal();

        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 13) CycleHandler.getInstance().setCurrentCycle(12);
    }
}
