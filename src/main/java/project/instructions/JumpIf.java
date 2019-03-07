package project.instructions;

import lombok.Data;

@Data
public class JumpIf implements BaseInstruction {

    private static final JumpIf JUMP_IF = new JumpIf();

    public static JumpIf getInstance() {
        return JUMP_IF;
    }

    @Override
    public void execute() {

    }
}
