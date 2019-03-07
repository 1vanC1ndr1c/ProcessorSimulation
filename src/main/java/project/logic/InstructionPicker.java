package project.logic;

import project.model.processor.Accumulator;
import project.model.processor.ProgramCounter;

public class InstructionPicker {

    public static void pick(String instruction) {

        switch (instruction) {
            case "adda": //adda.opcode = HEX(1)
                Accumulator.getInstance().setValue("00000001");//ACC = 1
                ProgramCounter.getInstance().setValue("000100");//adda
                break;
            case "lda": //lda.opcode = HEX(2)
                ProgramCounter.getInstance().setValue("000200");//lda
                break;
            case "anda": //anda.opcode = HEX(3)
                Accumulator.getInstance().setValue("55555555");
                ProgramCounter.getInstance().setValue("000300");//anda
                break;
            case "sta": //sta.opcode = HEX(4)
                Accumulator.getInstance().setValue("22222222");
                ProgramCounter.getInstance().setValue("000400");//sta
            case "shra": //sta.opcode = HEX(4)
                ProgramCounter.getInstance().setValue("000500");//sta
                break;

            default:
                ProgramCounter.getInstance().setValue("000100");//adda
        }
    }
}
