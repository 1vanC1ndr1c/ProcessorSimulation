package project.gui.leftSide.lowerLeftSide;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Data;
import project.gui.leftSide.middleLeftSide.CycleHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Data
public class LowerLeftSide {

    private static Map<Integer, String> operationsMap = new LinkedHashMap<>();

    public static VBox lowerLeftSideBox = new VBox();

    private static LowerLeftSide LOWER_LEFT_SIDE = new LowerLeftSide();

    public static LowerLeftSide getInstance() {
        return LOWER_LEFT_SIDE;
    }

    public static VBox set(BorderPane borderPane) {
        lowerLeftSideBox.prefWidthProperty().bind(borderPane.widthProperty().multiply(0.15));
        lowerLeftSideBox.setStyle("-fx-border-color: black");
        lowerLeftSideBox.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        lowerLeftSideBox = setActiveOperations(lowerLeftSideBox);
        return lowerLeftSideBox;
    }

    public static VBox setActiveOperations(VBox lowerLeftSideBox) {

        lowerLeftSideBox.getChildren().clear();

        Integer currCycle = CycleHandler.getInstance().getCurrentCycle();

        if (currCycle < 8 && currCycle >= 0) {
            activeOperationsFetchPhase();
        }

        for (Integer i = 1; i <= currCycle; i++) {
            lowerLeftSideBox.getChildren().add(new Text(("#" + i.toString() + ": " + operationsMap.get(i))));
        }


        return lowerLeftSideBox;
    }

    //depending on the current cycle, different elements are being active in the grid
    //since all the instructions have the same fetch phase, it is being executed here, while the execute
    //phases are being executed in the individual classes of the instructions
    public static void activeOperationsFetchPhase() {

        String activeOperationsString = "";
        List<String> activeOperations = new ArrayList<>();

        if (CycleHandler.getInstance().getCurrentCycle() == 1) {
            activeOperations.add("1. MAR <- PC");
            activeOperations.add("epc");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 2) {
            activeOperations.add("1. MAR <- PC");
            activeOperations.add("epc");
            activeOperations.add("lmar");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 3) {
            activeOperations.add("2. MDR <- M[MAR]");
            activeOperations.add("read");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 4) {
            activeOperations.add("2. MDR <- M[MAR]");
            activeOperations.add("read");
            activeOperations.add("lmdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 5) {
            activeOperations.add("3. PC++, IR <- MDR{31:28}");
            activeOperations.add("inc");
            activeOperations.add("emdr");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 6) {
            activeOperations.add("3. PC++, IR <- MDR{31:28}");
            activeOperations.add("emdr");
            activeOperations.add("lir");
        }
        if (CycleHandler.getInstance().getCurrentCycle() == 7) {
            activeOperations.add("4. *decoding*");
        }

        if (CycleHandler.getInstance().getCurrentCycle() < 8 && CycleHandler.getInstance().getCurrentCycle() > 0) {

            for (String s : activeOperations) {
                activeOperationsString = activeOperationsString + s + ", ";
            }
            //remove the last ','
            activeOperationsString = activeOperationsString.substring(0, activeOperationsString.length() - 2);

            operationsMap.put(CycleHandler.getInstance().getCurrentCycle(), activeOperationsString);
        }
    }
}
