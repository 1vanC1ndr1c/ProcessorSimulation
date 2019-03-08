package project.instructions;

import lombok.Data;
import project.model.processor.behavior.signals.COM;
import project.model.processor.behavior.signals.EALU;
import project.model.processor.behavior.signals.LA;
import project.model.processor.behavior.signals.LALU;

@Data
public class ComA implements BaseInstruction {

    private static final ComA COM_A = new ComA();

    public static ComA getInstance() {
        return COM_A;
    }

    @Override
    public void execute() {
        //1. A <- not A
        COM.getInstance().signal();
        LALU.getInstance().signal();
        EALU.getInstance().signal();
        LA.getInstance().signal();
    }
}
