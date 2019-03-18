package project.instructions;


import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.behavior.signals.SHR;

@Data
public class ShrA implements BaseInstruction {

    Integer noOfCycles = 8;

    private static final ShrA SHR_A = new ShrA();

    public static ShrA getInstance() {
        return SHR_A;
    }

    @Override
    public void execute() {

        drawActiveElements();

        //1. A <- shr(A)
        if (CycleHandler.getInstance().getCurrentCycle() == 8) SHR.getInstance().signal();

        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 9) CycleHandler.getInstance().setCurrentCycle(8);
    }


    private void drawActiveElements() {
        //1. MAR <- MDR[23:0}
        if (CycleHandler.getInstance().getCurrentCycle() == 8) {
            //EMDR
            Middle.fillTheGrid(Middle.middleGroup, "acc", "noIntBus");
        }
    }
}
