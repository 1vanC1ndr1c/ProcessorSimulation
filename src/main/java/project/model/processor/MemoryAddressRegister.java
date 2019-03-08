package project.model.processor;

import lombok.Data;

@Data
public final class MemoryAddressRegister extends BaseComponent {

    private static final MemoryAddressRegister MEMORY_ADDRESS_REGISTER = new MemoryAddressRegister();

    public static MemoryAddressRegister getInstance() {
        return MEMORY_ADDRESS_REGISTER;
    }
}

