package project.model.processor;

import lombok.Data;

@Data
public final class Accumulator extends BaseComponent {

    private static final Accumulator ACCUMULATOR = new Accumulator();

    public static Accumulator getInstance() {
        return ACCUMULATOR;
    }
}
