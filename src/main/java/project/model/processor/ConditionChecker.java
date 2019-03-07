package project.model.processor;

import lombok.Data;

@Data
public final class ConditionChecker extends BaseComponent {
    private static final ConditionChecker CONDITION_CHECKER = new ConditionChecker();

    public static ConditionChecker getInstance() {
        return CONDITION_CHECKER;
    }
}
