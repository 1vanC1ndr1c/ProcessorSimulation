package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
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
        //1. A <- not A
        if (CycleHandler.getInstance().getCurrentCycle() == 8) COM.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 8) LALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9) EALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 10) LA.getInstance().signal();

        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 11) CycleHandler.getInstance().setCurrentCycle(10);
    }
}
