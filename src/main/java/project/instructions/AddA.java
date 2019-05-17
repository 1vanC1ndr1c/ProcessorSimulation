package project.instructions;

import lombok.Data;
import project.gui.leftSide.lowerLeftSide.LowerLeftSide;
import project.logic.CycleHandler;
import project.gui.middle.Middle;
import project.model.memory.Memory;
import project.model.processor.ProgramCounter;
import project.model.processor.behavior.signals.*;

import java.util.ArrayList;
import java.util.List;

@Data
public final class AddA implements BaseInstruction {

    Integer noOfCycles = 15;

    private static final AddA ADD_A = new AddA();

    public static AddA getInstance() {
        return ADD_A;
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

        //3. A <- A + MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().sendSubstring("all");
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle())
            ADD.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 13 + CycleHandler.getInstance().getInstructionStartCycle())
            LALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 14 + CycleHandler.getInstance().getInstructionStartCycle())
            EALU.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 15 + CycleHandler.getInstance().getInstructionStartCycle())
            LA.getInstance().signal();

//        //end of instruction
//        if (CycleHandler.getInstance().getCurrentCycle() == 17 + CycleHandler.getInstance().getInstructionStartCycle())
//            CycleHandler.getInstance().setCurrentCycle(16 + CycleHandler.getInstance().getInstructionStartCycle());

        drawActiveElements();
        activeOperationsExecutePhase();
    }

    @SuppressWarnings("Duplicates")
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
        //3. A <- A + MDR
        if (CycleHandler.getInstance().getCurrentCycle() == 12 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //EMDR, ADD
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "alu", "acc", "noIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //EMDR, ADD, LALU
            Middle.fillTheGrid(Middle.middleGroup, "mdr", "intbus", "alu", "tr", "aluTrConnection", "acc", "noIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 14 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //EALU
            Middle.fillTheGrid(Middle.middleGroup, "intbus", "tr", "trToIntBus");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 15 + CycleHandler.getInstance().getInstructionStartCycle()) {
            //LA
            Middle.fillTheGrid(Middle.middleGroup, "acc", "intbus", "tr", "trToIntBus");
        }
//        //this doesn't do anything right now
//        if (CycleHandler.getInstance().getCurrentCycle() == 16 + CycleHandler.getInstance().getInstructionStartCycle()) {
//            //instruction complete, no active elements
//            Middle.fillTheGrid(Middle.middleGroup);
//        }
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
            activeOperations.add("3. A <- A + MDR");
            activeOperations.add("emdr");
            activeOperations.add("add");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 13 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("3. A <- A + MDR");
            activeOperations.add("emdr");
            activeOperations.add("add");
            activeOperations.add("lalu");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 14 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("3. A <- A + MDR");
            activeOperations.add("ealu");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 15 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("3. A <- A + MDR");
            activeOperations.add("ealu");
            activeOperations.add("la");
        }
        if (CycleHandler.getInstance().getCurrentCycle() < 16 + CycleHandler.getInstance().getInstructionStartCycle()) {
            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ','
            activeOperationsString = activeOperationsString.substring(0, activeOperationsString.length() - 2);

            LowerLeftSide.operationsMap.put(CycleHandler.getInstance().getCurrentCycle()
                    + CycleHandler.getInstance().getInstructionStartCycle(), activeOperationsString);
        }
    }
}
