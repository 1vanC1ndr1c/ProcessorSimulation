package project.model.processor;

import lombok.Data;

@Data
public class ProgramCounter extends BaseComponent {

    private String value = "00000000";

    private static final ProgramCounter PROGRAM_COUNTER = new ProgramCounter();

    public static ProgramCounter getInstance() {
        return PROGRAM_COUNTER;
    }
}
