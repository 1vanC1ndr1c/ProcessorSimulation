package project.model.processor.behavior;


import lombok.Data;
import project.gui.leftSide.lowerLeftSide.CycleHandler;
import project.gui.middle.ComponentBuilder;
import project.instructions.*;
import project.model.processor.*;
import project.model.processor.behavior.signals.*;
import project.output.OutputHandler;

@Data
public final class Fetch<T extends BaseInstruction> extends Thread {

    private T decodedInstruction;

    private static final Fetch FETCH = new Fetch();

    public static Fetch getInstance() {
        return FETCH;
    }


    public void fetch() {

        if (CycleHandler.getInstance().getCurrentCycle() == 0) OutputHandler.processorOut("Before first fetch", 0);
        if (CycleHandler.getInstance().getCurrentCycle() == 1)
            System.out.println("FETCH PHASE:========================================");

        //1. MAR <- PC
        if (CycleHandler.getInstance().getCurrentCycle() == 1) EPC.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 2) LMAR.getInstance().signal();

        //2. MDR <- M[MAR], read
        if (CycleHandler.getInstance().getCurrentCycle() == 3) READ.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 4)
            LMDR.getInstance().setSource("data");                   //LMDR can be MDR <- IntBus or in this case MDR <- Data
        if (CycleHandler.getInstance().getCurrentCycle() == 4) LMDR.getInstance().signal();

        //3. PC++, IR <- MDR{31:28}
        if (CycleHandler.getInstance().getCurrentCycle() == 5) INC.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 5) EMDR.getInstance().sendSubstring("opcode");
        if (CycleHandler.getInstance().getCurrentCycle() == 5) EMDR.getInstance().signal();
        if (CycleHandler.getInstance().getCurrentCycle() == 6) LIR.getInstance().signal();

        //4. decode
        if (CycleHandler.getInstance().getCurrentCycle() == 7)
            decodeInstruction(InstructionRegister.getInstance().getValue());
        if (CycleHandler.getInstance().getCurrentCycle() == 7)
            System.out.println("END FETCH ==========================================");

        ComponentBuilder.redrawActiveElementsFetchPhase();
    }


    private void decodeInstruction(String opCode) {
        //if the data in the memory is M1M2M3M4M5M6M7
        //then the opcode is the M1, M2 is irrelevant, and the rest
        //is data of the instruction
        System.out.println("            ***Instruction decoding***");
        System.out.println("            ========================================");
        switch (opCode) {
            case "1": //adda.opcode = HEX(1)
                decodedInstruction = (T) AddA.getInstance();
                break;
            case "2": //lda.opcode = HEX(2)
                decodedInstruction = (T) LdA.getInstance();
                break;
            case "3": //anda.opcode = HEX(3)
                decodedInstruction = (T) AndA.getInstance();
                break;
            case "4": //anda.opcode = HEX(4)
                decodedInstruction = (T) StA.getInstance();
                break;
            case "5": //shra.opcode = HEX(5)
                decodedInstruction = (T) ShrA.getInstance();
                break;
            case "6": //jmpz.opcode = HEX(6)
                decodedInstruction = (T) JmpZ.getInstance();
                break;
            case "7": //jmp.opcode = HEX(7)
                decodedInstruction = (T) Jmp.getInstance();
                break;
            case "8": //coma.opcode = HEX(8)
                decodedInstruction = (T) ComA.getInstance();
                break;
            default:
                System.out.println("            ***Cannot Decode!***");
                System.out.println("            ========================================");
                break;
        }
    }
}
