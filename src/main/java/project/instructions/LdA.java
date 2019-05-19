package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.logic.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.behavior.signals.*;

import java.util.ArrayList;
import java.util.List;

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
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().sendSubstring("data");
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle())
            LMAR.getInstance().signal();
        //2. MDR <- M[MAR], read
        if (CycleHandler.getInstance().getCurrentCycle() == 10 + CycleHandler.getInstance().getInstructionStartCycle())
            READ.getInstance().signal();
        //LMDR can be MDR <- IntBus or in this case MDR <- Data
        if (CycleHandler.getInstance().getCurrentCycle() == 11 + CycleHandler.getInstance().getInstructionStartCycle())
            LMDR.getInstance().setSource("data");
        if (CycleHandler.getInstance().getCurrentCycle() == 11 + CycleHandler.getInstance().getInstructionStartCycle())
            LMDR.getInstance().signal();
        //3. A <- MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().sendSubstring("all");
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 13 + CycleHandler.getInstance().getInstructionStartCycle())
            LA.getInstance().signal();

        drawActiveElements();
        activeOperationsExecutePhase();
    }

    private void drawActiveElements() {
        //1. MAR <- MDR[23:0}
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //EMDR
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //LMAR
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "mar");
        }
        //2. MDR <- M[MAR], read
        if (CycleHandler.getInstance().getCurrentCycle() == 10 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //read
            Middle.fillTheGrid(Middle.middleGroup, "memory");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 11 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //LMDR
            Middle.fillTheGrid(Middle.middleGroup, "memory", "mdr");
        }
        //3. A <- MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //EMDR
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //LA
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "acc");
        }
    }

    @SuppressWarnings("Duplicates")
    public static void activeOperationsExecutePhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. MAR <- MDR[23:0}");
            activeOperations.add("emdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. MAR <- MDR[23:0}");
            activeOperations.add("emdr");
            activeOperations.add("lmar");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 10 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("2. MDR <- M[MAR]");
            activeOperations.add("read");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 11 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("2. MDR <- M[MAR]");
            activeOperations.add("read");
            activeOperations.add("lmdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("3. A <- MDR");
            activeOperations.add("emdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("3. A <- MDR");
            activeOperations.add("emdr");
            activeOperations.add("la");
        }
        if (CycleHandler.getInstance().getCurrentCycle() < 14 + CycleHandler.getInstance().getInstructionStartCycle()) {
            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ', '
            activeOperationsString = activeOperationsString.replaceAll(", $", "");

            LowerLeftSide.operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
        }
    }
}
