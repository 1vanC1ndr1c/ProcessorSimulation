package project.instructions;

import lombok.Data;
import project.model.processor.behavior.signals.*;

@Data
public class LdA implements BaseInstruction {

    Integer noOfCycles = 12;

    private static final LdA LD_A = new LdA();

    public static LdA getInstance() {
        return LD_A;
    }

    @Override
    public void execute() {
        //1. MAR <- MDR[23:0}
        EMDR.getInstance().sendSubstring("data");
        EMDR.getInstance().signal();
        LMAR.getInstance().signal();

        //2. MDR <- M[MAR], read
        READ.getInstance().signal();
        LMDR.getInstance().setSource("data");                   //LMDR can be MDR <- IntBus or in this case MDR <- Data
        LMDR.getInstance().signal();

        //3. A <- MDR
        EMDR.getInstance().sendSubstring("all");
        EMDR.getInstance().signal();
        LA.getInstance().signal();
    }
}
