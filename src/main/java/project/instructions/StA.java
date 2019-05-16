package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
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
        if (CycleHandler.getInstance().getCurrentCycle() == 15) CycleHandler.getInstance().setCurrentCycle(14);

        drawActiveElements();
        activeOperationsExecutePhase();
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
            Middle.fillTheGrid(Middle.middleGroup, "memory", "mdr", "mar");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13) {
            //write
            Middle.fillTheGrid(Middle.middleGroup, "memory", "mdr", "mar");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 14) {
            //write
            Middle.fillTheGrid(Middle.middleGroup);
        }
    }

    @SuppressWarnings("Duplicates")
    public static void activeOperationsExecutePhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 8) {
            activeOperations.add("1. MAR <- MDR[23:0}");
            activeOperations.add("emdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 9) {
            activeOperations.add("1. MAR <- MDR[23:0}");
            activeOperations.add("emdr");
            activeOperations.add("lmar");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 10) {
            activeOperations.add("2. MDR <- A");
            activeOperations.add("ea");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 11) {
            activeOperations.add("2. MDR <- A");
            activeOperations.add("ea");
            activeOperations.add("lmdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 12) {
            activeOperations.add("3. M[MAR] <- MDR");
            activeOperations.add("write");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13) {
            activeOperations.add("3. M[MAR] <- MDR");
            activeOperations.add("write");
        }
        if (CycleHandler.getInstance().getCurrentCycle() < 14) {
            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ','
            activeOperationsString = activeOperationsString.substring(0, activeOperationsString.length() - 2);

            LowerLeftSide.operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
        }
    }
}
