package project.model.processor;


import lombok.Data;
import project.model.processor.behavior.Execute;
import project.model.processor.behavior.Fetch;


@Data
public class ControlUnit extends BaseComponent {
    private static final ControlUnit CONTROL_UNIT = new ControlUnit();

    public static ControlUnit getInstance() {
        return CONTROL_UNIT;
    }

    public void start() {
        Fetch.getInstance().fetch();
        Execute.getInstance().execute();
    }

}
