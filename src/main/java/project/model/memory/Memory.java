package project.model.memory;

import lombok.Data;

import java.util.Map;

@Data
public final class Memory {
    private Map<String, String> locationAndContent;

    private static final Memory MEMORY = new Memory();
    public static Memory getInstance() {
        return MEMORY;
    }
}
