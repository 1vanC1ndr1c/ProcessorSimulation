package project.model.processor.behavior;

import lombok.Data;
import project.instructions.*;
import project.model.processor.*;
import project.model.processor.behavior.signals.*;
import project.output.OutputHandler;

@Data
public final class Fetch<T extends BaseInstruction> {

    private T decodedInstruction;

    private static final Fetch FETCH = new Fetch();

    public static Fetch getInstance() {
        return FETCH;
    }

    public void fetch() {
        OutputHandler.processorOut("Before first fetch", 0);
        System.out.println("FETCH PHASE:========================================");
        //1. MAR <- PC
        EPC.getInstance().signal();
        LMAR.getInstance().signal();

        //2. MDR <- M[MAR], read
        READ.getInstance().signal();
        LMDR.getInstance().setSource("data");                   //LMDR can be MDR <- IntBus or in this case MDR <- Data
        LMDR.getInstance().signal();

        //3. PC++, IR <- MDR{31:28}
        INC.getInstance().signal();
        EMDR.getInstance().sendSubstring("opcode");
        EMDR.getInstance().signal();
        LIR.getInstance().signal();

        //4. decode
        decodeInstruction(InstructionRegister.getInstance().getValue());
        System.out.println("END FETCH ==========================================");
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
