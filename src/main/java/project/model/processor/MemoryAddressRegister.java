package project.model.processor;

import lombok.Data;

@Data
public final class MemoryAddressRegister extends BaseComponent {

    private String value = "000000";

    private static final MemoryAddressRegister MEMORY_ADDRESS_REGISTER = new MemoryAddressRegister();

    public static MemoryAddressRegister getInstance() {
        return MEMORY_ADDRESS_REGISTER;
    }
}

