package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.gui.rightSide.LowerRightSide.LowerRightSide;
import project.logic.CycleHandler;
import project.gui.middle.Middle;
import project.model.processor.behavior.signals.*;

import java.util.ArrayList;
import java.util.List;

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
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().sendSubstring("data");
        if (CycleHandler.getInstance().getCurrentCycle() == 8 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 9 + CycleHandler.getInstance().getInstructionStartCycle())
            LMAR.getInstance().signal();
        //2. MDR <- A
        if (CycleHandler.getInstance().getCurrentCycle() == 10 + CycleHandler.getInstance().getInstructionStartCycle())
            EA.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 11 + CycleHandler.getInstance().getInstructionStartCycle())
            LMDR.getInstance().setSource("bus");
        if (CycleHandler.getInstance().getCurrentCycle() == 11 + CycleHandler.getInstance().getInstructionStartCycle())
            LMDR.getInstance().signal();
        //3. M{MAR} <- MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle())
            WRITE.getInstance().signal();

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
        //2. MDR <- A
        if (CycleHandler.getInstance().getCurrentCycle() == 10 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //EA
            Middle.fillTheGrid(Middle.middleGroup, "acc", "intbus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 11 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //LMDR
            Middle.fillTheGrid(Middle.middleGroup, "acc", "intbus", "mdr");
        }
        //3. M[MAR] <- MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //write
            Middle.fillTheGrid(Middle.middleGroup, "memory", "mdr", "mar");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //write
            Middle.fillTheGrid(Middle.middleGroup, "memory", "mdr", "mar");
            LowerRightSide.set();
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
            activeOperations.add("2. MDR <- A");
            activeOperations.add("ea");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 11 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("2. MDR <- A");
            activeOperations.add("ea");
            activeOperations.add("lmdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("3. M[MAR] <- MDR");
            activeOperations.add("write");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("3. M[MAR] <- MDR");
            activeOperations.add("write");
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
