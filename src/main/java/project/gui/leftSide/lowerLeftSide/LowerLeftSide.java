package project.gui.leftSide.lowerLeftSide;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Data;
import project.logic.CycleHandler;
import project.model.processor.behavior.Fetch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Data
public class LowerLeftSide {

    public static Map<Integer, String> operationsMap = new LinkedHashMap<>();

    public static VBox lowerLeftSideBox = new VBox();

    private static LowerLeftSide LOWER_LEFT_SIDE = new LowerLeftSide();

    public static LowerLeftSide getInstance() {
        return LOWER_LEFT_SIDE;
    }

    public static VBox set(BorderPane borderPane) {
        lowerLeftSideBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        lowerLeftSideBox.setStyle("-fx-border-color: black");
        lowerLeftSideBox.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        setActiveOperations();

        return lowerLeftSideBox;
    }

    public static void setActiveOperations() {

        lowerLeftSideBox.getChildren().clear();

        Integer currCycle = CycleHandler.getInstance().getCurrentCycle();

        if (currCycle < 8 + CycleHandler.getInstance().getInstructionStartCycle() && currCycle >= 0) {
            activeOperationsFetchPhase();
        }

        for (Integer i = 1; i <= currCycle; i++) {
            if (operationsMap.get(i) != null) {
                lowerLeftSideBox.getChildren().add(new Text(("#" + i.toString() + ": " + operationsMap.get(i))));
            }
        }

    }

    //depending on the current cycle, different elements are being active in the grid
    //since all the instructions have the same fetch phase, it is being executed here, while the execute
    //phases are being executed in the individual classes of the instructions
    public static void activeOperationsFetchPhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 1 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. MAR <- PC");
            activeOperations.add("epc");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 2 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("1. MAR <- PC");
            activeOperations.add("epc");
            activeOperations.add("lmar");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 3 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("2. MDR <- M[MAR]");
            activeOperations.add("read");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 4 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("2. MDR <- M[MAR]");
            activeOperations.add("read");
            activeOperations.add("lmdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 5 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("3. PC++, IR <- MDR{31:28}");
            activeOperations.add("inc");
            activeOperations.add("emdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 6 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("3. PC++, IR <- MDR{31:28}");
            activeOperations.add("emdr");
            activeOperations.add("lir");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 7 + CycleHandler.getInstance().getInstructionStartCycle()) {
            activeOperations.add("4. *decoding*");
        }

        if (CycleHandler.getInstance().getCurrentCycle() > 0) {
            if (Fetch.decodedCorrectly) {
                for (String s : activeOperations) {
                    activeOperationsString = activeOperationsString + s + ", ";
                }
                //remove the last ', '
                activeOperationsString = activeOperationsString.replaceAll(", $", "");

                if (activeOperationsString.length() > 0)
                    operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
            }
            if (!Fetch.decodedCorrectly) {
                operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), "4. *decoding*");
            }
            if (!Fetch.decodedCorrectly
                    && CycleHandler.getInstance().getCurrentCycle() == CycleHandler.getInstance().getInstructionStartCycle()) {
                operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), "*decoding failed*");
            }
        }
    }
}
