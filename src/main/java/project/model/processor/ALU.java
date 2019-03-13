package project.model.processor;

import lombok.Data;

@Data
public class ALU extends BaseComponent {

    private String value = "00000000";

    private static final ALU ALU = new ALU();

    public static ALU getInstance() {
        return ALU;
    }
}
