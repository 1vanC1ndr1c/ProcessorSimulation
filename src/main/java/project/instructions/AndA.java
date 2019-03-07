package project.instructions;

import lombok.Data;
import project.model.processor.behavior.signals.*;

@Data
public final class AndA implements BaseInstruction {

    private static final AndA AND_A = new AndA();

    public static AndA getInstance() {
        return AND_A;
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

        //3. A <- A & MDR
        EMDR.getInstance().sendSubstring("all");
        EMDR.getInstance().signal();
        AND.getInstance().signal();
        LALU.getInstance().signal();
        EALU.getInstance().signal();
        LA.getInstance().signal();

        //checkAccumulator:
        //https://www.rapidtables.com/calc/math/binary-calculator.html
    }
}
