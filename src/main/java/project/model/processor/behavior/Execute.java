package project.model.processor.behavior;

import lombok.Data;

@Data
public final class Execute {

    private static final Execute EXECUTE = new Execute();

    public static Execute getInstance() {
        return EXECUTE;
    }

    public static void execute() {
        System.out.println("EXECUTE PHASE:======================================");
        Fetch.getInstance().getDecodedInstruction().execute();
        System.out.println("END EXECUTE:========================================");
    }
}
