package project.model.processor;

import lombok.Data;

@Data
public final class MemoryDataRegister extends BaseComponent {

    private String value = "00000000";

    private static final MemoryDataRegister MEMORY_DATA_REGISTER = new MemoryDataRegister();

    public static MemoryDataRegister getInstance() {
        return MEMORY_DATA_REGISTER;
    }
}
