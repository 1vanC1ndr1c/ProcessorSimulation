package project.instructions;

import lombok.Data;
import project.model.processor.ConditionChecker;
import project.model.processor.behavior.signals.EMDR;
import project.model.processor.behavior.signals.LPC;

@Data
public class JmpZ implements BaseInstruction {

    private static final JmpZ JMP_Z = new JmpZ();

    public static JmpZ getInstance() {
        return JMP_Z;
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
            System.out.println("            *** Jump not executed. Acc != 0 ***");
        }
    }
}
