package project.instructions;

import lombok.Data;
import project.model.processor.behavior.signals.*;

@Data
public class StA implements BaseInstruction {

    Integer noOfCycles = 13;

    private static final StA ST_A = new StA();

    public static StA getInstance() {
        return ST_A;
    }


    @Override
    public void execute() {
        //1. MAR <- MDR[23:0}
        EMDR.getInstance().sendSubstring("data");
        EMDR.getInstance().signal();
        LMAR.getInstance().signal();

        //2. MDR <- A
        EA.getInstance().signal();
        LMDR.getInstance().setSource("bus");
        LMDR.getInstance().signal();

        //3. M{MAR} <- MDR
        WRITE.getInstance().signal();
    }
}
