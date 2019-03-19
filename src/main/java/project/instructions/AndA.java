package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.behavior.signals.*;

@Data
public final class AndA implements BaseInstruction {

    Integer noOfCycles = 0;

    private static final AndA AND_A = new AndA();

    public static AndA getInstance() {
        return AND_A;
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
        //3. A <- A & MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12) EMDR.getInstance().sendSubstring("all");
        if (CycleHandler.getInstance().getCurrentCycle() == 12) EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 12) AND.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 13) LALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 14) EALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 15) LA.getInstance().signal();
        //end of instruction
        if (CycleHandler.getInstance().getCurrentCycle() == 17) CycleHandler.getInstance().setCurrentCycle(16);

        drawActiveElements();
        //check:
        //https://www.rapidtables.com/calc/math/binary-calculator.html
    }


    @SuppressWarnings("Duplicates")
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
        //2. MDR <- M[MAR], read
        if (CycleHandler.getInstance().getCurrentCycle() == 10) {
            //read
            Middle.fillTheGrid(Middle.middleGroup, "memory");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 11) {
            //LMDR
            Middle.fillTheGrid(Middle.middleGroup, "memory", "mdr");
        }

        //3. A <- A & MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12) {
            //EMDR, AND
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "alu", "acc", "noIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13) {
            //EMDR, ADD, LALU
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "alu", "tr", "aluTrConnection", "acc", "noIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 14) {
            //EALU
            Middle.fillTheGrid(Middle.middleGroup, "intbus", "tr", "trToIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 15) {
            //LA
            Middle.fillTheGrid(Middle.middleGroup, "acc", "intbus", "tr", "trToIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 16) {
            //instruction complete, no active elements
            Middle.fillTheGrid(Middle.middleGroup);
        }

    }
}
