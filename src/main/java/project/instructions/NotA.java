package project.instructions;

import lombok.Data;

@Data
public class NotA implements BaseInstruction {

    private static final NotA NOT_A = new NotA();

    public static NotA getInstance() {
        return NOT_A;
    }

    @Override
    public void execute() {

    }
}
