package project.model.processor;

import lombok.Data;

@Data
public final class ConditionChecker extends BaseComponent {

    private static final ConditionChecker CONDITION_CHECKER = new ConditionChecker();

    public static ConditionChecker getInstance() {
        return CONDITION_CHECKER;
    }

    public boolean checkAccumulator() {
        String accumulatorValue = Accumulator.getInstance().getValue();

        for (int i = 0; i < accumulatorValue.length(); i++) {
            if (accumulatorValue.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }
}
