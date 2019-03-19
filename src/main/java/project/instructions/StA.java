package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.gui.middle.Middle;
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

        drawActiveElements();

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
        if (CycleHandler.getInstance().getCurrentCycle() == 14) CycleHandler.getInstance().setCurrentCycle(13);
    }


    private void drawActiveElements() {
        //1. MAR <- MDR[23:0}
        if (CycleHandler.getInstance().getCurrentCycle() == 8) {
            //EMDR
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9) {
            //LMAR
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "mar");
        }
        //2. MDR <- A
        if (CycleHandler.getInstance().getCurrentCycle() == 10) {
            //EA
            Middle.fillTheGrid(Middle.middleGroup, "acc", "intbus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 11) {
            //LMDR
            Middle.fillTheGrid(Middle.middleGroup, "acc", "intbus", "mdr");
        }
        //3. M[MAR] <- MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12) {
            //write
            Middle.fillTheGrid(Middle.middleGroup, "memory");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13) {
            //write
            Middle.fillTheGrid(Middle.middleGroup, "memory");
        }
    }
}
