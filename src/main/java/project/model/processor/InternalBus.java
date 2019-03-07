package project.model.processor;

import lombok.Data;

@Data
public class InternalBus extends BaseComponent {
    private static final InternalBus INTERNAL_BUS = new InternalBus();

    public static InternalBus getInstance() {
        return INTERNAL_BUS;
    }
}
