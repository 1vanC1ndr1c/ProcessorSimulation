package project.instructions;

import lombok.Data;
import project.model.processor.ConditionChecker;
import project.model.processor.behavior.signals.EMDR;
import project.model.processor.behavior.signals.LPC;

@Data
public class JmpZ implements BaseInstruction {

    private static final JmpZ JUMP_IF = new JmpZ();

    public static JmpZ getInstance() {
        return JUMP_IF;
    }

    @Override
    public void execute() {

        //1. PC <- MDR [23:0}, if A = 0
        if (ConditionChecker.getInstance().checkAccumulator()) {
            //if A = 0
            EMDR.getInstance().sendSubstring("data");
            EMDR.getInstance().signal();
            LPC.getInstance().signal();
        } else {
            System.out.println("             Accumulator is not '0'");
        }

    }
}
