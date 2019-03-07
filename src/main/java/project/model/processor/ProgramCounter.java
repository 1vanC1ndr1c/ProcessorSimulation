package project.model.processor;

import lombok.Data;

@Data
public class ProgramCounter extends BaseComponent {
    private static final ProgramCounter PROGRAM_COUNTER = new ProgramCounter();

    public static ProgramCounter getInstance() {
        return PROGRAM_COUNTER;
    }
}
