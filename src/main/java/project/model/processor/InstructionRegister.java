package project.model.processor;

import lombok.Data;

@Data
public class InstructionRegister extends BaseComponent {

    private String value = "0";

    private static final InstructionRegister INSTRUCTION_REGISTER = new InstructionRegister();

    public static InstructionRegister getInstance() {
        return INSTRUCTION_REGISTER;
    }
}
