package project.instructions;


import lombok.Data;
import project.model.processor.behavior.signals.SHR;

@Data
public class ShrA implements BaseInstruction {

    private static final ShrA SHR_A = new ShrA();

    public static ShrA getInstance() {
        return SHR_A;
    }

    @Override
    public void execute() {
        //1. A <- shr(A)
        SHR.getInstance().signal();
    }
}
