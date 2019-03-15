package project.logic;

import project.model.processor.Accumulator;
import project.model.processor.ProgramCounter;

public class InstructionPicker {

    public static void pick(String instruction) {

        switch (instruction) {
            case "adda": //adda.opcode = HEX(1)
                Accumulator.getInstance().setValue("00000001");//ACC = 1
                ProgramCounter.getInstance().setValue("000021");//adda
                break;
            case "lda": //lda.opcode = HEX(2)
                ProgramCounter.getInstance().setValue("000025");//lda
                break;
            case "anda": //anda.opcode = HEX(3)
                Accumulator.getInstance().setValue("55555555");
                ProgramCounter.getInstance().setValue("000029");//anda
                break;
            case "sta": //sta.opcode = HEX(4)
                Accumulator.getInstance().setValue("22222222");
                ProgramCounter.getInstance().setValue("00002d");//sta
                break;
            case "shra": //shra.opcode = HEX(5)
                Accumulator.getInstance().setValue("00132132");
                ProgramCounter.getInstance().setValue("000032");//shra
                break;
            case "jmpz": //jmpz.opcode = HEX(6)
                ProgramCounter.getInstance().setValue("000034");//jmpz
                break;
            case "jmp": //jmp.opcode = HEX(7)
                ProgramCounter.getInstance().setValue("000038");//jmp
                break;
            case "coma": //com.opcode = HEX(8)
                ProgramCounter.getInstance().setValue("00003c");//coma
                break;
            default:
                ProgramCounter.getInstance().setValue("000021");//adda
        }
    }

    public static String retrunOpCode(String instruction) {
        switch (instruction) {
            case "adda": //adda.opcode = HEX(1)
                return "1";
            case "lda": //lda.opcode = HEX(2)
                return "2";
            case "anda": //anda.opcode = HEX(3)
                return "3";
            case "sta": //sta.opcode = HEX(4)
                return "4";
            case "shra": //shra.opcode = HEX(5)
                return "5";
            case "jmpz": //jmpz.opcode = HEX(6)
                return "6";
            case "jmp": //jmp.opcode = HEX(7)
                return "7";
            case "coma": //com.opcode = HEX(8)
                return "8";
            default:
                return "0";
        }
    }
}
