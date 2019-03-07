package project.instructions;

import lombok.Data;

@Data
public final class Jump implements BaseInstruction {

    private static final Jump JUMP = new Jump();

    public static Jump getInstance() {
        return JUMP;
    }

    @Override
    public void execute() {

    }
}
