package project.instructions;

import lombok.Data;
import project.model.processor.behavior.signals.EMDR;
import project.model.processor.behavior.signals.LPC;

@Data
public final class Jmp implements BaseInstruction {

    Integer noOfCycles = 8;

    private static final Jmp JMP = new Jmp();

    public static Jmp getInstance() {
        return JMP;
    }

    @Override
    public void execute() {
        //1. PC <- MDR [23:0}, if A = 0
        EMDR.getInstance().sendSubstring("data");
        EMDR.getInstance().signal();
        LPC.getInstance().signal();
    }
}
