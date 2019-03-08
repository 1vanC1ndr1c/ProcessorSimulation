package project.model.processor;

import lombok.Data;

@Data
public class TemporaryRegister extends BaseComponent {

    private static final TemporaryRegister TEMPORARY_REGISTER = new TemporaryRegister();

    public static TemporaryRegister getInstance() {
        return TEMPORARY_REGISTER;
    }
}
