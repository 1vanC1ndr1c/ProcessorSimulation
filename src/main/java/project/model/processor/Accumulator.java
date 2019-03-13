package project.model.processor;

import lombok.Data;

@Data
public final class Accumulator extends BaseComponent {

    private String value = "00000000";

    private static final Accumulator ACCUMULATOR = new Accumulator();

    public static Accumulator getInstance() {
        return ACCUMULATOR;
    }
}
