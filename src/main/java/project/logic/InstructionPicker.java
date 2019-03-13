package project.logic;

import project.model.processor.Accumulator;
import project.model.processor.ProgramCounter;

public class InstructionPicker {

    public static void pick(String instruction) {

        switch (instruction) {
            case "adda": //adda.opcode = HEX(1)
                Accumulator.getInstance().setValue("00000001");//ACC = 1
                ProgramCounter.getInstance().setValue("000001");//adda
                break;
            case "lda": //lda.opcode = HEX(2)
                ProgramCounter.getInstance().setValue("000005");//lda
                break;
            case "anda": //anda.opcode = HEX(3)
                Accumulator.getInstance().setValue("55555555");
                ProgramCounter.getInstance().setValue("000009");//anda
                break;
            case "sta": //sta.opcode = HEX(4)
                Accumulator.getInstance().setValue("22222222");
                ProgramCounter.getInstance().setValue("00000d");//sta
                break;
            case "shra": //shra.opcode = HEX(5)
                Accumulator.getInstance().setValue("00132132");
                ProgramCounter.getInstance().setValue("000012");//shra
                break;
            case "jmpz": //jmpz.opcode = HEX(6)
                ProgramCounter.getInstance().setValue("000014");//jmpz
                break;
            case "jmp": //jmp.opcode = HEX(7)
                ProgramCounter.getInstance().setValue("000018");//jmp
                break;
            case "coma": //com.opcode = HEX(8)
                ProgramCounter.getInstance().setValue("00001c");//coma
                break;
            default:
                ProgramCounter.getInstance().setValue("000100");//adda
        }
    }
}
