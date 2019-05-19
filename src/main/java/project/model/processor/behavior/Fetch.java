package project.model.processor.behavior;

import lombok.Data;
import project.logic.CycleHandler;
import project.gui.middle.ComponentBuilder;
import project.instructions.*;
import project.model.processor.*;
import project.model.processor.behavior.signals.*;

@Data
public final class Fetch<T extends BaseInstruction> extends Thread {

    private T decodedInstruction;
    public static boolean decodedCorrectly = true;

    private static final Fetch FETCH = new Fetch();

    public static Fetch getInstance() {
        return FETCH;
    }

    public void fetch() {
        decodedCorrectly = true;

        //1. MAR <- PC
        if (CycleHandler.getInstance().getCurrentCycle() == 1 + CycleHandler.getInstance().getInstructionStartCycle())
            EPC.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 2 + CycleHandler.getInstance().getInstructionStartCycle())
            LMAR.getInstance().signal();

        //2. MDR <- M[MAR], read
        if (CycleHandler.getInstance().getCurrentCycle() == 3 + CycleHandler.getInstance().getInstructionStartCycle())
            READ.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 4 + CycleHandler.getInstance().getInstructionStartCycle())
            LMDR.getInstance().setSource("data");                   //LMDR can be MDR <- IntBus or in this case MDR <- Data
        if (CycleHandler.getInstance().getCurrentCycle() == 4 + CycleHandler.getInstance().getInstructionStartCycle())
            LMDR.getInstance().signal();

        //3. PC++, IR <- MDR{31:28}
        if (CycleHandler.getInstance().getCurrentCycle() == 5 + CycleHandler.getInstance().getInstructionStartCycle())
            INC.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 5 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().sendSubstring("opcode");
        if (CycleHandler.getInstance().getCurrentCycle() == 5 + CycleHandler.getInstance().getInstructionStartCycle())
            EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 6 + CycleHandler.getInstance().getInstructionStartCycle())
            LIR.getInstance().signal();

        //4. decode
        if (CycleHandler.getInstance().getCurrentCycle() == 7 + CycleHandler.getInstance().getInstructionStartCycle())
            decodeInstruction(InstructionRegister.getInstance().getValue());

        ComponentBuilder.redrawActiveElementsFetchPhase();
    }


    private void decodeInstruction(String opCode) {
        //if the data in the memory is M1M2M3M4M5M6M7
        //then the opcode is the M1, M2 is irrelevant, and the rest
        //is data of the instruction
        switch (opCode) {
            case "1": //adda.opcode = HEX(1)
                decodedInstruction = (T) AddA.getInstance();
                decodedCorrectly = true;
                break;
            case "2": //lda.opcode = HEX(2)
                decodedInstruction = (T) LdA.getInstance();
                decodedCorrectly = true;
                break;
            case "3": //anda.opcode = HEX(3)
                decodedInstruction = (T) AndA.getInstance();
                decodedCorrectly = true;
                break;
            case "4": //anda.opcode = HEX(4)
                decodedInstruction = (T) StA.getInstance();
                decodedCorrectly = true;
                break;
            case "5": //shra.opcode = HEX(5)
                decodedInstruction = (T) ShrA.getInstance();
                decodedCorrectly = true;
                break;
            case "6": //jmpz.opcode = HEX(6)
                decodedInstruction = (T) JmpZ.getInstance();
                decodedCorrectly = true;
                break;
            case "7": //jmp.opcode = HEX(7)
                decodedInstruction = (T) Jmp.getInstance();
                decodedCorrectly = true;
                break;
            case "8": //coma.opcode = HEX(8)
                decodedInstruction = (T) ComA.getInstance();
                decodedCorrectly = true;
                break;
            default:
                decodedCorrectly = false;
                break;
        }
    }
}
